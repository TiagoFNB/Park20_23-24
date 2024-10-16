import { Component, OnInit } from "@angular/core";
import { Subscription } from "rxjs";
import { ToastEmitter } from "../_services/toast-emitter.service";
import { GetStaysService } from "../_services/get-stays.service";
import { HttpErrorResponse } from "@angular/common/http";
import { ErrorResponse } from "../_dtos/ErrorResponse.dto";
import { StayDTO } from "../_dtos/Stay.dto";
import { ChartConfiguration, ChartOptions } from "chart.js";
import { ListParksService } from "../_services/list-parks.service";

@Component({
  selector: 'component-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit{

  private subscriptions: Subscription[] = [];

  private stays : StayDTO[];

  private parks;

  constructor(
    private te: ToastEmitter,
    private getStaysService: GetStaysService,
    private listParksService: ListParksService
  ) {}

  ngOnInit(): void {
    this.subscriptions.push(
      this.getStaysService.get().subscribe(
          (response) => {
            this.stays = response;

            this.listParksService.get().subscribe(
              (response) => {
                this.parks = response;
                this.calculateVehicleTypeLastMonth();
                this.calculateMostUsedParksLastMonth();
                this.calculateMostBusyDayOfWeek();
                this.calculateTimeOfDayByUsage();
              },
              (error : HttpErrorResponse) => {
                const err : ErrorResponse = error.error;
                this.te.emitError(err.message);
              }
            );
          },
          (error : HttpErrorResponse) => {
            const err : ErrorResponse = error.error;
            this.te.emitError(err.message);
          }
    ));
  }

  calculateVehicleTypeLastMonth() {
    const currentDate = new Date();
    let eletric =0;
    let gpl=0;
    let gas=0;
    let motorycle=0;

    this.stays.forEach(
      (s) => {

        if(this.wasLastMonth(currentDate, new Date(s.stayStartTime))) {
          let v = s.vehicle;
          if(v.category=="Eletric") eletric++;
          if(v.category=="GPL") gpl++;
          if(v.category=="Gas") gas++;
          if(v.category=="Motorcycle") motorycle++;
          return;
        }
      }
    )

    this.graphByVehicleType_pieChartDatasets = [ {
      data: [ eletric, gpl, gas, motorycle ]
    } ];
  }

  //Park by vehicle type since forever
  public graphByVehicleType_pieChartOptions: ChartOptions<'pie'> = {
    responsive: false,
    maintainAspectRatio: true,
  };
  public graphByVehicleType_pieChartLabels = [ ['Eletric'], [ 'GPL' ], ['Gas'], ['Motorcycle'], ];
  public graphByVehicleType_pieChartDatasets;
  public graphByVehicleType_pieChartLegend = true;
  public graphByVehicleType_pieChartPlugins = [];

  calculateMostUsedParksLastMonth() {
    const currentDate = new Date();

    let parkIds : any = {};
    this.stays.forEach((stay) => {
      if(this.wasLastMonth(currentDate, new Date(stay.stayStartTime))) {
        if(!parkIds[stay.parkId]) parkIds[stay.parkId] = 1;
        else {
          parkIds[stay.parkId]++;
        }
      }
    });

    const parkDesignations = Object.entries(parkIds).map(([parkId, count]) => {
      const park = this.parks.find(p => p.designation === parkId);
      return [park.designation, count];
    });

    this.graphByParkUsage_barChartData = {
      labels: parkDesignations.map(el => el[0]),
      datasets: [
        { data: parkDesignations.map(el => el[1]), label: 'Park usage' }
      ]
    };
  }

  public graphByParkUsage_barChartLegend = true;
  public graphByParkUsage_barChartPlugins = [];
  public graphByParkUsage_barChartData: ChartConfiguration<'bar'>['data'] = {
    labels: [],
    datasets: [
    ]
  };
  public graphByParkUsage_barChartOptions: ChartConfiguration<'bar'>['options'] = {
    responsive: false,
    maintainAspectRatio: true,
  };


  calculateMostBusyDayOfWeek() {
    const weekCount = {0: 0, 1:0, 2:0, 3:0, 4:0, 5:0, 6:0};
    this.stays.forEach((stay) => {
      const date = new Date(stay.stayStartTime);
      const dayOfWeek = date.getDay();

      if (weekCount[dayOfWeek] === undefined) {
        weekCount[dayOfWeek] = 1;
      } else {
        weekCount[dayOfWeek]++;
      }
    });

    this.graphByDayofWeek_barChartData = {
      labels: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
      datasets: [
        { data: Object.values(weekCount), label: 'Days of Week' }
      ]
    };
  }

  public graphByDayofWeek_barChartLegend = true;
  public graphByDayofWeek_barChartPlugins = [];
  public graphByDayofWeek_barChartData: ChartConfiguration<'bar'>['data'] = {
    labels: [],
    datasets: [
    ]
  };
  public graphByDayofWeek_barChartOptions: ChartConfiguration<'bar'>['options'] = {
    responsive: false,
    maintainAspectRatio: true,
  };


  calculateTimeOfDayByUsage() {
    let hours = {0:0,1:0,2:0,3:0,4:0,5:0,6:0,7:0,8:0,9:0,10:0,11:0,12:0,13:0,14:0,15:0,16:0,17:0,18:0,19:0,20:0,21:0,22:0,23:0};
    let hourStarts = this.stays.map((stay) => {return new Date(stay.stayStartTime).getHours()});

    hourStarts.forEach((hour) => {
      if (hours[hour] === undefined) {
        hours[hour] = 1;
      } else {
        hours[hour]++;
      }
    });

    this.lineChartData = {
      labels: Object.keys(hours),
      datasets: [
        {
          data: Object.values(hours),
          label: 'Hours of day',
          fill: true,
          tension: 0.5,
          borderColor: 'black',
          backgroundColor: 'rgba(255,0,0,0.3)'
        }
      ]
    };
  }

  public lineChartData: ChartConfiguration<'line'>['data'] = {
    labels: [],
    datasets: [
    ]
  };
  public lineChartOptions: ChartOptions<'line'> = {
    responsive: false
  };
  public lineChartLegend = true;


  wasLastMonth(referenceDate, verifyingDate) {
    return (referenceDate.getFullYear() === verifyingDate.getFullYear() && referenceDate.getMonth() === verifyingDate.getMonth() + 1) ||
      (referenceDate.getFullYear() === verifyingDate.getFullYear() + 1 && referenceDate.getMonth() === 0 && verifyingDate.getMonth() === 11) // 11 represents December
  }

  /**
   * Signals if we're waiting for a subscription or not
   * @returns
   */
  waitingForSubscription(): boolean {
    return this.subscriptions.some(s => {
      return s.closed;
    });
  }
}

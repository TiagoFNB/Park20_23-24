import { HttpErrorResponse } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { FormControl, FormGroup } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { Subscription } from "rxjs";
import { ErrorResponse } from "src/app/_dtos/ErrorResponse.dto";
import { GetParksFullService } from "src/app/_services/get-parks-full.service";
import { SaveParkConfigurationService } from "src/app/_services/save-park-configuration.service";
import { ToastEmitter } from "src/app/_services/toast-emitter.service";

@Component({
  selector: 'component-park',
  templateUrl: './park.component.html',
  styleUrls: ['./park.component.css']
})
export class ParkComponent implements OnInit {

  private subscriptions: Subscription[] = [];

  id;
  designation;

  park;

  form: FormGroup;

  constructor(
    private te: ToastEmitter,
    private route: ActivatedRoute,
    private getParksFullService: GetParksFullService,
    private saveParkConfigurationService: SaveParkConfigurationService,
  ) {
    this.form = new FormGroup({
        SpotsGas: new FormControl(0, []),
        SpotsEletric: new FormControl(0, []),
        SpotsGPL: new FormControl(0, []),
        SpotsMotorcycle: new FormControl(0, []),
        SpotsHandicapped: new FormControl(0, []),
        ParkyCoinHourlyGain: new FormControl(0, []),
        ParkyCoinWorthInTime: new FormControl(0, []),
        ParkyCoinUsageMinimum: new FormControl(0, []),
      }
    );
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.id = params['id'];
    });

    this.route.queryParams.subscribe(queryParams => {
      this.designation = queryParams['designation'];
    });

    this.subscriptions.push(
      this.getParksFullService.get(this.id).subscribe(
          (response) => {
            this.park = response;

            this.form.patchValue(
              {
                SpotsGas: this.park.spotsGas,
                SpotsEletric: this.park.spotsElectric,
                SpotsGPL: this.park.spotsGPL,
                SpotsMotorcycle: this.park.spotsMotorcycle,
                SpotsHandicapped: this.park.spotsHandicapped,
                ParkyCoinHourlyGain: this.park.parkyCoinGainHourly,
                ParkyCoinWorthInTime: this.park.parkyCoinWorthInTime,
                ParkyCoinUsageMinimum: this.park.parkyCoinUsageMinimumTime,
              }
            )
          },
          (error : HttpErrorResponse) => {
            const err : ErrorResponse = error.error;
            this.te.emitError(err.message);
          }
    ));
  }


  confirm() {
    const body = {
      parkId: this.id,
      spotsGas: this.form.get('SpotsGas').value,
      spotsGPL: this.form.get('SpotsGPL').value,
      spotsElectric: this.form.get('SpotsEletric').value,
      spotsMotorcycle: this.form.get('SpotsMotorcycle').value,
      spotsHandicapped: this.form.get('SpotsHandicapped').value,
      parkyCoinGainHourly: this.form.get('ParkyCoinHourlyGain').value,
      parkyCoinWorthInTime: this.form.get('ParkyCoinWorthInTime').value,
      parkyCoinUsageMinimumTime: this.form.get('ParkyCoinUsageMinimum').value,
    };

    this.subscriptions.push(
      this.saveParkConfigurationService.save(body).subscribe(
          (response) => {
            this.te.emitNotification("Saved Successfully");
          },
          (error : HttpErrorResponse) => {
            const err : ErrorResponse = error.error;
            this.te.emitError(err.message);
          }
    ));
  }

}

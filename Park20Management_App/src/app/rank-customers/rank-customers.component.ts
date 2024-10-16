import { Component, OnInit } from "@angular/core";
import { FormArray } from "@angular/forms";
import { ToastEmitter } from "src/app/_services/toast-emitter.service";
import { HttpErrorResponse } from "@angular/common/http";
import { ErrorResponse } from "../_dtos/ErrorResponse.dto";
import { Subscription } from "rxjs";
import { GetParksByManagerService } from '../_services/get-parks-by-manager.service';
import { ParkWithDistanceDTO } from '../_dtos/ParkWithDistance.dto';
import { CustomerUsageRankDTO } from '../_dtos/CustomerUsageRank.dto';
import { RankCustomersUsageService } from '../_services/rank-customers-usage.service';

@Component({
  selector: 'component-rank-customers',
  templateUrl: './rank-customers.component.html',
  styleUrls: ['./rank-customers.component.css']
})
export class RankCustomersComponent implements OnInit {

  private subscriptions: Subscription[] = [];

  listCustomers: CustomerUsageRankDTO[] = [];
  listParks: ParkWithDistanceDTO[];

  form: FormArray;
  selectedParks: any;

  constructor(
    private te: ToastEmitter,
    private rankCustomersUsageService: RankCustomersUsageService,
    private getParksByManagerService: GetParksByManagerService,
  ) {
    
  }

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.subscriptions.push(
      this.getParksByManagerService.getNoParams().subscribe(
          (res) => {
            this.listParks = res;
          },
          (error : HttpErrorResponse) => {
            const err : ErrorResponse = error.error;
            this.te.emitError(err.message);
          }
    ));
      
   
  }

  fetchCustomers(){
    this.subscriptions.push(
      this.rankCustomersUsageService.get(this.selectedParks).subscribe(
          (res) => {
            this.listCustomers = res;
          },
          (error : HttpErrorResponse) => {
            const err : ErrorResponse = error.error;
            this.te.emitError(err.message);
          }
    ));
  }

  

}

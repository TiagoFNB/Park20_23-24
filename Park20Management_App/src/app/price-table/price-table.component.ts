import { SavePriceTableService } from './../_services/save-price-table.service';
import { Component, EventEmitter, Input, OnInit, Output } from "@angular/core";
import { FormArray, FormControl, FormGroup, Validators } from "@angular/forms";
import { MatDialog } from "@angular/material/dialog";
import { ToastEmitter } from "src/app/_services/toast-emitter.service";
import { PriceTableDialog } from "src/app/price-table/price-table-dialog/price-table-dialog.component";
import { HttpErrorResponse } from "@angular/common/http";
import { ErrorResponse } from "../_dtos/ErrorResponse.dto";
import { Subscription } from "rxjs";
import { GetParkPriceTablesService } from "../_services/get-park-price-tables.service";
import { PriceTableDTO } from "../_dtos/PriceTable.dto";
import { GetParksByManagerService } from '../_services/get-parks-by-manager.service';
import { ParkWithDistanceDTO } from '../_dtos/ParkWithDistance.dto';

@Component({
  selector: 'component-price-table',
  templateUrl: './price-table.component.html',
  styleUrls: ['./price-table.component.css']
})
export class PriceTableComponent implements OnInit {

  private subscriptions: Subscription[] = [];

  listPriceTables: PriceTableDTO[] = [];
  listParks: ParkWithDistanceDTO[];

  form: FormArray;
  selectedPark: any;

  constructor(
    private te: ToastEmitter,
    private dialog: MatDialog,
    private getParkPriceTablesService: GetParkPriceTablesService,
    private getParksByManagerService: GetParksByManagerService,
    private savePriceTableService:SavePriceTableService
  ) {
    this.form = new FormArray([], [Validators.required,]);
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

  fetchTables(){
    this.subscriptions.push(
      this.getParkPriceTablesService.getParkPriceTables(this.selectedPark).subscribe(
          (res) => {
            this.listPriceTables = res;
          },
          (error : HttpErrorResponse) => {
            const err : ErrorResponse = error.error;
            this.te.emitError(err.message);
          }
    ));
  }

  showPriceTable(pt: PriceTableDTO){
    const dialogRef = this.dialog.open(PriceTableDialog,
      {
        // height: '400px',
        // width: '600px',
        data:{
          pt:pt,
          new: false,
        }
      });

    dialogRef.afterClosed().subscribe(result => {
      
    });
  }
  

  addPriceTable() {
    const dialogRef = this.dialog.open(PriceTableDialog,
      {
        height: '800px',
        width: '1000px',
        data:{
          pt:null,
          new: true,
          parkId: this.selectedPark
        }
      });

    dialogRef.afterClosed().subscribe(result => {
      if(result){
        this.subscriptions.push(
          this.savePriceTableService.save(result).subscribe(
              (res) => {
                  this.fetchTables();
              },
              (error : HttpErrorResponse) => {
                const err : ErrorResponse = error.error;
                this.te.emitError(err.message);
              }
        ));
      }
    });
  }


}

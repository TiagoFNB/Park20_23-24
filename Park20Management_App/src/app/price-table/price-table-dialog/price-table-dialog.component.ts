import { HttpErrorResponse } from "@angular/common/http";
import { Component, Inject } from "@angular/core";
import { FormGroup, FormControl, Validators, FormBuilder, FormArray } from "@angular/forms";
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";
import { Subscription } from "rxjs";
import { ErrorResponse } from "src/app/_dtos/ErrorResponse.dto";
import { PriceTableDTO } from "src/app/_dtos/PriceTable.dto";
import { ToastEmitter } from "src/app/_services/toast-emitter.service";
import * as moment from 'moment';
import { DateValidators } from "src/app/shared/date-validators";


@Component({
  selector: 'dialog-price-table',
  templateUrl: 'price-table-dialog.component.html',
  styleUrls: ['./price-table-dialog.component.css'],
})
export class PriceTableDialog {

  priceTableForm : FormGroup;
  maxNumberPeriods = 2;
  maxNumberFractions = 5;
  editingPeriod=false;
  private subscriptions: Subscription[] = [];
  minDate :Date;

  constructor(
    public dialogRef: MatDialogRef<PriceTableDialog>,
    private fb: FormBuilder,
    @Inject(MAT_DIALOG_DATA) public data: {
      pt: PriceTableDTO,
      parkId: string,
      new: boolean,
    },
    private te: ToastEmitter,
  ) {
    if(data.new){
      this.minDate = new Date();
      this.minDate.setDate(this.minDate.getDate() + 1);
      this.priceTableForm = this.fb.group({
        parkId: new FormControl(data.parkId, [Validators.required]),
        effectiveTime: new FormControl('', [Validators.required]),
        periods: this.fb.array([]),
      })
      this.onValueChange();
    }

    
  }

  get maxPeriodsReached(): boolean {
    return this.periods.length >= this.maxNumberPeriods;
  }

  get periods() {
    return this.priceTableForm.get('periods') as FormArray;
  }

  get periodsControls() {
    return (<FormArray>this.priceTableForm.get('periods')).controls ;
  }

  addPeriod() {
    const startControl = new FormControl('', [Validators.required]);
    const endControl = new FormControl('', [ Validators.required]);
   const newPeriod = this.fb.group({
      startTime: startControl,
      endTime: endControl,
      fractions: this.fb.array([]),
      editing:true,
    });
    this.editingPeriod=true;
    this.periods.push(
      newPeriod
    );
    this.priceTableForm.updateValueAndValidity();
    this.priceTableForm.markAsDirty();
  }

  onAddPeriod(index): void {
    this.periods.at(index).patchValue({
      editing:false
    });
    this.editingPeriod=false;
  }

  deletePeriod(index: number) {
    this.periods.removeAt(index);
 }

  get maxFractionsReached(): boolean {
    return this.periods.length >= this.maxNumberFractions;
  }

  getFractions(period){
       return period.controls.fractions.controls;
  }
  addFraction(i) {

    const control = <FormArray>this.periodsControls[i].get('fractions');
    // console.log(control);
    const newFraction = this.fb.group({
      automobilePrice: new FormControl(0, [Validators.required]),
      motorcyclePrice: new FormControl(0, [Validators.required]),
    });
    control.push(newFraction);
    
  }
  deleteFraction(i,j) {
    const control = <FormArray>this.periodsControls[i].get('fractions');
    control.removeAt(j);
 }


  onValueChange() {
    // this.priceTableForm.get('licensePlate').valueChanges.subscribe(newValue => {
    //   if (this.priceTableForm.get('licensePlate').valid) {
    //     this.detectPriceTableCharacteristics();
    //   }
    // });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onAddClick(): void {
    let resource = JSON.stringify(this.priceTableForm.getRawValue());
    let obj : PriceTableDTO = JSON.parse(resource);
    obj.effectiveTime = obj.effectiveTime.split('T')[0];
    //console.log('Add Button clicked: ' + obj);
    this.dialogRef.close(obj);
  }

}

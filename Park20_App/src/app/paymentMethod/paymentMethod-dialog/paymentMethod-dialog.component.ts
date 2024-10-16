import { HttpErrorResponse } from "@angular/common/http";
import { Component, Inject } from "@angular/core";
import { FormGroup, FormControl, Validators } from "@angular/forms";
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";
import { ToastEmitter } from "src/app/_services/toast-emitter.service";

@Component({
  selector: 'dialog-paymentMethod',
  templateUrl: 'paymentMethod-dialog.component.html',
  styleUrls: ['./paymentMethod-dialog.component.css'],
})
export class PaymentMethodDialog {

  paymentForm : FormGroup;

  phoneNumberPattern = /^\d{9}$/;

  constructor(
    public dialogRef: MatDialogRef<PaymentMethodDialog>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private te: ToastEmitter,
  ) {
    this.paymentForm = new FormGroup({
      phoneNumber: new FormControl('', [Validators.required, Validators.pattern(this.phoneNumberPattern)]),
    })

  }


  onNoClick(): void {
    this.dialogRef.close();
  }

  onAddClick(): void {
    this.dialogRef.close(this.paymentForm);
  }
}

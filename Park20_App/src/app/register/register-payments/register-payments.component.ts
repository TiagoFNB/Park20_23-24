import { Component, EventEmitter, Input, Output } from "@angular/core";
import { FormArray } from "@angular/forms";
import { MatDialog } from "@angular/material/dialog";
import { ToastEmitter } from "src/app/_services/toast-emitter.service";
import { PaymentMethodDialog } from "src/app/paymentMethod/paymentMethod-dialog/paymentMethod-dialog.component";

@Component({
  selector: 'component-register-payments',
  templateUrl: './register-payments.component.html',
  styleUrls: ['./register-payments.component.css']
})
export class RegisterPaymentsComponent {

  @Input() form: FormArray;

  @Output() next: EventEmitter<any> = new EventEmitter();

  @Output() back: EventEmitter<any> = new EventEmitter();

  constructor(
    private te: ToastEmitter,
    private dialog: MatDialog
  ) {}

  addPaymentMethod(){
    if(this.form.controls.length >= 3) {
      this.te.emitError("MaximumReached");
    } else {
      const dialogRef = this.dialog.open(PaymentMethodDialog);

      dialogRef.afterClosed().subscribe(result => {
        if(result) {
          this.form.push(result);
        }
      });
    }
  }

  nextStep() {
     if(this.form.valid && this.form.controls.length > 0) {
       this.next.emit();
     } else {
       this.te.emitError("InvalidData");
     }
  }

  backStep() {
    this.back.emit();
  }

}

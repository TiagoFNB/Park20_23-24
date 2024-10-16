import { Component, EventEmitter, Input, Output } from "@angular/core";
import { FormArray, FormControl, FormGroup, Validators } from "@angular/forms";
import { MatDialog } from "@angular/material/dialog";
import { ToastEmitter } from "src/app/_services/toast-emitter.service";
import { HttpErrorResponse } from "@angular/common/http";
import { ErrorResponse } from "../_dtos/ErrorResponse.dto";
import { Subscription } from "rxjs";
import { PaymentMethodDialog } from "./paymentMethod-dialog/paymentMethod-dialog.component";
import { CostumerPaymentMethodDTO } from "../_dtos/CostumerPaymentMethod.dto";
import { SavePaymentMethodListService } from "../_services/save-paymentmethod.service";

@Component({
    selector: 'component-paymentMethod',
    templateUrl: './paymentMethod.component.html',
    styleUrls: ['./paymentMethod.component.css']
  })

  export class PaymentMethodComponent {
    @Input() form: FormArray;

    @Output() next: EventEmitter<any> = new EventEmitter();
  
    @Output() back: EventEmitter<any> = new EventEmitter();

    private subscriptions: Subscription[] = [];

    
  
    constructor(
      private te: ToastEmitter,
      private dialog: MatDialog,
      private savePaymentMethodListService: SavePaymentMethodListService,
    ) {
        this.form = new FormArray([], [Validators.required,]);
      }
  
    addPaymentMethod(){
      if(this.form.controls.length >= 3) {
        this.te.emitError("MaximumReached");
      } else {
        const dialogRef = this.dialog.open(PaymentMethodDialog);
  
        dialogRef.afterClosed().subscribe(result => {
          if(result) {
            const alreadyAdded = this.form.value.find(elem => elem.licensePlate === result.get('phoneNumber').value);
            if(alreadyAdded === undefined) {
                this.form.push(result);
              } else {
                this.te.emitWarning("CannotAddSameNumber");
              }
          }
        });
      }
    }

    confirm() {
        if(this.form.valid && this.form.controls.length > 0) {
          const body = {methods : this.getPaymentMethodDtos()};
    
    
          this.subscriptions.push(
            this.savePaymentMethodListService.save(body).subscribe(
                (response) => {
                  this.te.emitNotification("SaveSuccessful");
                },
                (error : HttpErrorResponse) => {
                  const err : ErrorResponse = error.error;
                  this.te.emitError(err.message);
                }
          ));
        } else {
          this.te.emitError("Minimum1PaymentMethod");
        }
      }


      private getPaymentMethodDtos() : CostumerPaymentMethodDTO[] {
        const methods = [];
    
        for(let control of this.form.controls) {
          const dto = {
            phoneNumber: control.get('phoneNumber').value,
          };
    
          methods.push(dto);
        }
        return methods;
      }

  }
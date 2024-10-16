import { Component } from "@angular/core";
import { FormControl, FormGroup } from "@angular/forms";
import { MatDialog } from "@angular/material/dialog";
import { Subscription } from "rxjs";
import { SaveVehicleListService } from "../_services/save-vehicle.service";
import { ToastEmitter } from "../_services/toast-emitter.service";
import { GetCustomerLoggedService } from "../_services/get-customer-logged.service";
import { HttpErrorResponse } from "@angular/common/http";
import { ErrorResponse } from "../_dtos/ErrorResponse.dto";

@Component({
  selector: 'component-parky-wallet',
  templateUrl: './parkyWallet.component.html',
  styleUrls: ['./parkyWallet.component.css']
})
export class ParkyWalletComponent {

  private subscriptions: Subscription[] = [];

  form: FormGroup;

  constructor(
    private te: ToastEmitter,
    private dialog: MatDialog,
    private getCustomerLoggedService: GetCustomerLoggedService,
  ) {
    this.form = new FormGroup({
      coins: new FormControl(0, []),
    })


    this.subscriptions.push(this.getCustomerLoggedService.get().subscribe(
      (response) => {
        this.form.get('coins').setValue(response.wallet.amount);
      },
      (error : HttpErrorResponse) => {
        const err : ErrorResponse = error.error;
        this.te.emitError(err.message);
      }
    ));
  }


}

import { Component, OnInit } from "@angular/core";
import { FormGroup, FormControl } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { Subscription } from "rxjs";
import { ToastEmitter } from "../_services/toast-emitter.service";
import { BulkAssignCoinsService } from "../_services/bulk-assign-coins.service";
import { HttpErrorResponse } from "@angular/common/http";
import { ErrorResponse } from "../_dtos/ErrorResponse.dto";

@Component({
  selector: 'component-parky-wallet',
  templateUrl: './parky-wallet.component.html',
  styleUrls: ['./parky-wallet.component.css']
})
export class ParkyWalletComponent implements OnInit {

  private subscriptions: Subscription[] = [];

  parkList = [];

  form: FormGroup;

  constructor(
    private te: ToastEmitter,
    private route: ActivatedRoute,
    private bulkAssingCoinsService: BulkAssignCoinsService,
  ) {
    this.form = new FormGroup({
      amount: new FormControl(0,[]),
    }
    );
  }

  ngOnInit(): void {

  }

  confirm() {
    this.subscriptions.push(
      this.bulkAssingCoinsService.assign(this.form.get('amount').value).subscribe(
          (response) => {
            this.te.emitNotification("Successfull Assign");
          },
          (error : HttpErrorResponse) => {
            const err : ErrorResponse = error.error;
            this.te.emitError(err.message);
          }
    ));
  }


}

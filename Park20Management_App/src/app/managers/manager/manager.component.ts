import { Component, EventEmitter, Input, OnInit, Output } from "@angular/core";
import { FormArray, FormControl, FormGroup, Validators } from "@angular/forms";
import { ToastEmitter } from "src/app/_services/toast-emitter.service";
import { Subscription } from "rxjs";
import { ActivatedRoute } from "@angular/router";
import { ListParksService } from "src/app/_services/list-parks.service";
import { HttpErrorResponse } from "@angular/common/http";
import { ErrorResponse } from "src/app/_dtos/ErrorResponse.dto";
import { GetParksByManagerService } from "src/app/_services/get-parks-by-manager.service";
import { AddParksToManagerService } from "src/app/_services/add-parks-to-manager.service";

@Component({
  selector: 'component-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.css']
})
export class ManagerComponent implements OnInit{

  private subscriptions: Subscription[] = [];

  id;
  username;

  parkList = [];

  form: FormGroup;

  constructor(
    private te: ToastEmitter,
    private route: ActivatedRoute,
    private listParksService: ListParksService,
    private getParksByManagerService : GetParksByManagerService,
    private addParksToManagerService: AddParksToManagerService,
  ) {
    this.form = new FormGroup({
      parks: new FormControl([]),
    }
    );
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.id = params['id'];
    });

    this.route.queryParams.subscribe(queryParams => {
      this.username = queryParams['username'];
    });

    this.subscriptions.push(
      this.listParksService.get().subscribe(
          (response) => {
            this.parkList = response;
          },
          (error : HttpErrorResponse) => {
            const err : ErrorResponse = error.error;
            this.te.emitError(err.message);
          }
    ));

    this.subscriptions.push(
      this.getParksByManagerService.get(this.username).subscribe(
          (response) => {
            this.form.get('parks').setValue(response);
          },
          (error : HttpErrorResponse) => {
            const err : ErrorResponse = error.error;
            this.te.emitError(err.message);
          }
    ));
  }


  confirm() {
    this.subscriptions.push(
      this.addParksToManagerService.change(this.id, this.form.get('parks').value).subscribe(
          (response) => {
            this.te.emitNotification("SaveSuccessful");
          },
          (error : HttpErrorResponse) => {
            const err : ErrorResponse = error.error;
            this.te.emitError(err.message);
          }
    ));
  }

}

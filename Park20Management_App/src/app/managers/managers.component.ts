import { Component, EventEmitter, Input, OnInit, Output } from "@angular/core";
import { FormArray, FormControl, FormGroup, Validators } from "@angular/forms";
import { ToastEmitter } from "src/app/_services/toast-emitter.service";
import { Subscription } from "rxjs";
import { ListManagersService } from "../_services/list-managers.service";
import { HttpErrorResponse } from "@angular/common/http";
import { ErrorResponse } from "../_dtos/ErrorResponse.dto";
import { Router } from "@angular/router";

@Component({
  selector: 'component-managers',
  templateUrl: './managers.component.html',
  styleUrls: ['./managers.component.css']
})
export class ManagersComponent implements OnInit{

  private subscriptions: Subscription[] = [];

  managers = [];

  form: FormArray;

  constructor(
    private te: ToastEmitter,
    private router: Router,
    private listManagersService: ListManagersService,
  ) {
    this.form = new FormArray([], [Validators.required,]);
  }

  ngOnInit(): void {
    this.subscriptions.push(
      this.listManagersService.get().subscribe(
          (response) => {
            this.managers = response;
          },
          (error : HttpErrorResponse) => {
            const err : ErrorResponse = error.error;
            this.te.emitError(err.message);
          }
    ));
  }

  redirectToManager(manager) {
    this.router.navigate(['home/manager', manager.id], { queryParams: { username: manager.username } });
  }
}

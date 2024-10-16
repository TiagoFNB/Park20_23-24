import { Component, OnInit } from "@angular/core";
import { FormArray, Validators } from "@angular/forms";
import { ToastEmitter } from "src/app/_services/toast-emitter.service";
import { Subscription } from "rxjs";
import { HttpErrorResponse } from "@angular/common/http";
import { ErrorResponse } from "../_dtos/ErrorResponse.dto";
import { Router } from "@angular/router";
import { ListParksService } from "../_services/list-parks.service";

@Component({
  selector: 'component-parks',
  templateUrl: './parks.component.html',
  styleUrls: ['./parks.component.css']
})
export class ParksComponent implements OnInit{

  private subscriptions: Subscription[] = [];

  parks = [];

  form: FormArray;

  constructor(
    private te: ToastEmitter,
    private router: Router,
    private listParksService: ListParksService,
  ) {
    this.form = new FormArray([], [Validators.required,]);
  }

  ngOnInit(): void {
    this.subscriptions.push(
      this.listParksService.get().subscribe(
          (response) => {
            this.parks = response;
          },
          (error : HttpErrorResponse) => {
            const err : ErrorResponse = error.error;
            this.te.emitError(err.message);
          }
    ));
  }

  redirectToPark(park) {
    this.router.navigate(['home/park', park.id], { queryParams: { designation: park.designation } });
  }
}

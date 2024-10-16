import { Component, OnDestroy } from "@angular/core";
import { FormGroup, FormControl, Validators, FormArray, AbstractControl } from "@angular/forms";
import { Router } from "@angular/router";
import { ToastEmitter } from "../_services/toast-emitter.service";
import { Subscription } from "rxjs";
import { RegisterCustomerService } from "../_services/register-customer.service";
import { LoginResponse } from "../_dtos/LoginResponse.dto";
import { AuthService } from "../_services/authentication.service";
import { HttpErrorResponse } from "@angular/common/http";
import { ErrorResponse } from "../_dtos/ErrorResponse.dto";
import { VehicleDTO } from "../_dtos/Vehicle.dto";
import { CostumerPaymentMethodDTO } from "../_dtos/CostumerPaymentMethod.dto";

@Component({
  selector: 'component-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnDestroy {

  usernamePattern = "^.{6,}$";
  emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
  passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d).{6,}$";
  nifPattern = /\d{9}/;

  userForm: FormGroup;

  vehiclesForm: FormArray;

  paymentsForm: FormArray;

  termsForm: FormGroup;

  private subscriptions: Subscription[] = [];

  step = 1;

  constructor(
    private te: ToastEmitter,
    private router: Router,
    private registerCustomerService: RegisterCustomerService,
    private authServ: AuthService,
  ) {
    this.userForm = new FormGroup({
      username: new FormControl('', [Validators.required, Validators.pattern(this.usernamePattern), Validators.minLength(6),]),
      password: new FormControl('', [Validators.required, Validators.pattern(this.passwordPattern), Validators.minLength(6),]),
      email: new FormControl('', [Validators.required, Validators.pattern(this.emailPattern)]),
      name: new FormControl('', [Validators.required,]),
      nif: new FormControl('', [Validators.required, Validators.pattern(this.nifPattern), Validators.minLength(9), Validators.maxLength(9)]),
      handicapped: new FormControl(false, [Validators.required]),
    })

    this.vehiclesForm = new FormArray([], [Validators.required,]);

    this.paymentsForm = new FormArray([], [Validators.required,]);

    this.termsForm = new FormGroup({
      accept: new FormControl(false, [Validators.required]),
    })
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  nextStep() {
    if(this.step === 4) {
      this.confirm();
    } else {
      this.step++;
    }
  }

  backStep() {
    this.step--;
  }

  confirm() {

    const vehicles = this.getVehicleDtos();
    const payments = this.getPaymentDtos();

    const body = {
      username: this.userForm.get('username').value,
      password: this.userForm.get('password').value,
      email: this.userForm.get('email').value,
      name : this.userForm.get('name').value,
      nif: this.userForm.get('nif').value,
      handicapped: this.userForm.get('handicapped').value,
      vehicles: vehicles,
      payments: payments,
    };

    this.subscriptions.push(
      this.registerCustomerService
        .register(body).subscribe(
          (response : LoginResponse) => {
            const token = response.token;
            this.authServ.setToken(token);
            this.te.emitNotification("RegisterSuccessful");
            this.router.navigate(['/home']);
          },
          (error : HttpErrorResponse) => {
            const err : ErrorResponse = error.error;
            this.te.emitError(err.message);
          }
    ));
  }

  private getVehicleDtos() : VehicleDTO[] {
    const vehicles = [];

    for(let control of this.vehiclesForm.controls) {
      const dto = {
        plate: control.get('licensePlate').value,
        brand: control.get('brand').value,
        model: control.get('model').value,
        category: control.get('category').value,
      };

      vehicles.push(dto);
    }
    return vehicles;
  }

  private getPaymentDtos() : CostumerPaymentMethodDTO[] {
    const payments = [];

    for(let control of this.paymentsForm.controls) {
      const dto = {
        phoneNumber: control.get('phoneNumber').value,
      };

      payments.push(dto);
    }
    return payments;
  }


}

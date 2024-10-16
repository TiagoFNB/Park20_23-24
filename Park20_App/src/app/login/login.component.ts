import { Component, OnDestroy } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastEmitter } from '../_services/toast-emitter.service';
import { Subscription } from 'rxjs';
import { LoginService } from '../_services/login.service';
import { AuthService } from '../_services/authentication.service';
import { ErrorResponse } from '../_dtos/ErrorResponse.dto';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { GetCustomerLoggedService } from '../_services/get-customer-logged.service';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'component-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnDestroy{

  private subscriptions: Subscription[] = [];

  userForm: FormGroup;

  constructor(
    private te: ToastEmitter,
    private loginServ: LoginService,
    private authServ: AuthService,
    private router: Router,
    private getLoggedCustomer: GetCustomerLoggedService,
    private translate: TranslateService,
  ) {
    this.userForm = new FormGroup({
      username: new FormControl('', [Validators.required,]),
      password: new FormControl('', [Validators.required,]),
    })
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  submit() {
    if(this.userForm.invalid) {
      this.te.emitWarning('Missing field');
    } else {
      const loginSubscription = this.loginServ.login(this.userForm.get('username').value,this.userForm.get('password').value).subscribe(
        (response) => {
          const token = response.token;
          this.authServ.setToken(token);
          this.te.emitNotification("LoginSuccessful");

          this.getLoggedCustomer.get().subscribe(
            (response) => {
              this.translate.use(response.language);
              this.router.navigate(['/home']);
            },
            (error : HttpErrorResponse) => {
              const err : ErrorResponse = error.error;
              this.te.emitError(err.message);
            }
          )
        },
        (error : HttpErrorResponse) => {
          const err : ErrorResponse = error.error;
          this.te.emitError(err.message);
        }
      );

      // Store the subscription in the array
      this.subscriptions.push(loginSubscription);
    }
  }
}

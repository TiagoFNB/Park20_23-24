import { HttpErrorResponse } from "@angular/common/http";
import { Component } from "@angular/core";
import { Router } from "@angular/router";
import { TranslateService } from "@ngx-translate/core";
import { ErrorResponse } from "src/app/_dtos/ErrorResponse.dto";
import { AuthService } from "src/app/_services/authentication.service";
import { ChangeLanguageService } from "src/app/_services/change-language.service";
import { ToastEmitter } from "src/app/_services/toast-emitter.service";

@Component({
  selector: 'component-language-button',
  templateUrl: './language-button.component.html',
  styleUrls: ['./language-button.component.css']
})
export class LanguageButtonComponent {

  constructor(
    private authServ: AuthService,
    private translate: TranslateService,
    private langServ: ChangeLanguageService,
    private te: ToastEmitter,
    private router: Router) {
  }

  changeLanguage(lang : string){
    if(this.authServ.getToken()) {
      const loginSubscription = this.langServ.change({language: lang}).subscribe(
        (response) => {
          this.te.emitNotification("LanguageChanged");
          this.translate.use(lang);

        },
        (error : HttpErrorResponse) => {
          if(error.status === 403) {
            this.authServ.clearToken();
            this.router.navigate(["/home"]);
          }
          const err : ErrorResponse = error.error;
          this.te.emitError(err.message);
        });
    } else {
      this.te.emitNotification("LanguageChanged");
      this.translate.use(lang);
    }

  }
}

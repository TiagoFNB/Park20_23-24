import { APP_INITIALIZER } from "@angular/core";
import { AuthService } from "./_services/authentication.service";
import { GetManagerLoggedService } from "./_services/get-manager-logged.service";
import { HttpErrorResponse } from "@angular/common/http";
import { TranslateService } from "@ngx-translate/core";
import { Router } from "@angular/router";

export function appInitializer(translate: TranslateService,getManagerLoggedService: GetManagerLoggedService, authServ: AuthService, router: Router) {
  return () =>  getManagerLoggedService.get().subscribe((response) => {
    translate.use(response.language);},
  (error : HttpErrorResponse) => {
    if(error.status === 403) {
      authServ.clearToken();
      router.navigate(['/login']);
    }
  });
}

export const AppInitializerProvider = {
  provide: APP_INITIALIZER,
  useFactory: appInitializer,
  deps: [TranslateService, GetManagerLoggedService, AuthService, Router],
  multi: true,
};

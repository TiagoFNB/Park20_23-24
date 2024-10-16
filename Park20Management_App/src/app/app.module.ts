import { NgModule, isDevMode } from '@angular/core';
import { ServiceWorkerModule } from '@angular/service-worker';
import { TranslateModule, TranslateLoader, TranslateService } from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import { HTTP_INTERCEPTORS, HttpClient } from '@angular/common/http';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { ExampleComponent } from './example/example.component';
import { AuthInterceptor } from './_interceptors/authentication.interceptor';
import { AuthGuard } from './_guards/authentication.guard';
import { LoginComponent } from './login/login.component';
import { AppInitializerProvider } from './app.initializer';
import { PriceTableDialog } from './price-table/price-table-dialog/price-table-dialog.component';
import { PriceTableComponent } from './price-table/price-table.component';

import { MatNativeDateModule } from '@angular/material/core';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { SharedModule } from './shared/shared.module';
import { ManagersComponent } from './managers/managers.component';
import { ManagerComponent } from './managers/manager/manager.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NgChartsModule } from 'ng2-charts';
import { RankCustomersComponent } from './rank-customers/rank-customers.component';
import { ParkComponent } from './parks/park/park.component';
import { ParksComponent } from './parks/parks.component';
import { ParkyWalletComponent } from './parky-wallet/parky-wallet.component';


export function createTranslateLoader(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    ExampleComponent,
    PriceTableDialog,
    PriceTableComponent,
    ManagersComponent,
    ManagerComponent,
    DashboardComponent,
    RankCustomersComponent,
    ParksComponent,
    ParkComponent,
    ParkyWalletComponent,
  ],
  imports: [
   
    ServiceWorkerModule.register('ngsw-worker.js', {
      enabled: !isDevMode(),
      // Register the ServiceWorker as soon as the application is stable
      // or after 30 seconds (whichever comes first).
      registrationStrategy: 'registerWhenStable:30000'
    }),
    TranslateModule.forRoot({
      defaultLanguage: 'en',
      loader: {
        provide: TranslateLoader,
        useFactory: createTranslateLoader,
        deps: [HttpClient]
      }
    }),    
    MatNativeDateModule,
    MatDatepickerModule,
    SharedModule,
    NgChartsModule


    
    
    
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
    AuthGuard,
    AppInitializerProvider
  ],
  bootstrap: [AppComponent]
})

export class AppModule {
  constructor(translate: TranslateService) {
    // this language will be used as a fallback when a translation isn't found in the current language
    translate.setDefaultLang('en');
    translate.use('en');
  }
}

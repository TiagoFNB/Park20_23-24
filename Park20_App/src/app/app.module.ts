import { NgModule, isDevMode } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatButtonModule } from '@angular/material/button';
import {MatListModule} from '@angular/material/list';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatIconModule} from '@angular/material/icon';
import {MatToolbarModule} from '@angular/material/toolbar';
import { ServiceWorkerModule } from '@angular/service-worker';
import { TranslateModule, TranslateLoader, TranslateService } from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import { HTTP_INTERCEPTORS, HttpClient } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { ExampleComponent } from './example/example.component';
import { AuthInterceptor } from './_interceptors/authentication.interceptor';
import { AuthGuard } from './_guards/authentication.guard';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { RegisterComponent } from './register/register.component';
import { RegisterDataComponent } from './register/register-data/register-data.component';
import { RegisterVehiclesComponent } from './register/register-vehicles/register-vehicles.component';
import {MatTableModule} from '@angular/material/table';
import { TextCollumnComponent } from './shared/text-collumn/text-collumn.component';
import {MatDialogModule} from '@angular/material/dialog';
import { VehicleDialog } from './vehicle/vehicle-dialog/vehicle-dialog.component';
import { RegisterPaymentsComponent } from './register/register-payments/register-payments.component';
import { RegisterTermsComponent } from './register/register-terms/register-terms.component';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { VehicleComponent } from './vehicle/vehicle.component';
import { PaymentMethodDialog } from './paymentMethod/paymentMethod-dialog/paymentMethod-dialog.component';
import { PaymentMethodComponent } from './paymentMethod/paymentMethod.component';
import { LanguageButtonComponent } from './shared/language-button/language-button.component';
import { MatSelectModule } from '@angular/material/select';
import {MatMenuModule} from '@angular/material/menu';
import { AppInitializerProvider } from './app.initializer';
import { ShowListParkComponent } from './showListPark/showListPark.component';
import { ParkyWalletComponent } from './parkyWallet/parkyWallet.component';

export function createTranslateLoader(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    ExampleComponent,
    RegisterDataComponent,
    RegisterVehiclesComponent,
    TextCollumnComponent,
    VehicleDialog,
    RegisterPaymentsComponent,
    PaymentMethodComponent,
    PaymentMethodDialog,
    RegisterTermsComponent,
    VehicleComponent,
    ShowListParkComponent,
    LanguageButtonComponent,
    ParkyWalletComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    NgbModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatToolbarModule,
    MatListModule,
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
    MatSnackBarModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatTableModule,
    MatDialogModule,
    MatCheckboxModule,
    MatSelectModule,
    MatMenuModule,
    TranslateModule.forRoot({
      defaultLanguage: 'en',
      loader: {
        provide: TranslateLoader,
        useFactory: createTranslateLoader,
        deps: [HttpClient]
      }
    }),
    ServiceWorkerModule.register('ngsw-worker.js', {
      enabled: !isDevMode(),
      // Register the ServiceWorker as soon as the application is stable
      // or after 30 seconds (whichever comes first).
      registrationStrategy: 'registerWhenStable:30000'
    })
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

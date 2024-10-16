import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LanguageButtonComponent } from './language-button/language-button.component';
import { TextCollumnComponent } from './text-collumn/text-collumn.component';
import { NgxMatDatetimePickerModule, NgxMatNativeDateModule } from '@angular-material-components/datetime-picker';
import { NgxMatTimepickerModule } from 'ngx-mat-timepicker';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { MatSelectModule } from '@angular/material/select';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from '../app-routing.module';



@NgModule({
 imports:      [ CommonModule,
    FormsModule,
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
   
    NgxMatDatetimePickerModule,
    NgxMatNativeDateModule,
    NgxMatTimepickerModule, ],
 declarations: [ TextCollumnComponent,
    LanguageButtonComponent, ],
 exports:      [  TextCollumnComponent,
                 LanguageButtonComponent,
                 CommonModule, 
                 FormsModule,
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
                
                 NgxMatDatetimePickerModule,
                 NgxMatNativeDateModule,
                 NgxMatTimepickerModule, ]
})
export class SharedModule { }
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AuthGuard } from './_guards/authentication.guard';
import { LoginComponent } from './login/login.component';
import { ManagerComponent } from './managers/manager/manager.component';
import { ParkComponent } from './parks/park/park.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard],
    children: [
      { path: 'manager/:id', component: ManagerComponent },
      { path: 'park/:id', component: ParkComponent },
      // Add other nested routes as needed
      { path: '', redirectTo: 'default', pathMatch: 'full' } // Default route inside HomeComponent
    ]
  },
  { path: 'login', component: LoginComponent },
  { path: '**', redirectTo: '/home', pathMatch: 'full' },
  // Other routes...
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

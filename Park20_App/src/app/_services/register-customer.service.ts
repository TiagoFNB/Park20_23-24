import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppConfig } from '../app.config';
import { LoginResponse } from '../_dtos/LoginResponse.dto';
import { CustomerDTO } from '../_dtos/Customer.dto';

@Injectable({
  providedIn: 'root'
})
export class RegisterCustomerService {
  constructor(private http: HttpClient) { }

  register(body: CustomerDTO): Observable<LoginResponse> {
    // Define the login endpoint
    const url = `${AppConfig.apiUrl}:9001/register`;

    return this.http.post<LoginResponse>(url, body);
  }
}

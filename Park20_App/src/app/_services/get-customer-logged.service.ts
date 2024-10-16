import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppConfig } from '../app.config';
import { CustomerDTO } from '../_dtos/Customer.dto';

@Injectable({
  providedIn: 'root'
})
export class GetCustomerLoggedService {
  constructor(private http: HttpClient) { }

  get(): Observable<CustomerDTO> {
    // Define the login endpoint
    const url = `${AppConfig.apiUrl}:9001/customer-logged`;

    const params = new HttpParams();

    return this.http.get<CustomerDTO>(url,{params});
  }
}

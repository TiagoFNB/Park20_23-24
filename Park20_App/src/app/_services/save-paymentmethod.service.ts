import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppConfig } from '../app.config';
import { PaymentMethodListDTO } from '../_dtos/PaymentMethodList.dto';

@Injectable({
  providedIn: 'root'
})
export class SavePaymentMethodListService {
  constructor(private http: HttpClient) { }

  save(body: PaymentMethodListDTO): Observable<void> {
    // Define the login endpoint
    const url = `${AppConfig.apiUrl}:9001/paymentMethod`;

    return this.http.post<void>(url, body);
  }
}

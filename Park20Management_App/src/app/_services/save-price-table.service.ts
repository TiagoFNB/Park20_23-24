import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppConfig } from '../app.config';
import { PriceTableDTO } from '../_dtos/PriceTable.dto';

@Injectable({
  providedIn: 'root'
})
export class SavePriceTableService {
  constructor(private http: HttpClient) { }

  save(body: PriceTableDTO): Observable<void> {
    // Define the price table register endpoint
    const url = `${AppConfig.apiUrl}:9002/register-pricetable`;

    return this.http.post<void>(url, body);
  }
}

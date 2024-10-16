import { AppConfig } from '../app.config';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PriceTableDTO } from '../_dtos/PriceTable.dto';


@Injectable({
  providedIn: 'root'
})
export class GetParkPriceTablesService {
  constructor(private http: HttpClient) { }

  getParkPriceTables(parkId: string): Observable<PriceTableDTO[]> {
    // Define the park endpoint
    const url = `${AppConfig.apiUrl}:9002/price-tables`;

    const params = new HttpParams()
    .set('parkId', parkId)


    return this.http.get<PriceTableDTO[]>(url,{params});
  }
}
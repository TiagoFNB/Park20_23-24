import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppConfig } from '../app.config';
import { VehicleListDTO } from '../_dtos/VehicleList.dto';

@Injectable({
  providedIn: 'root'
})
export class SaveVehicleListService {
  constructor(private http: HttpClient) { }

  save(body: VehicleListDTO): Observable<void> {
    // Define the login endpoint
    const url = `${AppConfig.apiUrl}:9001/vehicle`;

    return this.http.post<void>(url, body);
  }
}

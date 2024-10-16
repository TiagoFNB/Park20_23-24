import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppConfig } from '../app.config';
import { VehicleDTO } from '../_dtos/Vehicle.dto';

@Injectable({
  providedIn: 'root'
})
export class DetectVehicleCharacteristicsService {
  constructor(private http: HttpClient) { }

  detect(licensePlate: string): Observable<VehicleDTO> {
    // Define the login endpoint
    const url = `${AppConfig.apiUrl}:9001/vehicle-detect`;

    const params = new HttpParams()
    .set('licensePlate', licensePlate);

    return this.http.get<VehicleDTO>(url,{params});
  }
}

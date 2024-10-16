import { AppConfig } from '../app.config';
import { VehicleDTO } from '../_dtos/Vehicle.dto';
import { Injectable } from '@angular/core';
import { ParkWithDistanceDTO } from '../_dtos/ParkWithDistance.dto';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ShowListParkService {
  constructor(private http: HttpClient) { }

  getParks(latitude: string, longitude: string): Observable<ParkWithDistanceDTO[]> {
    // Define the login endpoint
    const url = `${AppConfig.apiUrl}:9002/showNearbyParks`;

    const params = new HttpParams()
      .set('latitude', latitude)
      .set('longitude',longitude);

    return this.http.get<ParkWithDistanceDTO[]>(url,{params});
  }
}

import { AppConfig } from '../app.config';
import { Injectable } from '@angular/core';
import { ParkWithDistanceDTO } from '../_dtos/ParkWithDistance.dto';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import {ParkWithSpotsAndParkyDTO} from "../_dtos/ParkWithSpotsAndParky.dto";

@Injectable({
  providedIn: 'root'
})
export class SaveParkService {
  constructor(private http: HttpClient) { }

  save(body: ParkWithSpotsAndParkyDTO): Observable<void> {
    // Define the login endpoint
    const url = `${AppConfig.apiUrl}:9002/parkConfig`;

    return this.http.post<void>(url, body);
  }
}

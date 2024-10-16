import { AppConfig } from '../app.config';
import { Injectable } from '@angular/core';
import { ParkWithDistanceDTO } from '../_dtos/ParkWithDistance.dto';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import {ParkWithSpotsAndParkyDTO} from "../_dtos/ParkWithSpotsAndParky.dto";

@Injectable({
  providedIn: 'root'
})
export class ListParksService {
  constructor(private http: HttpClient) { }

  get(): Observable<ParkWithDistanceDTO[]> {
    // Define the login endpoint
    const url = `${AppConfig.apiUrl}:9002/parks`;
    return this.http.get<ParkWithDistanceDTO[]>(url,{});
  }

  getPark(): Observable<ParkWithSpotsAndParkyDTO> {
    // Define the login endpoint
    const url = `${AppConfig.apiUrl}:9002/park`;
    const params = new HttpParams();
    return this.http.get<ParkWithSpotsAndParkyDTO>(url,{params});
  }
}

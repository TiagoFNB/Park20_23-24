import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ParkWithDistanceDTO } from "../_dtos/ParkWithDistance.dto";
import { AppConfig } from "../app.config";

@Injectable({
  providedIn: 'root'
})
export class GetParksByManagerService {
  constructor(private http: HttpClient) { }

  get(username): Observable<String[]> {
    // Define the login endpoint
    const url = `${AppConfig.apiUrl}:9006/manager-parks`;

    const params = new HttpParams()
    .set('username', username)

    return this.http.get<String[]>(url,{params});
  }

  getNoParams(): Observable<ParkWithDistanceDTO[]> {
    // Define the login endpoint
    const url = `${AppConfig.apiUrl}:9002/parks-manager`;



    return this.http.get<ParkWithDistanceDTO[]>(url);
  }
}

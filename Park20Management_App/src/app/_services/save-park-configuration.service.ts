import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ParkWithSpotsAndParkyDTO } from "../_dtos/ParkWithSpotsAndParky.dto";
import { AppConfig } from "../app.config";

@Injectable({
  providedIn: 'root'
})
export class SaveParkConfigurationService {
  constructor(private http: HttpClient) { }

  save(body: ParkWithSpotsAndParkyDTO): Observable<void> {
    // Define the login endpoint
    const url = `${AppConfig.apiUrl}:9002/parkConfig`;
    return this.http.post<void>(url, body);
  }
}

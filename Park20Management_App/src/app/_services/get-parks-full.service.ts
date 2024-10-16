import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { AppConfig } from "../app.config";

@Injectable({
  providedIn: 'root'
})
export class GetParksFullService {
  constructor(private http: HttpClient) { }

  get(idPark): Observable<any> {
    // Define the login endpoint
    const url = `${AppConfig.apiUrl}:9002/park`;

    const params = new HttpParams()
    .set('id', idPark)

    return this.http.get<any>(url,{params});
  }
}

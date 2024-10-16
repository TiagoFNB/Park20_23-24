import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs/internal/Observable";
import { AppConfig } from "../app.config";

@Injectable({
  providedIn: 'root'
})
export class BulkAssignCoinsService {
  constructor(private http: HttpClient) { }

  assign(amount: number): Observable<void> {
    // Define the endpoint
    const url = `${AppConfig.apiUrl}:9001/coins-assign`;

    const body = {amount: amount}

    return this.http.post<void>(url, body);
  }
}

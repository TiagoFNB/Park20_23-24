import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs/internal/Observable";
import { AppConfig } from "../app.config";

@Injectable({
  providedIn: 'root'
})
export class AddParksToManagerService {
  constructor(private http: HttpClient) { }

  change(userId, parks : string[]): Observable<void> {
    // Define the login endpoint
    const url = `${AppConfig.apiUrl}:9006/manager-park`;

    const body = {id: userId, parks: parks}

    return this.http.post<void>(url, body);
  }
}

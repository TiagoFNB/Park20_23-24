import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { AppConfig } from "../app.config";
import { StayDTO } from "../_dtos/Stay.dto";

@Injectable({
  providedIn: 'root'
})
export class GetStaysService {
  constructor(private http: HttpClient) { }

  get(): Observable<StayDTO[]> {
    // Define the login endpoint
    const url = `${AppConfig.apiUrl}:9001/stays`;
    return this.http.get<StayDTO[]>(url,{});
  }
}

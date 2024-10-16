import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs/internal/Observable";
import { AppConfig } from "../app.config";
import { CustomerUsageRankDTO } from "../_dtos/CustomerUsageRank.dto";

@Injectable({
  providedIn: 'root'
})
export class RankCustomersUsageService {
  constructor(private http: HttpClient) { }

  get(parks : string[]): Observable<CustomerUsageRankDTO[]> {
    // Define the login endpoint
    const url = `${AppConfig.apiUrl}:9001/rank-customers-usage`;
    
    let params = new HttpParams();
    params = params.appendAll({'parks': parks});

    return this.http.get<CustomerUsageRankDTO[]>(url,{params} );
  }
}

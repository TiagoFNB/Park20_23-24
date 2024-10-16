import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppConfig } from '../app.config';
import { ManagerDTO } from '../_dtos/Manager.dto';

@Injectable({
  providedIn: 'root'
})
export class GetManagerLoggedService {
  constructor(private http: HttpClient) { }

  get(): Observable<ManagerDTO> {
    // Define the login endpoint
    const url = `${AppConfig.apiUrl}:9006/manager-logged`;

    const params = new HttpParams();

    return this.http.get<ManagerDTO>(url,{params});
  }
}

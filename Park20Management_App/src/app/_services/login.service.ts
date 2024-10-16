import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppConfig } from '../app.config';
import { LoginResponse } from '../_dtos/LoginResponse.dto';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<LoginResponse> {
    // Define the login endpoint
    const loginUrl = `${AppConfig.apiUrl}:9009/login-manager`;

    // Prepare the request body with user credentials
    const body = {
      username: username,
      password: password
    };

    return this.http.post<LoginResponse>(loginUrl, body);
  }
}

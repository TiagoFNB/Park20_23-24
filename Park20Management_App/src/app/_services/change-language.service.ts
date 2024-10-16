import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppConfig } from '../app.config';
import { ChangeLanguageDTO } from '../_dtos/ChangeLanguage.dto';

@Injectable({
  providedIn: 'root'
})
export class ChangeLanguageService {
  constructor(private http: HttpClient) { }

  change(body: ChangeLanguageDTO): Observable<void> {
    // Define the login endpoint
    const url = `${AppConfig.apiUrl}:9006/language`;

    return this.http.post<void>(url, body);
  }
}

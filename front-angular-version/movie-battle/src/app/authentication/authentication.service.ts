import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from "rxjs";
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private httpClient: HttpClient) { }

  authenticate(userName: string, userPassword: string): Observable<any> {
    return this.httpClient.post(`${environment.backend}auth`, {
      name: userName,
      password: userPassword,
    });
  }
}


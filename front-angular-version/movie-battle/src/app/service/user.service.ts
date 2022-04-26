import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }

  get(): Observable<User[]> {
    return this.httpClient.get<User[]>(`${environment.backend}ranking`);
  }

  singup(userName: string, userPassword: string): Observable<any> {
    return this.httpClient.post(`${environment.backend}singup`, {
      name: userName,
      password: userPassword,
    });
  }
}

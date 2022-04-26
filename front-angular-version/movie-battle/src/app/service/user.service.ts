import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TokenService } from '../authentication/token.service';
import { User } from '../model/user';
import jwt_decode from 'jwt-decode'

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient, private tokenService: TokenService) { 
    if(this.tokenService.hasToken()){
      this.decoderJWT()
    }
  }

  private userSubject = new BehaviorSubject<User>({});

  private decoderJWT(){
    const token = this.tokenService.getToken();
    const user = jwt_decode(token) as User;
  }

  returnUser(){
    this.userSubject.asObservable();
  }

  saveToken(token : string){
    this.tokenService.saveToken(token);
    this.decoderJWT()
  }

  logout(){
    this.tokenService.removeToken();
    this.userSubject.next({});
  }

  isLogged(){
    return this.tokenService.hasToken();
  }

  singup(userName: string, userPassword: string): Observable<any> {
    return this.httpClient.post(`${environment.backend}user`, {
      name: userName,
      password: userPassword,
    });
  }
}

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { JwtHelperService } from '@auth0/angular-jwt';
import { map } from 'rxjs/operators';

import { EnvConfigService } from './env-config.service';
import { UserForLogin } from './../models/user-for-login';
import { UserForRegister } from './../models/user-for-register';
import { UserLoginResponse } from './../models/user-login-response';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  baseUrl: string;
  jwtHelper = new JwtHelperService();
  decodedToken: any;

  constructor(private envConfigService: EnvConfigService,
              private http: HttpClient) {

                this.envConfigService.getApiUrls$().subscribe((apiUrl: string) => {
                  this.baseUrl = apiUrl + 'auth/';
                });
              }

  login(userForLogin: UserForLogin) {
    return this.http.post(this.baseUrl + 'login', userForLogin)
      .pipe(
        map((user: UserLoginResponse) => {

          if (user) {
            localStorage.setItem('token', user.token);
            this.decodedToken = this.jwtHelper.decodeToken(user.token);
          }
        })
      );
  }

  register(model: UserForRegister) {
    return this.http.post(this.baseUrl + 'register', model);
  }

  loggedIn(): boolean {
    const token = localStorage.getItem('token');
    return !this.jwtHelper.isTokenExpired(token);
  }
}

import { Component, OnInit } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';

import { AuthService } from './services/auth.service';
import { EnvConfigService } from './services/env-config.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  jwtHelper = new JwtHelperService();
  title = 'Heartcooking';

  constructor(private envConfigService: EnvConfigService,
              private authService: AuthService) {

                this.envConfigService.init();
              }

  ngOnInit(): void {
    const token = localStorage.getItem('token');
    if (token) {
      this.authService.decodedToken = this.jwtHelper.decodeToken(token);
    }
  }
}

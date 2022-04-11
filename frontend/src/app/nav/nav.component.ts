import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { UserForLogin } from './../models/user-for-login';
import { AlertifyService } from './../services/alertify.service';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent implements OnInit {

  userForLogin: UserForLogin = {};
  wasFailedLogin = false;

  constructor(private alertifyService: AlertifyService,
              public authService: AuthService,
              private router: Router) { }

  ngOnInit() {
  }

  isFailedLoginInfoToShow(): boolean {
    return this.wasFailedLogin && !this.loggedIn();
  }

  login() {

    this.authService.login(this.userForLogin).subscribe(next => {
      this.alertifyService.success('Zalogowano pomyślnie');
      this.router.navigate(['/home']);
      this.wasFailedLogin = true;
    }, error => {
      this.wasFailedLogin = true;
      this.alertifyService.error(error);
    });
  }

  loggedIn(): boolean {
    return this.authService.loggedIn();
  }

  logout() {
    localStorage.removeItem('token');
    this.alertifyService.message('Pomyślnie wylogowano');
    this.router.navigate(['/home']);
  }
}

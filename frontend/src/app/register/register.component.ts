import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { UserForRegister } from './../models/user-for-register';
import { AlertifyService } from './../services/alertify.service';
import { AuthService } from './../services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  model: UserForRegister = {};

  constructor(private alertifyService: AlertifyService,
              private authService: AuthService,
              private router: Router) { }

  ngOnInit() {
  }

  register() {
    this.authService.register(this.model).subscribe(() => {
      this.alertifyService.success('Pomyślnie zarejestrowano');
      this.router.navigate(['/home']);
    }, error => {
      this.alertifyService.error(error);
    });
  }

}

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { lastValueFrom } from 'rxjs';
import { AuthenticationService } from 'src/app/authentication/authentication.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user = '';
  password = '';

  constructor(private userService: UserService, private authService: AuthenticationService, private router: Router) { }

  ngOnInit(): void {
  }

  login(){
    this.authService.authenticate(this.user, this.password)
    .subscribe(
      (r) => {
        this.userService.saveToken(r.token)
        this.router.navigate(['game']);
      },
      (error : any) => {
        alert('invalid user or password');
        console.log(error);
      }
    );
  }

}

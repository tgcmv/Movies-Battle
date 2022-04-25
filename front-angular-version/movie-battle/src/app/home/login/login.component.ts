import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { lastValueFrom } from 'rxjs';
import { AuthenticationService } from 'src/app/authentication/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user = '';
  password = '';

  constructor(private authService: AuthenticationService, private router: Router) { }

  ngOnInit(): void {
  }

  login(){
    this.authService.authenticate(this.user, this.password)
    .subscribe(
      () => {
        this.router.navigate(['game']);
      },
      (error : any) => {
        alert('invalid user or password');
        console.log(error);
      }
    );
  }

}

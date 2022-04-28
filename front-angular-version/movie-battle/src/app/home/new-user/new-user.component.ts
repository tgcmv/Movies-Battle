import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/authentication/authentication.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-new-user',
  templateUrl: './new-user.component.html',
  styleUrls: ['./new-user.component.css']
})
export class NewUserComponent implements OnInit {
  
  user = '';
  password = '';

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  createUser(){
    this.userService.singup(this.user, this.password)
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

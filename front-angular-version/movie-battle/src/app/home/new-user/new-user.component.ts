import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/authentication/authentication.service';

@Component({
  selector: 'app-new-user',
  templateUrl: './new-user.component.html',
  styleUrls: ['./new-user.component.css']
})
export class NewUserComponent implements OnInit {
  
  user = '';
  password = '';

  constructor(private authService: AuthenticationService) { }

  ngOnInit(): void {
  }

  createUser(){
    // this.authService.authenticate(this.user, this.password)
    // .subscribe(
    //   () => {
    //     this.router.navigate(['game']);
    //   },
    //   (error : any) => {
    //     alert('invalid user or password');
    //     console.log(error);
    //   }
    // );
  }
}

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home.component';
import { HomeRoutingModule } from './home-routing.module';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { MessageModule } from '../components/message/message.module';
import { NewUserComponent } from './new-user/new-user.component';



@NgModule({
  declarations: [
    HomeComponent,
    LoginComponent,
    NewUserComponent
  ],
  imports: [
    CommonModule, HomeRoutingModule, FormsModule, MessageModule
  ]
})
export class HomeModule { }

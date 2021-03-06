import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { lastValueFrom } from 'rxjs';
import { Movie } from '../model/movie';
import { GameService } from '../service/game.service';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {
  
  constructor(private gameService : GameService, private router: Router) { }

  ngOnInit(): void {}

  startGame(){
    this.gameService.start()
    .subscribe(
      (r) => {
        this.router.navigate(['play']);
      },
      (error : any) => {
        alert('Error');
        console.log(error);
      }
    );
  }

}



import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { lastValueFrom } from 'rxjs';
import { Movie } from 'src/app/model/movie';
import { GameService } from 'src/app/service/game.service';
import { Game } from '../../model/game';

@Component({
  selector: 'app-play',
  templateUrl: './play.component.html',
  styleUrls: ['./play.component.css']
})
export class PlayComponent implements OnInit {
  
  game : Game | undefined;

  constructor(private gameService : GameService, private router: Router) { }

  movies: Movie[] = [];

  ngOnInit(): void {
    lastValueFrom(this.gameService.nextMovies())
        .then(data => this.movies = data!);
    
  }


  movieSelect(imdbID : string){
    
      this.gameService.hit(imdbID)
      .subscribe(
        (game) => {
          this.game = game;
          lastValueFrom(this.gameService.nextMovies())
          .then(data => this.movies = data!);

          if(game.gameOver){
            alert("GAME OVER")
            this.router.navigate(['game']);
          }
          

        },
        (error : any) => {
          alert('Error');
          console.log(error);
        }
      );
  }

}

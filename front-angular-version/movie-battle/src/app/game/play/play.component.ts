import { Component, OnInit } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { Movie } from 'src/app/model/movie';
import { GameService } from 'src/app/service/game.service';

@Component({
  selector: 'app-play',
  templateUrl: './play.component.html',
  styleUrls: ['./play.component.css']
})
export class PlayComponent implements OnInit {
  
  constructor(private gameService : GameService) { }

  movies: Movie[] = [];

  ngOnInit(): void {
    lastValueFrom(this.gameService.nextMovies())
        .then(data => this.movies = data!);
    
  }


  movieSelect(imdbID : string){
    console.log(imdbID)
  }

}

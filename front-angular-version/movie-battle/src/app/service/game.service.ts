import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Game } from '../model/game';
import { Movie } from '../model/movie';

@Injectable({
  providedIn: 'root'
})
export class GameService {

    constructor(private httpClient: HttpClient) { }
  
    nextMovies(): Observable<Movie[]> {
      return this.httpClient.get<Movie[]>(`${environment.backend}game/next-round`);
    }

    hit(imdbid: string): Observable<Game> {
      return this.httpClient.get<Game>(`${environment.backend}game/hit?imdbid=${imdbid}`,);
    }

    start(){
      return this.httpClient.get<any>(`${environment.backend}game`);
    }
  }
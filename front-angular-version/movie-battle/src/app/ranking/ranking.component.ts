import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Ranking } from "../../model/Ranking";
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-ranking',
  templateUrl: './ranking.component.html',
  styleUrls: ['./ranking.component.css']
})
export class RankingComponent implements OnInit {

  constructor(private httpClient: HttpClient) { }

  rankings: Ranking[] = [];

  ngOnInit(): void {
    this.get().toPromise().then(data => this.rankings = data!);
  }

  get(): Observable<Ranking[]> {
    return this.httpClient.get<Ranking[]>(`http://localhost:8080/public/ranking`);
}

}

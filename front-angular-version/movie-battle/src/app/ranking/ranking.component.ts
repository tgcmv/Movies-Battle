import { Component, OnInit } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { Ranking } from "../model/Ranking";
import { RankingService } from '../service/ranking.service';

@Component({
  selector: 'app-ranking',
  templateUrl: './ranking.component.html',
  styleUrls: ['./ranking.component.css']
})
export class RankingComponent implements OnInit {

  constructor(private rankingService : RankingService) { }

  rankings: Ranking[] = [];

  ngOnInit(): void {
    lastValueFrom(this.rankingService.get())
        .then(data => this.rankings = data!);
    
  }

}

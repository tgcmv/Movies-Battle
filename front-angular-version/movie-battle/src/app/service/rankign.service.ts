import { Ranking } from "../../model/Ranking";
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";

@Injectable()
export class RankingService {

    constructor(private httpClient: HttpClient) { }

    get(): Observable<Ranking[]> {
        return this.httpClient.get<Ranking[]>(`${environment.backend}products`);
    }
}
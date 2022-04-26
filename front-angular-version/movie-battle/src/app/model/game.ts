import { Movie } from "./movie"

export interface Game {
    points:number,
    wrongs:number,
    isRightHit:boolean,
    gameOver:boolean,
    movieA:Movie,
    movieB:Movie,
}


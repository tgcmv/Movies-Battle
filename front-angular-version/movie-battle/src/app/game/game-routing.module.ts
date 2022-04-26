import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GameComponent } from './game.component';
import { PlayComponent } from './play/play.component';

const routes: Routes = [
  {
    path: '',
    component:GameComponent,
  },
  {
    path: 'pĺay',
    component: PlayComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GameRoutingModule { }

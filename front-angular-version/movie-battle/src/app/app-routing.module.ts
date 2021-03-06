import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    pathMatch:'full',
    redirectTo: 'home'
  },
  {
    path: 'home',
    loadChildren:() => import('./home/home.module').then((m)=>m.HomeModule),
  },
  {
    path: 'game',
    loadChildren:() => import('./game/game.module').then((m)=>m.GameModule),
  },
  {
    path: 'play',
    loadChildren:() => import('./game/play/play.module').then((m)=>m.PlayModule),
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

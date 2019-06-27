import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MainComponent } from './main/main.component';
import { FilmComponent } from './film/film.component';

const routes: Routes = [
  {path: 'film/:id', component: FilmComponent},
  { path: '', component: MainComponent },
  { path: 'main', redirectTo: '', pathMatch: 'full' },
  { path: '**', component: MainComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }

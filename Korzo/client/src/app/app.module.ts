import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { FilmComponent } from './film/film.component';
import { FilmListComponent } from './film-list/film-list.component';

@NgModule({
  declarations: [
    AppComponent,
    FilmComponent,
    FilmListComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

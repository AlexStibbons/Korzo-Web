import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';


import { MainComponent } from './main/main.component';
import { FilmComponent } from './film/film.component';
import { AddFilmComponent } from './add-film/add-film.component';
import { EditFilmComponent } from './edit-film/edit-film.component';
import { GenreService } from './services/genre.service';
import { FilmService } from './services/film.service';

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    FilmComponent,
    AddFilmComponent,
    EditFilmComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
    FilmService,
    GenreService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

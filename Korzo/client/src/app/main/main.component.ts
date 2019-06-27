import { Component, OnInit } from '@angular/core';
import { FilmService } from '../services/film.service';
import { GenreService } from '../services/genre.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  page: any;

  titleSearch: string;


  constructor(private filmService: FilmService,
              private genreService: GenreService,
              private router: Router) {

                this.titleSearch = '';
               }

  ngOnInit() {
    this.getFilms()
  }

  getFilms(){
    this.filmService.getFilms(0, this.titleSearch).subscribe(
      (res: any) => {
        this.page = res;
      }
    );
  }

  prev(){
    this.filmService.getFilms(this.page.number - 1, this.titleSearch).subscribe(
      (res: any) => {
        this.page = res;
      }
    );
  }

  next(){
    this.filmService.getFilms(this.page.number + 1, this.titleSearch).subscribe(
      (res: any) => {
        this.page = res;
      }
    );
  }

  deleteFilm(id: number) {
    this.filmService.deleteFilm(id).subscribe(
      () => {
        this.getFilms();
      }
    );
  }

  details(id: number) {
    this.router.navigate([`/film/${id}`]);
  }

}

import { Component, OnInit } from '@angular/core';
import { FilmService } from '../services/film.service';
import { GenreService } from '../services/genre.service';
import { Router } from '@angular/router';
import { Genre } from '../common.models';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  page: any; // NB: in case there is no genreId, the get request return a PAGE
            // in case there is a genreId, the get request returns a PAGELISTHOLDER
            // since they have different attributes, the html tables must be different

  // search helpers
  titleSearch: string;
  genres: Genre[];
  genreId: number;


  constructor(private filmService: FilmService,
              private genreService: GenreService,
              private router: Router) {

                this.titleSearch = '';
                this.genreId = -1;
               }

  ngOnInit() {
    this.getFilms()
    this.getGenres();
  }

  getGenres(){
    this.genreService.getGenres().subscribe(
      (res: Genre[]) => {
        this.genres = res;
      }
    );
  }

  getFilms() {
    // this if condition could have been done on the backend side
    if (this.genreId > - 1) {
      this.filmService.getSearch(0, this.titleSearch, this.genreId).subscribe(
        (res: any) => {
          this.page = res;
        }
      );

    } else {
      this.filmService.getFilms(0, this.titleSearch).subscribe(
        (res: any) => {
          this.page = res;
        }
      );
    }
  }

  prev() {

    if (this.genreId > - 1) {
      this.filmService.getSearch(this.page.page - 1, this.titleSearch, this.genreId).subscribe(
        (res: any) => {
          this.page = res;
        }
      );
    } else {
      this.filmService.getFilms(this.page.number - 1, this.titleSearch).subscribe(
        (res: any) => {
          this.page = res;
        }
      );
    }

  }

  next() {
    if (this.genreId > - 1) {
      this.filmService.getSearch(this.page.page + 1, this.titleSearch, this.genreId).subscribe(
        (res: any) => {
          this.page = res;
        }
      );
    } else {
      this.filmService.getFilms(this.page.number + 1, this.titleSearch).subscribe(
        (res: any) => {
          this.page = res;
        }
      );
    }
  }

  lastPage() {
    if (this.genreId > - 1) {
      this.filmService.getSearch(this.page.pageCount, this.titleSearch, this.genreId).subscribe(
        (res: any) => {
          this.page = res;
        }
      );
    } else {
      this.filmService.getFilms(this.page.totalPages - 1, this.titleSearch).subscribe(
        (res: any) => {
          this.page = res;
        }
      );
    }
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

  clearSearch() {
    this.titleSearch = '';
    this.genreId = -1;
    this.getFilms();
  }

  addFilm() {
    this.router.navigate(['/new']);
  }
}

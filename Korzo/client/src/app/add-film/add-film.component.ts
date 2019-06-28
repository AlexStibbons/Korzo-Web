import { Component, OnInit } from '@angular/core';
import { Film, AddFilm } from '../common.models';
import { ActivatedRoute, Router } from '@angular/router';
import { FilmService } from '../services/film.service';

@Component({
  selector: 'app-add-film',
  templateUrl: './add-film.component.html',
  styleUrls: ['./add-film.component.css']
})
export class AddFilmComponent implements OnInit {


  film: AddFilm; // needs a special class because there is a special addFilm dto in backend
  id: number;


  constructor(private router: Router,
              private filmService: FilmService) { }

  ngOnInit() {
    this.film = {
      title: '',
      storage: '',
      year: 0,
      genreIds: [],
      domestic: false
    }
  }

  addFilm() {
    this.filmService.addFilm(this.film).subscribe(
      (res: Film) => {
        this.router.navigate([`/film/${res.id}`]);
      }
    );
  }

}

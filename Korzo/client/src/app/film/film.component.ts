import { Component, OnInit } from '@angular/core';
import { FilmService } from '../services/film.service';
import { Film } from '../common.models';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-film',
  templateUrl: './film.component.html',
  styleUrls: ['./film.component.css']
})
export class FilmComponent implements OnInit {


  id: number;
  private sub: any;
  film: Film;

  constructor(private filmService: FilmService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.getFilm();
  }

  getFilm(){
    this.sub = this.route.params.subscribe(
      params => {
        this.id = params['id'];
        this.filmService.getFilm(this.id).subscribe(
          (res: Film) => {
            this.film = res;
          }
        );
      }
    );
  }

}

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Film } from '../common.models';

@Injectable({
  providedIn: 'root'
})
export class FilmService {

  constructor(private http: HttpClient) { }

  getFilms(page: number, title?: string): Observable<any> {
    return this.http.get<any>(`/api/films/title?q=${title}&page=${page}&size=3`);
  }

  getFilm(id: number): Observable<Film> {
    return this.http.get<Film>(`/api/films/${id}`);
  }

  getSearch(page: number, title: string, genreId: number): Observable<any> {
    return this.http.get<any>(`/api/search/${genreId}?title=${title}&page=${page}&size=3`);
  }

  addFilm(film: Film): Observable<Film> {
    let headers = new HttpHeaders({ 'Content-Type': 'application/json'});
    return this.http.post<Film>('/api/films/new', JSON.stringify(film), {headers});
  }

  editFilm(film: Film, filmId: number): Observable<Film> {
    let headers = new HttpHeaders({ 'Content-Type' : 'application/json'});
    return this.http.put<Film>(`api/films/edit/${filmId}`, JSON.stringify(film), {headers});
  }

  deleteFilm(id:number): Observable<void> {
    return this.http.delete<void>(`/api/film/${id}`);
  }

  // depricated function
  getFilmsByGenre(page: number, genreId: number): Observable<any> {
    return this.http.get<any>(`/api/films/genre/${genreId}?page=${page}&size=3`);
  }

}

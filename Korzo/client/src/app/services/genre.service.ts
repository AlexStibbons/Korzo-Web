import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Genre } from '../common.models';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class GenreService {

  constructor(private http: HttpClient) { }

  getGenres(): Observable<Genre[]> {
    return this.http.get<Genre[]>('/api/genres');
  }

  getGenre(id: number): Observable<Genre>{
    return this.http.get<Genre>(`/api/genre/${id}`);
  }
}

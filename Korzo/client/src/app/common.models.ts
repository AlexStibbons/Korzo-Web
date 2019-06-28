interface FilmInterface {
  id?: number;
  title: string;
  year?: number;
  storage: string;
  domestic?: boolean;
  genres?: Genre[];
}

export class Film implements FilmInterface {
  public id?: number;
  public title: string;
  public year?: number;
  public storage: string;
  public domestic?: boolean;
  public genres?: Genre[];

  constructor(spec: FilmInterface) {
    this.id = spec.id;
    this.title = spec.title;
    this.year = spec.year;
    this.storage = spec.storage;
    this.domestic = spec.domestic;
    this.genres = spec.genres;
  }
}

interface GenreInterface {
  id?: number;
  genre: string;
}

export class Genre implements GenreInterface {
  public id?: number;
  public genre: string;

  constructor(spec: GenreInterface) {
    this.id = spec.id;
    this.genre = spec.genre;
  }
}

interface AddFilmIntr {
  title: string;
  year?: number;
  domestic?: boolean;
  storage: string;
  genreIds?: number[];
}

export class AddFilm implements AddFilmIntr {

  public title: string;
  public year?: number;
  public domestic?: boolean;
  public storage: string;
  public genreIds?: number[];

  constructor(spec: AddFilmIntr) {
    this.title = spec.title;
    this.year = spec.year;
    this.domestic = spec.domestic;
    this.storage = spec.storage;
    this.genreIds = spec.genreIds;
  }
}

interface FilmInterface {
  id?: number;
  title: string;
  year?: number;
  storage: string;
  isDomestic?: boolean;
  genres?: Genre[];
}

export class Film implements FilmInterface {
  public id?: number;
  public title: string;
  public year?: number;
  public storage: string;
  public isDomestic?: boolean;
  public genres?: Genre[];

  constructor(spec: FilmInterface) {
    this.id = spec.id;
    this.title = spec.title;
    this.year = spec.year;
    this.storage = spec.storage;
    this.isDomestic = spec.isDomestic;
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

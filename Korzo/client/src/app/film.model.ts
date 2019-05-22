export interface FilmInterface {

  id: number,
  title: string,
  year: number,
  domestic: boolean,
  storage: string,
  genres: GenreInterface[]

}

export interface GenreInterface {

  id: number,
  genre: string

}

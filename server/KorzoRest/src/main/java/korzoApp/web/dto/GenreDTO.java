package korzoApp.web.dto;

import java.util.List;

import korzoApp.model.FilmGenre;
import korzoApp.model.Genre;

public class GenreDTO {
	
	private long id;
	
	private String genre;
	
	private List<FilmDTO> films;

	public GenreDTO() {
		super();
	}
	
	public GenreDTO(Genre genre) {
		this.id = genre.getId();
		this.genre = genre.getGenre();
	}
	
	public GenreDTO(FilmGenre filmGenre) {
		this.id = filmGenre.getGenre().getId();
		this.genre = filmGenre.getGenre().getGenre();
	}

	public GenreDTO(String genre) {
		super();
		this.genre = genre;
	}
	
	public GenreDTO(long id, String genre) {
		super();
		this.id = id;
		this.genre = genre;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public List<FilmDTO> getFilms() {
		return films;
	}

	public void setFilms(List<FilmDTO> films) {
		this.films = films;
	}
	
	

}

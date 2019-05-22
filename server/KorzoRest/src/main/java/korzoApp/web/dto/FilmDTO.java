package korzoApp.web.dto;

import java.util.List;

import korzoApp.model.Film;
import korzoApp.model.FilmGenre;

public class FilmDTO implements Comparable<FilmDTO> {
	
	private long id;
	
	private String title;
	
	private int year;
	
	private boolean domestic;
	
	private String storage;
	
	private List<GenreDTO> genres;
	
	public FilmDTO(String title, int year, boolean domestic, String storage) {
		super();
		this.title = title;
		this.year = year;
		this.domestic = domestic;
		this.storage = storage;
	}

	public FilmDTO(long id, String title, int year, boolean domestic, String storage) {
		super();
		this.id = id;
		this.title = title;
		this.year = year;
		this.domestic = domestic;
		this.storage = storage;
	}

	public FilmDTO() {
		super();
	}
	
	public FilmDTO(Film film) {
		this.id = film.getId();
		this.title = film.getTitle();
		this.year = film.getYear();
		this.domestic = film.isDomestic();
		this.storage = film.getStorage();
	}
	
	public FilmDTO(FilmGenre filmGenre) {
		this.id = filmGenre.getFilm().getId();
		this.title = filmGenre.getFilm().getTitle();
		this.year = filmGenre.getFilm().getYear();
		this.domestic = filmGenre.getFilm().isDomestic();
		this.storage = filmGenre.getFilm().getStorage();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public boolean isDomestic() {
		return domestic;
	}

	public void setDomestic(boolean domestic) {
		this.domestic = domestic;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public List<GenreDTO> getGenres() {
		return genres;
	}

	public void setGenres(List<GenreDTO> genres) {
		this.genres = genres;
	}

	@Override
	public int compareTo(FilmDTO arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	

}

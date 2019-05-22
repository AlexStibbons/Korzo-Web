package korzoApp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import korzoApp.data.FilmGenreRepository;
import korzoApp.data.FilmRepository;
import korzoApp.model.Film;
import korzoApp.model.FilmGenre;

@Component
public class FilmService {
	
	@Autowired
	FilmRepository filmRepository;
	
	@Autowired
	FilmGenreRepository filmGenreRepository;
	
	public Film save(Film film) {
		return filmRepository.save(film);
	}
	
	public void delete(Film film) {
		filmRepository.delete(film);
	}
	
	public List<Film> findAll() {
		return filmRepository.findAll(); // ORDER ALPHABETICALLY
	}
	
	public Page<Film> findAll(Pageable page) {
		return filmRepository.findAll(page);
	}
	
	
	public List<Film> findByTitle(String title) {
		return filmRepository.findByTitleContainsIgnoreCase(title);
	}
	
	public List<Film> findByYear(int year) {
		return filmRepository.findByYear(year);
	}
	
	public List<Film> findByYearRage(int greater, int less) {
		return filmRepository.findByYearGreaterThanAndYearLessThan(greater, less);
	}
	
	public Film findById(long id) {
		return filmRepository.findById(id).get();
	}
	
	public List<FilmGenre> findByGenre(long genreId) {
		
		List<FilmGenre> found = filmGenreRepository.findAll().stream()
								.filter(f -> f.getGenre().getId() == genreId)
								.collect(Collectors.toList());
		
		return found;
	}

}

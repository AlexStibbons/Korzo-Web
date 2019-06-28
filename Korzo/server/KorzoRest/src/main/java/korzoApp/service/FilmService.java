package korzoApp.service;

import java.util.ArrayList;
import java.util.List;

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
	
	public Page<Film> findAllOrdered(Pageable page) {
		return filmRepository.findAllByOrderByTitleAsc(page);
	}
	
	public Page<Film> findTitleOrdered(String title, Pageable page) {
		return filmRepository.findByTitleContainsIgnoreCaseOrderByTitleAsc(title, page);
	}

	
	public Page<Film> findByYear(int year, Pageable page) {
		return filmRepository.findByYear(year, page);
	}
	
	public Page<Film> findByYearRage(int greater, int less, Pageable page) {
		return filmRepository.findByYearGreaterThanAndYearLessThan(greater, less, page);
	}
	
	public Film findById(long id) {
		return filmRepository.findById(id).get();
	}
	
	
	public Page<FilmGenre> findByGenrePage(long genreId, Pageable page) {
		
		Page<FilmGenre> found = filmGenreRepository.findByGenreIdOrderByFilmTitleAsc(genreId, page);
		
		return found;
	}
	
	public List<Film> findByTitleList(String title) {
		return filmRepository.findByTitleContainsIgnoreCase(title);
	}
	
	public List<Film> findByGenreList(Long id) {
		List<FilmGenre> filmgenre = filmGenreRepository.findByGenreId(id);
		
		List<Film> films = new ArrayList<>();
		
		filmgenre.stream()
				.forEach(fg -> films.add(fg.getFilm()));
		
		return films;
							
	}
 
}

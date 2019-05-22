package korzoApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import korzoApp.data.FilmGenreRepository;
import korzoApp.model.FilmGenre;

@Component
public class FilmGenreService {
	
	@Autowired
	FilmGenreRepository filmGenreRepository;
	
	public List<FilmGenre> findAll() {
		return filmGenreRepository.findAll();
	}
	
	public FilmGenre save(FilmGenre filmGenre) {
		return filmGenreRepository.save(filmGenre);
	}
	
	public void delete(FilmGenre filmGenre) {
		filmGenreRepository.delete(filmGenre);
	}

}

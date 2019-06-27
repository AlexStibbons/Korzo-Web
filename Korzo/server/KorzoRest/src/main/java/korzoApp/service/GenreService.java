package korzoApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import korzoApp.data.GenreRepository;
import korzoApp.model.Genre;

@Component
public class GenreService {

	@Autowired
	GenreRepository genreRepository;
	
	public List<Genre> findAll(){
		return genreRepository.findAll();
	}
	
	public Genre findById(long id) {
		return genreRepository.findById(id).get();
	}
	
	public Genre save(Genre genre) {
		return genreRepository.save(genre);
	}
	
	public void delete(Genre genre) {
		genreRepository.delete(genre);
	}
	 
}

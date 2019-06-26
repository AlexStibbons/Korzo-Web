package korzoApp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import korzoApp.model.Genre;
import korzoApp.service.FilmGenreService;
import korzoApp.service.GenreService;
import korzoApp.web.dto.GenreDTO;

@RestController
public class GenreController {

	@Autowired
	GenreService genreService;
	
	@Autowired
	FilmGenreService filmGenreService;
	
	@DeleteMapping("api/genre/{genreId}")
	public ResponseEntity<Void> deleteGenre(@PathVariable long genreId) {
		
		// find the genre
		Genre toDel = genreService.findById(genreId);
		
		// remove genre from the table connecting genre and films
		filmGenreService.findAll().stream()
		.forEach(fg -> {
			if (fg.getGenre().getId() == toDel.getId()) {
				filmGenreService.delete(fg);
			}
		});
		
		// finally, remove genre itself
		genreService.delete(toDel);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

	@PostMapping("api/genre/new")
	public ResponseEntity<GenreDTO> addGenre(@RequestBody GenreDTO newGenre) {
		
		Genre addGenre = new Genre();
		addGenre.setGenre(newGenre.getGenre());
		addGenre = genreService.save(addGenre);
		
		return new ResponseEntity<>(new GenreDTO(addGenre), HttpStatus.CREATED);
	}

}

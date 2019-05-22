package korzoApp.web.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import korzoApp.model.Film;
import korzoApp.model.FilmGenre;
import korzoApp.model.Genre;
import korzoApp.service.FilmGenreService;
import korzoApp.service.FilmService;
import korzoApp.service.GenreService;
import korzoApp.web.dto.AddFilmDTO;
import korzoApp.web.dto.FilmDTO;
import korzoApp.web.dto.GenreDTO;

@RestController
public class FilmController {

	@Autowired
	FilmService filmService;
	
	@Autowired
	FilmGenreService filmGenreService;
	
	@Autowired
	GenreService genreService;
	
	/*TODO || test in postman:
		! change genres when changing film [???]
		? delete a film [X] 
		? delete a genre [X]
		! change a genre [] */
	
	// the search will be a search form, so all of these 
	//individual searches/get methods will be basically useless
	
	private void findFilmGenre(FilmDTO filmDto) { // add another parameter - the List<FilmGenre> which is
													// made in the http requests themselves
		
		List<FilmGenre> filmgenre = filmGenreService.findAll(); // i don't want to create
																// a new list for every dto, though
		List<GenreDTO> dtos = new ArrayList<>();
		
		for (FilmGenre film : filmgenre) { // try to lambda stream this
			if (film.getFilm().getId() == filmDto.getId()) {
				GenreDTO dto = new GenreDTO(film); // collect dtos to list 
				dtos.add(dto); // setGenres to filmDto after the stream is done
			}
		}
		
		filmDto.setGenres(dtos);
	}
	
	@GetMapping("api/films")
	public ResponseEntity<List<FilmDTO>> findAll() {
		
		List<FilmDTO> films = filmService.findAll().stream()
								.map(FilmDTO::new)
								.collect(Collectors.toList());
		
		films.stream()
			.forEach(this::findFilmGenre);

		
		return new ResponseEntity<>(films, HttpStatus.OK);
	}
	
	@GetMapping("api/films/genre/{genreId}")
	public ResponseEntity<List<FilmDTO>> findByGenre(@PathVariable long genreId) {
		
		List<FilmDTO> films = filmService.findByGenre(genreId).stream()
								.map(FilmDTO::new)
								.collect(Collectors.toList());
		
		return new ResponseEntity<>(films, HttpStatus.OK);
	}
	
	@GetMapping("api/films/title")
	public ResponseEntity<List<FilmDTO>> findFilmByTitle(@RequestParam String query) {
		
		List<FilmDTO> dtos = filmService.findByTitle(query).stream()
							.map(FilmDTO::new)
							.collect(Collectors.toList());
		
		dtos.stream()
			.forEach(this::findFilmGenre);
		
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("api/films/year")
	public ResponseEntity<List<FilmDTO>> findFilmByYear(@RequestParam int query) {
		
		List<FilmDTO> dtos = filmService.findByYear(query).stream()
							.map(FilmDTO::new)
							.collect(Collectors.toList());
		
		dtos.stream()
			.forEach(this::findFilmGenre);
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("api/films/period")
	public ResponseEntity<List<FilmDTO>> findFilmsByYearRange(@RequestParam int from, 
															@RequestParam int to) {
		
		List<FilmDTO> dtos = filmService.findByYearRage(to, from).stream()
							.map(FilmDTO::new)
							.collect(Collectors.toList());
		
		dtos.stream()
			.forEach(this::findFilmGenre);
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
		
	}
	
	@GetMapping("api/films/{filmId}")
	public ResponseEntity<FilmDTO> findById(@PathVariable long filmId) {
		
		Film found = filmService.findById(filmId);
		
		FilmDTO dto = new FilmDTO(found);
		
		findFilmGenre(dto); // attaches object Genre, but what if I want only the genre name to be displayed?
				
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@PostMapping("api/films/new")
	public ResponseEntity<String> addFilm(@RequestBody AddFilmDTO newFilm) {
		
		Set<FilmGenre> genres = new HashSet<>();
		
		Film film = new Film();
		film.setTitle(newFilm.getTitle());
		film.setYear(newFilm.getYear());
		film.setDomestic(newFilm.isDomestic());
		film.setStorage(newFilm.getStorage());
		film = filmService.save(film);
				
		if (!newFilm.getGenreIds().isEmpty()) {
			for (int genreId : newFilm.getGenreIds()) {
				Genre addGenre = genreService.findById(genreId);
				
				FilmGenre filmGenre = new FilmGenre();
				filmGenre.setFilm(film);
				filmGenre.setGenre(addGenre);
				
				filmGenre = filmGenreService.save(filmGenre);
				
				genres.add(filmGenre);
			}
		}
		
		film.setGenres(genres);
		film = filmService.save(film);
		
		return new ResponseEntity<>("Film added", HttpStatus.CREATED);
	}
	
	@PostMapping("api/genre/new")
	public ResponseEntity<String> addGenre(@RequestBody GenreDTO newGenre) {
		
		Genre addGenre = new Genre();
		addGenre.setGenre(newGenre.getGenre());
		addGenre = genreService.save(addGenre);
		
		return new ResponseEntity<>("Genre created", HttpStatus.CREATED);
	}
	
	@PutMapping("api/films/edit/{filmId}")
	public ResponseEntity<FilmDTO> editFilm(@PathVariable long filmId,
											@RequestBody AddFilmDTO editedFilm) {
		
		Film film = filmService.findById(filmId);
		
		// you should be able to:
		// - change title,
		// - change storage,
		// - change year,
		// - change type (domestic or international),
		// - add genres, and
		// - remove genres.
		
		if (editedFilm.getTitle()!=null) {
			film.setTitle(editedFilm.getTitle());
			film = filmService.save(film);
		}
		
		if (editedFilm.getStorage()!=null) {
			film.setStorage(editedFilm.getStorage());
			film = filmService.save(film);
		}
		
		if (editedFilm.getYear() != 0) {
			film.setYear(editedFilm.getYear());
			film = filmService.save(film);
		}
		
		if (editedFilm.isDomestic()) {
			film.setDomestic(editedFilm.isDomestic());
			film = filmService.save(film);
		}
		
		FilmDTO dto = new FilmDTO(film);
		
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@DeleteMapping("api/film/{filmId}")
	public ResponseEntity<String> deleteFilmById (@PathVariable long filmId) {
		
		Film toDel = filmService.findById(filmId);
		
		filmGenreService.findAll().stream()
			.forEach(fg -> {
				if (fg.getFilm().getId() == toDel.getId()) {
					filmGenreService.delete(fg);
				}
			}); // will this work?
		
		filmService.delete(toDel); 
		
		return new ResponseEntity<>("Film deleted", HttpStatus.OK);
	}
	
	// anything related to genre so directly should be in
	// genre controller, really
	
	@DeleteMapping("api/genre/{genreId}")
	public ResponseEntity<String> deleteGenre(@PathVariable long genreId) {
		
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
		
		return new ResponseEntity<>("Genre deleted", HttpStatus.OK);
	}
	
}

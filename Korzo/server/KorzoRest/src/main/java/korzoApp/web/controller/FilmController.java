package korzoApp.web.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import korzoApp.web.dto.PageFilmDTO;

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

	
	// find all, sort alphabetically
	@GetMapping("api/films")
	public ResponseEntity<PageFilmDTO> findAll(Pageable page) {
		
		PageFilmDTO dto = new PageFilmDTO(filmService.findAllOrdered(page));
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	// find by title, sort alphabetically
	@GetMapping("api/films/title")
	public ResponseEntity<PageFilmDTO> findTitleOrdered(@RequestParam String q, Pageable page) {
		
		PageFilmDTO dto = new PageFilmDTO(filmService.findTitleOrdered(q, page));
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	// find by genre
	@GetMapping("api/films/genre/{genreId}")
	public ResponseEntity<List<FilmDTO>> findByGenre(@PathVariable long genreId) {
		
		List<FilmDTO> films = filmService.findByGenre(genreId).stream()
								.map(FilmDTO::new)
								.collect(Collectors.toList());
		
		return new ResponseEntity<>(films, HttpStatus.OK);
	}
	
	// trying to make a find by genre (above) paginated
	@GetMapping("api/test/{genreId}")
	public ResponseEntity<PageFilmDTO> findByGenreTest(@PathVariable long genreId, Pageable page) {
		
		// long way round:
		List<Film> raw = new ArrayList<>();
		
		filmService.findByGenre(genreId).stream()
			.forEach(f -> raw.add(f.getFilm()));
		
		Page<Film> found = new PageImpl<Film>(raw); // works, but DTO does not work
		PageFilmDTO dto = new PageFilmDTO(found);
		

		Page<Film> found2 = new PageImpl<Film>(raw, page, raw.size()); // page from input --> add page to service/repo
		PageFilmDTO dto2 = new PageFilmDTO(found2); 

		/*
		Page<Film> films = filmserive.find();
		List<FilmDto> filmDto = films.getContent().stream()
								.map(FilmDto::new)
								.collect(Collectors.toList());
		Page<FilmDto> dto = new PageImpl<FilmDTO>(filmDto, page, films.getTotalElements());

		frontend: 
		- does not need a model og a Page object
		- enough to say private page: any;
		- then take any data from page
		- for ex 'blahblah.subscribe(
			(res: any) => {
				this.page = res;
			});'
		*/

				
		return new ResponseEntity<>(dto2, HttpStatus.OK);
	}
	
	@GetMapping("api/films/{filmId}")
	public ResponseEntity<FilmDTO> findById(@PathVariable long filmId) {
		
		Film found = filmService.findById(filmId);
		
		if (found!=null) {
			return new ResponseEntity<>(new FilmDTO(found), HttpStatus.OK);
		}
				
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("api/films/new")
	public ResponseEntity<FilmDTO> addFilm(@RequestBody AddFilmDTO newFilm) {
		
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
		
		return new ResponseEntity<>(new FilmDTO(film), HttpStatus.CREATED);
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
		
		// ...what - all these ifs are not needed
		// fix later
		
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
	public ResponseEntity<Void> deleteFilmById (@PathVariable long filmId) {
		
		Film toDel = filmService.findById(filmId);
		
		filmGenreService.findAll().stream()
			.forEach(fg -> {
				if (fg.getFilm().getId() == toDel.getId()) {
					filmGenreService.delete(fg);
				}
			});
		
		filmService.delete(toDel); 
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// anything related to genre so directly should be in
	// genre controller, really
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
	
	// this should be in genre controller too
	@PostMapping("api/genre/new")
	public ResponseEntity<GenreDTO> addGenre(@RequestBody GenreDTO newGenre) {
		
		Genre addGenre = new Genre();
		addGenre.setGenre(newGenre.getGenre());
		addGenre = genreService.save(addGenre);
		
		return new ResponseEntity<>(new GenreDTO(addGenre), HttpStatus.CREATED);
	}
	
	
	// search by year of release and by year range
	// i decided not to use these
	
	@GetMapping("api/films/year")
	public ResponseEntity<List<FilmDTO>> findFilmByYear(@RequestParam int query) {
		
		List<FilmDTO> dtos = filmService.findByYear(query).stream()
							.map(FilmDTO::new)
							.collect(Collectors.toList());
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("api/films/period")
	public ResponseEntity<List<FilmDTO>> findFilmsByYearRange(@RequestParam int from, 
															@RequestParam int to) {
		
		List<FilmDTO> dtos = filmService.findByYearRage(to, from).stream()
							.map(FilmDTO::new)
							.collect(Collectors.toList());
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
		
	}
}

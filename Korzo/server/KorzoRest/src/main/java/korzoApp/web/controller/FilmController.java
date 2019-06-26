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

@RestController
public class FilmController {

	@Autowired
	FilmService filmService;
	
	@Autowired
	FilmGenreService filmGenreService;
	
	@Autowired
	GenreService genreService;
	
	// find all, sort alphabetically
	@GetMapping("api/films")
	public ResponseEntity<Page<FilmDTO>> findAll(Pageable page) {
		
		Page<Film> films = filmService.findAllOrdered(page);
		
		List<FilmDTO> filmDto = films.getContent().stream()
								.map(FilmDTO::new)
								.collect(Collectors.toList());
		
		Page<FilmDTO> dto = new PageImpl<FilmDTO>(filmDto, page, films.getTotalElements());
		
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	// find by title, sort alphabetically
	@GetMapping("api/films/title")
	public ResponseEntity<Page<FilmDTO>> findTitleOrdered(@RequestParam String q, Pageable page) {
		
		Page<Film> films = filmService.findTitleOrdered(q, page);
		
		List<FilmDTO> filmsDto = films.getContent().stream()
								.map(FilmDTO::new)
								.collect(Collectors.toList());
		
		Page<FilmDTO> dto = new PageImpl<FilmDTO>(filmsDto, page, films.getTotalElements());
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	// find by genre
	@GetMapping("api/films/genre/{genreId}")
	public ResponseEntity<Page<FilmDTO>> findByGenrePage(@PathVariable long genreId,
														Pageable page) {
		
		// find elements from binding table
		Page<FilmGenre> found = filmService.findByGenrePage(genreId, page);
		
		List<FilmDTO> dtos = new ArrayList<>();
		
		// extract films only from binding table
		found.getContent().stream()
			.forEach(f -> dtos.add(new FilmDTO(f.getFilm())));
		
		// the page is the same, but instead of binding table content (which would be 'filmId, genreId')
		// what is now returned is the list of films
		Page<FilmDTO> res = new PageImpl<FilmDTO>(dtos, page, found.getTotalElements());
		
		return new ResponseEntity<>(res, HttpStatus.OK);

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

		
		film.setTitle(editedFilm.getTitle());
		film.setStorage(editedFilm.getStorage());
		film.setYear(editedFilm.getYear());
		film.setDomestic(editedFilm.isDomestic());		
		film = filmService.save(film);
		
		
		// adding genres
		Set<FilmGenre> genres = new HashSet<>();
		
		if (!editedFilm.getGenreIds().isEmpty()) {
			for (int genreId : editedFilm.getGenreIds()) {
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
		
		FilmDTO dto = new FilmDTO(film);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@DeleteMapping("api/film/{filmId}")
	public ResponseEntity<Void> deleteFilmById (@PathVariable long filmId) {
		
		// find the film
		Film toDel = filmService.findById(filmId);
		
		// delete the film from filmGenre table
		filmGenreService.findAll().stream()
			.forEach(fg -> {
				if (fg.getFilm().getId() == toDel.getId()) {
					filmGenreService.delete(fg);
				}
			});
		
		// delete film itself
		filmService.delete(toDel); 
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	// search by year of release and by year range
	// i decided not to use these
	@GetMapping("api/films/year")
	public ResponseEntity<Page<FilmDTO>> findFilmByYear(@RequestParam int query, Pageable page) {
		
		Page<Film> films = filmService.findByYear(query, page);
		
		List<FilmDTO> filmsDto = films.getContent().stream()
								.map(FilmDTO::new)
								.collect(Collectors.toList());
		
		Page<FilmDTO> dtos = new PageImpl<FilmDTO>(filmsDto, page, films.getTotalElements());
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("api/films/period")
	public ResponseEntity<Page<FilmDTO>> findFilmsByYearRange(@RequestParam int from, 
															@RequestParam int to,
															Pageable page) {
		
		Page<Film> films = filmService.findByYearRage(from, to, page);
		
		List<FilmDTO> filmsDto = films.getContent().stream()
								.map(FilmDTO::new)
								.collect(Collectors.toList());
		
		Page<FilmDTO> dtos = new PageImpl<FilmDTO>(filmsDto, page, films.getTotalElements());
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
		
	}
}

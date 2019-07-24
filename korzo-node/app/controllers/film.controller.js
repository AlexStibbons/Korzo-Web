// import models
const Film = require('../models/film.model');


/* CREATING IN SPRING BOOT

@PostMapping("api/film")
public ResponseEntity<FilmDto> save(@RequestBody FilmDto film){
    Film created = new Film();

    created.setTitle(film.getTitle());
    created.setYear(film.getYear);

    // if genre is an object
    Set<Genres> genres = new HashSet<>();
    if (!film.getGenres.isEmpty()) {
        film.getGenres.stream
            .forEach(g -> genres.add(genreService.findById(g.getId)));
    }
    created.setGenres(genres);
    created = filmService.save(created);
    //etcetera

    return new ResponseEntity<>(new FilmDto(created), HttpStatus.OK);
}*/

// to create a film
exports.addFilm = (request, response) => {
    // check if there is a film at all
    if (!request.body) {
        response.status(400).send(
            { "message": "Body of film must not be null"}
        );
    }

    // if there is a body, create
    const film = new Film(
        {
            title: request.body.title || "No title",
            year: request.body.year,
            isDomestic: request.body.isDomestic,
            storage: request.body.storage || "No storage",
            genres: request.body.genres
        }
    );

    // save -- this will work because Film itself has mongoose
    film.save()
        .then( data => {
            response.send(data);
        }).catch( err => {
            response.status(500).send({
                message: err.message || "Unknown error occured."
            });
        });
}

// findAll
/*
@GetMapping("api/films")
public ResponseEntity<Page<FilmDto>> findAll(Pageable page) {
    
    Page<Film> films = filmService.findAll(page);

    List<FilmDto> filmDtos = films.getContent.stream
                            .map(FilmDto::new)
                            .collect(Collectors.toList());
    
    Page<FilmDto> pageDto = new PageImpl(filmsDtos, page.getTotalElements);
    
    // if pageImpl does not return in slices, PageListHolder must be used

    return new ResponseEntity<>(pageDto, HttpStatus.OK);
}
*/

exports.findAll = (request, response) => {
    Film.find()
        .then(films => {
            response.send(films);
        })
        .catch(err => {
            response.status(500).send({
                message: err.message || "Error while retreiving data"
            });
        });    
}

exports.findById = (request, response) => {
    Film.findById(request.params.filmId) // except there is no film id [aside from the automatic key
                                        // and that is not one single number]?
        .then(film => {
            response.send(film);
        }).catch(err => {
            response.status(500).send({
                message: err.message || "No such film error"
            });
        });
}
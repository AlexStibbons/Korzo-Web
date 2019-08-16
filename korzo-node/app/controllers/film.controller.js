// import models
const Film = require('../models/film.model');

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

    // now save to database
    film.save()
        .then( data => {
            response.send(data);
        }).catch( err => {
            response.status(500).send({
                message: err.message || "Unknown error occured."
            });
        });
}


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

exports.findByTitle = (request, response) => {
    Film.find( { title: { $regex: request.query.title, $options: 'i' } } ) // the DB query
        .then( films => { // sending the DB response onward
            response.send(films);
        })
        .catch( err => {
            response.status(500).send({
                message: err.message || "Something went wrong. Please, try again."
            });
        });
}

exports.findByGenre = (request, response) => {
    Film.find({genre: { $regex: request.query.genre, $options: 'i'} })
        .then()
        .catch(err => {
            response.status(500).send({
                message: err.message || "Something went wrong. Please, try again."
            });
        });
}

exports.updateFilm = (request, response) => {

    Film.findByIdAndUpdate(request.params.filmId, 
        {
            $set: {
                title: request.body.title,
                year: request.body.year,
                isDomestic: request.body.isDomestic,
                storage: request.body.storage,
                genres: request.body.genres
            }
        }, 
        { 
            new: true // sends the updated film in response
        } 
        )
        .then( film => {
            response.send(film);
        })
        .catch(err => {
            response.status(500).send({
                message: err.message || "Something went wrong."
            });
        })
}

exports.delete = (request, response) => {
    Film.findByIdAndDelete(request.params.filmId)
        .then( film => {
            response.send({ message: "success!"});
            }
        )
        .catch(err => {
            response.status(500).send(
                {message: "nope!"}
            );
        })
}

// TBD
exports.findByTitleAndGenre = (request, response) => {
    Film.find()
        .then(films => {
            response.send(films);
        })
        .catch(err => {
            response.status(500).send({
                message: "nope"
            });
        });
}
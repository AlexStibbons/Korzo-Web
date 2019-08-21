// import models
const Film = require('../models/film.model');

// to create a film
exports.addFilm = (req, res) => {
    // check if there is a film at all
    if (!req.body) {
        res.status(400).send(
            { "message": "Body of film must not be null"}
        );
    }

    // if there is a body, create
    const film = new Film(
        {
            title: req.body.title || "No title",
            year: req.body.year,
            isDomestic: req.body.isDomestic,
            storage: req.body.storage || "No storage",
            genres: req.body.genres
        }
    );

    // now save to database
    film.save()
        .then( data => {
            res.send(data);
        }).catch( err => {
            res.status(500).send({
                message: err.message || "Unknown error occured."
            });
        });
}


exports.findAll = (req, res) => {
    Film.find()
        .then(films => {
            res.send(films);
        })
        .catch(err => {
            res.status(500).send({
                message: err.message || "Error while retreiving data"
            });
        });    
}

exports.findById = (req, res) => {
    Film.findById(req.params.filmId) // except there is no film id [aside from the automatic key
                                        // and that is not one single number]?
        .then(film => {
            res.send(film);
        }).catch(err => {
            res.status(500).send({
                message: err.message || "No such film error"
            });
        });
}

// command works in mongo, but not here
exports.findByTitle = (req, res) => {
    console.log(req.query);
    let query = req.query.title;
    Film.find( { title: { $regex: query, $options: 'i' } } ) // the DB query
        .then( films => { // sending the DB res onward
            res.send(films);
        })
        .catch( err => {
            res.status(500).send({
                message: err.message || "Something went wrong. Please, try again."
            });
        });
}

// not sure $elemMatch is needed here
// for ex, if the query is 'drama,' the result
// should be all documents that contain 'drama' in
// their genre array
// there's no multiple criteria here
// works in mongo
exports.findByGenre = (req, res) => {
    console.log(req.query);
    let query = req.query.genre; // can't recognize req.query in mongo query? why?
    Film.find({genres: { $regex: query, $options: 'i'} })
        .then( films =>{
            res.send(films);
        })
        .catch(err => {
            res.status(500).send({
                message: err.message || "Something went wrong. Please, try again."
            });
        });
}

exports.updateFilm = (req, res) => {

    Film.findByIdAndUpdate(req.params.filmId, 
        {
            $set: {
                title: req.body.title,
                year: req.body.year,
                isDomestic: req.body.isDomestic,
                storage: req.body.storage,
                genres: req.body.genres
            }
        }, 
        { 
            new: true // sends the updated film in res
        } 
        )
        .then( film => {
            res.send(film);
        })
        .catch(err => {
            res.status(500).send({
                message: err.message || "Something went wrong."
            });
        })
}

exports.delete = (req, res) => {
    Film.findByIdAndDelete(req.params.filmId)
        .then( film => {
            res.send({ message: "success!"});
            }
        )
        .catch(err => {
            res.status(500).send(
                {message: "nope!"}
            );
        })
}

// TBD
exports.findByTitleAndGenre = (req, res) => {
    console.log(req.query);
    let title = req.query.title;
    let genre = req.query.genre;


    Film.find( {
        title: {$regex: title, $options: 'i'},
        genres: {$regex: genre, $options: 'i'}
    })
        .then(films => {
            res.send(films);
        })
        .catch(err => {
            res.status(404).send({
                message: "none such thing"
            });
        });
}
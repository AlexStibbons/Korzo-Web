module.exports = (app) => {
    // import dependency
    const filmController = require('../controllers/film.controller');

    // create
    app.post('/add', filmController.addFilm);

    // findAll
    app.get('/films', filmController.findAll);

    // findById
    app.get('/films/:filmId', filmController.findById);

    // findTitle
    app.get('/films/title', filmController.findByTitle);

    // findGenre
    app.get('/films/genre', filmController.findByGenre);

    // update
    app.put('/films/:filmId', filmController.updateFilm);

    // delete
    app.delete('/films/:filmId', filmController.delete);

    // search
    app.get('/films/search', filmController.findByTitleAndGenre);
}
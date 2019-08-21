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
    app.get('/title', filmController.findByTitle);

    // findGenre
    // same issue as search below
    app.get('/genre', filmController.findByGenre);

    // update
    app.put('/films/:filmId', filmController.updateFilm);

    // delete
    app.delete('/films/:filmId', filmController.delete);

    // search
    // '/search' works 
    // BUT
    // '/films/search' does not???
    app.get('/search', filmController.findByTitleAndGenre);
}
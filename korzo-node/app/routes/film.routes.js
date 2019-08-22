const express = require('express');
const filmController = require('../controllers/film.controller');
const router = express.Router();

router.get('/', filmController.findAll);

router.get('/title', filmController.findByTitle);

router.get('/genre', filmController.findByGenre);

router.get('/search', filmController.findByTitleAndGenre);

router.get('/random', filmController.randomFilm);

router.post('/new', filmController.addFilm);

router.route('/:filmId')
        .get(filmController.findById)
        .put(filmController.updateFilm)
        .delete(filmController.deleteFilm);

module.exports = router;

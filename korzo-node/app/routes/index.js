const express = require('express');
const filmRoutes = require('./film.routes');

const router = express.Router();

router.use('/films', filmRoutes);

module.exports = router;
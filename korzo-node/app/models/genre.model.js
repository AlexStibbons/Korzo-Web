const mongoose = require('mongoose');

const GenreSchema = mongoose.Schema (
    {
        name: String
    }
);

module.exports = mongoose.model('Genre', GenreSchema);
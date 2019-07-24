// import dependency
const mongoose = require('mongoose');

// the model is
const FilmSchema = mongoose.Schema(
    {
        title: {type: String, lowercase: true, required: true},
        year: Number,
        isDomestic: Boolean,
        storage: {type: String, lowercase: true, required: true},
        genres: [ {type: String, lowercase: true} ] // add reference to genre here
    },
    {
        timestamps: true
    }
); 

module.exports = mongoose.model('Film', FilmSchema);
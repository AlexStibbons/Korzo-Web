// import dependency
const mongoose = require('mongoose');

// the model is
const FilmSchema = mongoose.Schema(
    {
        title: {type: String, required: true},
        year: Number,
        isDomestic: Boolean,
        storage: {type: String, required: true},
        genres: [String] // later, add reference to genre here 
    },
    {
        timestamps: true
    }
); 

module.exports = mongoose.model('Film', FilmSchema);

// so
// if I understand this correctly, mongoose does something similar
// to Hibernate/Spring Data JPA?
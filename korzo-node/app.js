// import dependencies
const express = require('express');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');
const config = require('./config/config');

const app = express();

// parsing requests
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

// connect to database
mongoose.Promise = global.Promise;

mongoose.connect( config.localDB, {
    useNewUrlParser: true
}).then( () => {
    console.log('Connected to database');
}).catch( err => {
    console.log('No conection. Exiting', err);
    process.exit();
});

// set up routing
const routes = require('./app/routes');
app.use('/api', routes);

// set the port
app.listen(config.serverport, () => {
    console.log('Listening port 3000');
});
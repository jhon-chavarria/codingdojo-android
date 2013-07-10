var mongoose = require('mongoose');
var config = require('../config');

var db = mongoose.createConnection( config.mongodb.url );

exports.data = require("./data").setup( mongoose, db );
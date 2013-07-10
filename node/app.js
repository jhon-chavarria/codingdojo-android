/*
 * Required modules
 **/
var express = require('express')
    , mongoose = require('mongoose')
    , stylus = require('stylus')
    , nib = require('nib')
    , controllers = require('./controllers')
    , config = require('./config')
    , models = require('./models')
    , db = mongoose.createConnection( config.mongodb.url );


function compile(str, path) {
    return stylus(str)
        .set('filename', path)
        .use(nib())
}

var app = express();
/*
 * Express settings
 **/
app.set('views', __dirname + '/views');
app.set('view engine', 'jade');
app.use(express.bodyParser());
app.use(express.cookieParser());
app.use(express.session({ secret: '023197422617bce43335cbd3c675aeed' }));
app.use(express.logger('dev'));
app.use(stylus.middleware(
    { src: __dirname + '/public'
        , compile: compile
    }
));
app.use(express.static(__dirname + '/public'));

/*
 * Setup Pages
 **/
controllers.pages.setup(models, config);

// Page route
app.get("/",controllers.pages.home);

var server = require('http').createServer(app)
var WebSocket = require("socket.io").listen( server );
WebSocket.set('log level', 0);

/*
 * Setup Api
 **/
controllers.api.setup(models, WebSocket);

WebSocket.sockets.on("connection", controllers.api.webSocketStart);

/*
 * Routes from API
 **/
app.post("/api/post",controllers.api.post);
app.get("/api/get",controllers.api.get);
app.get("/api/demo",controllers.api.demo);

/*
 * Server port listen
 **/
server.listen(80);
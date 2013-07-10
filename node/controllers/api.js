var request = require('request');
var models
    , websocket
    , string = require('string')
    , moment = require('moment');

moment.lang("es");
exports.setup = function( _models, _websocket ){
    models = _models;
    websocket = _websocket;
};

function webSocketSendData (data){
    data.message = string(decodeURIComponent(data.message.replace(/\+/g, '%20'))).stripTags().s;
    data.registered = new Date();

    var row = new models.data;
    row.name = data.name;
    row.email = data.email;
    row.message = data.message;
    row.registered = new Date();
    row.save(function(err){
        if(err){
            console.log(err);
        }
        else{
            websocket.sockets.emit("ws_getData",data);
        }
    });
}


exports.webSocketStart = function (data){
    function out(){
        data.on("new_message", webSocketSendData);
    }
    return out();
}


exports.post = function(req, res){
    function out(  ){
        var obj = {};
        obj.name = req.body.name;
        obj.email = req.body.email;
        obj.message = req.body.message;
        webSocketSendData(obj);
        res.send('true');
    }
    return out( );
}


exports.get = function(req, res){
    function out(  ){
        models.data.find({ },{},{ limit: 20, sort:{ registered: -1 } }, function(err,rows){
            if( !err ){
                if( rows ){
                    var items = new Array();
                    rows.forEach(function(item){
                        var objItem = {};
                        objItem.id = item._id;
                        objItem.name = item.name;
                        objItem.email = item.email;
                        objItem.message = item.message;
                        objItem.registered = item.registered; //moment(item.registered).fromNow();
                        items.push(objItem);
                    });
                    res.send( JSON.stringify( items ) );
                }
                else{
                    res.send( err );
                }
            }
        });
    }
    return out( );
}


exports.demo = function(req, res){
    function out(){
        request.post(
            'http://test.clov3r.net/api/post',
            { form: { name: 'demo', email: 'demo@demo.com', message: 'Hola mundo' } },
            function (error, response, body) {
                if (!error && response.statusCode == 200) {
                    console.log(body);
                    res.send('{"status":true}');
                }
                else{
                    res.send('{"status":false,"error":"'+error+'"}');
                }
            }
        );

    }
    return out();
}

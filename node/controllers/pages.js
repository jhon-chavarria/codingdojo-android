var models
    , config
    , string = require('string')
    , moment = require('moment');

moment.lang("es");
exports.setup = function (_models, _config) {
    models = _models;
    config = _config;
}

exports.home = function (req, res) {
    function out() {
        models.data.find({ }, {}, { limit: 20, sort: { registered: -1 } }, function (err, rows) {
            var data = {};
            data.title = "Coding Dojo Android";
            data.socket = config.socket;
            if (rows) {
                var items = new Array();
                rows.forEach(function (item) {
                    var objItem = {};
                    objItem.id = item._id;
                    objItem.name = item.name;
                    objItem.email = item.email;
                    objItem.message = item.message;
                    objItem.registered = item.registered; //moment(item.registered).fromNow();
                    items.push(objItem);
                });
                data.items = items;
            }
            else {
                data.items = [];
            }
            res.render('home', {data: data});
        });
    }

    return out();
}

exports.streaming = function (req, res) {
    function out() {
        res.send('<iframe width="420" height="315" src="http://www.youtube.com/embed/vRdUqGelMVA" frameborder="0" allowfullscreen></iframe>');
    }

    return out();
}

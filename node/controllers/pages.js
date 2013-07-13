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

    function render (data) {
        // url
        data.message = data.message.replace(/(https?:\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&:\/~\+#]*[\w\-\@?^=%&\/~\+#])?)/g, '<a href="$1" title="$1" target="_blank">$1</a>');
        // mensiones
        data.message = data.message.replace(/\B@([\w-_]+)/ig, '<a href="http://twitter.com/$1" title="@$1 en twitter" target="_blank">@$1</a>');
        // hashtag
        data.message = data.message.replace(/\B#([\w-_]+)/ig, '<a href="https://twitter.com/search/%23$1" title="#$1" target="_blank">#$1</a>')

        return data;
    }

    return out();
}

exports.streaming = function (req, res) {
    function out() {
        res.send('<iframe width="420" height="315" src="http://www.youtube.com/embed/vRdUqGelMVA" frameborder="0" allowfullscreen></iframe>');
    }

    return out();
}

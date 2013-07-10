var times = [];

var custom = {};

custom.add = function(obj){
    var span = $('<div></div>').addClass('span6').prependTo($(".my")).fadeIn('slow');
    var widget = $('<div></div>').addClass('widget').attr("id",obj._id).appendTo(span);

    var widget_body =  $('<div></div>').addClass('widget-body').append($('<p></p>').text(obj.message)).prependTo(widget);

    var widget_title = $('<div></div>').addClass('widget-title').append($('<h4></h4>').text(obj.name)).append($('<span></span>').addClass('tools').append($('<a></a>').text(moment(obj.registered).fromNow()).attr('href','JavaScript:void(0);'))).prependTo(widget);
    times.unshift(obj.registered);
}

custom.time = {};

custom.time.save = function(){
    $(".tools a").each(function() {
        var time = $(this).html();
        times.push(time);
    });
}

custom.time.set = function(){
    var a = $(".tools a");
    a.each(function(index, item) {
        var time = times[index];
        var new_time = moment(time).fromNow();
        $(this).html(new_time);
    });
    console.log('set called');
    console.log(a.length + '-' + times.length);
}

custom.time.action = function(){
    custom.time.set();
}
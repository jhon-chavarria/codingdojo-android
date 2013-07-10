var mongoose;
var db;
exports.setup = function(_mongoose,_db){
    mongoose = _mongoose;
    db = _db;


    var schema = mongoose.Schema({
        name: String,
        email: String,
        message: String,
        registered: { type: Date, default: Date.now }
    });
    var model = db.model('data', schema);
    var Data = db.model('data');
    return Data;
};
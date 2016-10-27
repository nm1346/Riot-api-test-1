var express = require('express'),
	http = require('http'),
    path = require("path"),
    fs = require('fs');
var app = express();
var server = http.createServer(app).listen(5000, function () {
	console.log('server on !');
	console.log(__dirname);
});
app.use('resources' ,express.static(__dirname + "/views/resources"));

app.get('*',function (req , res ) {
	console.log(__dirname);
	fs.readFile('views/index.html', function (error , data) {
		
		res.writeHead(200 ,{'content-type' : 'text/html; charset="utf-8"'});
		res.end(data);
	});
});

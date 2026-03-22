const http = require('http');
http.createServer((req, res) => {
  res.writeHead(200, {'Content-Type': 'text/html'});
  res.end('<h1>Ung dung React dang chay!</h1>');
}).listen(3000);
console.log('App is running on port 3000');
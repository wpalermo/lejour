'use strict'

//console.log('Iniciando desafio silhuetas');

const silhuetas = require('./app/silhuetas/desafioSilhuetas.js');

var params = process.argv.splice(2);
console.log(params[0]);
console.log(params[1]);


silhuetas.run(params[0], params[1]);


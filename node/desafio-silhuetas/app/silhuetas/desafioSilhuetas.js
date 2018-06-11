'use strict'

const fileRead = require('../fileServices/fileRead.js')

exports.run = function(){

    fileRead.readFile(desafio);

};


var desafio = function(data){

    console.log('Iniciando processamento das silhuetas' + data.length );

    var linhas = [];
    const qtdLinhas = data.shift();
    console.log('HEADER do arquivo: ' + qtdLinhas );
    console.log('Quantidade de casos: ' + data.length/2);

    if(data.length/2 != qtdLinhas)
        console.log('WARN - quantidade de linhas do arquivo diferente da quantidade passada no HEADER');
    else
        console.log('INFO - Quantidade de linhas OK');


}
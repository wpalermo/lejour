'use strict'

const fs = require('fs');
const readline = require('readline');
const FILE_PATH = './resources/';
const FILE_NAME = 'caso1.txt';


const rl = readline.createInterface({
    input: fs.createReadStream(FILE_PATH + FILE_NAME),
    crlfDelay: Infinity
});

let values = [];

exports.readFile = function read(callback){
   
    console.log('Iniciando leitura do arquivo ' + FILE_NAME);

    
    rl.on('line', (line) => {
        values.push(line);
    }).on('close', () => { 
        console.log('Arquivo lido ' + FILE_NAME)
        console.log('Quantidade de linhas encontradas: ' + values.length);

        //Realiza o callback para funcao que continua o processo
        //TODO: criar validacao se callback eh uma funcao ou nao.
        callback(values);
    });

})};




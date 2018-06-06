'use strict'

console.log('Inicio');

const fs = require('fs');
const readline = require('readline');
const sync = require('sync');
const FILE_PATH = './resources/';
const FILE_NAME = 'caso1.txt';

const rl = readline.createInterface({
    input: fs.createReadStream(FILE_PATH + FILE_NAME),
    crlfDelay: Infinity
});

var values = [];
 rl.on('line', (line) => {
        console.log('rl.on');
        values.push(toString(line));
        console.log('rl.off');
    });




console.log(values);
values.forEach(function(item, index, array){
    console.log(index, item);
});


console.log('Fim');

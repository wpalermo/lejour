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

exports.readFile = function read(){
   
    return new Promise((resolve, reject) => {
        rl.on('line', (line) => {
            values.push(line);
        }).on('close', () => { 
            console.log('Arquivo lido')
            resolve(values);
        });

    return getList();
})};

function toList(line){
    values.push(line);
}

function getList(){
    console.log('getList');
    console.log(values.length);
    return values;
}


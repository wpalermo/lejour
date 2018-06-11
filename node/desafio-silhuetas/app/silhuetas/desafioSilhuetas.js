'use strict'

const fileRead = require('../fileServices/fileRead.js')

exports.run = function(){

    fileRead.readFile(desafio);

};


var desafio = function(data){

    console.log('Iniciando processamento das silhuetas' + data.length );

    var returnable = [];
    const casos = [];
    
    const qtdLinhas = data.shift();
    console.log('HEADER do arquivo: ' + qtdLinhas );
    console.log('Quantidade de casos: ' + data.length/2);

    if(data.length/2 != qtdLinhas)
        console.log('WARN - quantidade de linhas do arquivo diferente da quantidade passada no HEADER');
    else
        console.log('INFO - Quantidade de linhas OK');


    data.forEach((element, index) => {

        
        console.log(index);

        if(index %2 == 1){
            let linhas = [];
            casos.push(element.split(' '));
        }

    });

    casos.forEach((linha, indexLinha) => {

        let valor;
        let valorParcial;

        element.forEach((element, index) => {

            valor = element;
            var indexMaior = await buscarIndexMaior(linha, index);

            if(indexMaior == null){
                if(index + 1 == linha.length)
                    break;

                var indexSegundoMaior = await buscarSegundoMaior(linha, index);
                valor = linha.get(indexSegundoMaior);

                if(indexSegundoMaior == (index + 1))
                    continue;
                else{
                    linha.forEach((element) => {
                        valorParcial = valorParcial + (valor - element);
                    });
                }

            }else if(indexMaior == (index+1)){
                continue;
            }else{
                linha.slice(index +1, indexMaior).forEach((element) => {
                    valorParcial = valorParcial + (valor - element);
                })

                index = indexMaior - 1;
            }

            returnable.push(valorParcial);
            

        });

    });
    

}


function buscarIndexMaior(lista, i){

    let numero = lista.get(i);

    lista.forEach((element, index) => {
        if(element > numero && index < i)
            return index;
    });

};


function buscarSegundoMaior(lista, i){

    let segundoMaior = Math.max.apply(Math, lista.slice(index+1, lista.length));

    if(segundoMaior == lista.get(i)){
        lista.slice(index+1, lista.length).forEach((element, index =>{
            if(element == segundoMaior)
                return index;
        }));
    }else{
        lista.slice(index+1, lista.length).forEach((element, index =>{
            if(element == segundoMaior)
                return index;
        }));
    };

    return null;
};
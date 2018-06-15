'use strict'

const fileRead = require('../fileServices/fileRead.js');

exports.run = function(){
    fileRead.readFile(desafio);
   //desafio(null, 1);
};


var desafio = function(err, data){
    if(err){
        console.error(err.message);
        throw err;
    }

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

        if(index %2 == 1){
            let linhas = [];
            casos.push(element.split(' '));
        }

    });

    casos.forEach((linha, indexLinha) => {

        let valor = 0;
        let valorParcial = 0;
        var index = 0;

        for(index; index < linha.length; index++) {

            valor = linha[index];;
            var indexMaior = buscarIndexMaior(linha, index);

            if(indexMaior == null){
                if(index + 1 == linha.length)
                    break;

                var indexSegundoMaior = buscarSegundoMaior(linha, index);
                valor = linha[indexSegundoMaior];

                if(indexSegundoMaior == (index + 1))
                    continue;
                else{

                    for(var element in linha.slice(index + 1, indexSegundoMaior))
                        valorParcial = valorParcial + (valor - element);

                    index = indexSegundoMaior - 1;
               
                }

            }else if(indexMaior == (index+1)){
                continue;
            }else{
                linha.slice(index +1, indexMaior).forEach((element) => {
                    valorParcial = valorParcial + (valor - element);
                })

                index = indexMaior - 1;
            }


        };
            console.log(valorParcial);
            returnable.push(valorParcial);
            

     

    });
    

}


function buscarIndexMaior(lista, index){

    let numero = lista[index];

    lista.forEach((element, count) => {
        if(element > numero && count < index)
            return index;
    });

    return null;

};


function buscarSegundoMaior(lista, index){

    let count = index;
    let segundoMaior = Math.max.apply(Math, lista.slice(index+1, lista.length));

    if(segundoMaior == lista[index]){

        var subList = lista.slice(index+1, lista.length);

        for(var i = 0; i< subList.length; i++){
            let element = subList[i];
            if(element == segundoMaior)
                return i;
        }

    }else{
        var subList = lista.slice(index, lista.length);
        
        for(var i = 0; i< subList.length; i++){
            let element = subList[i];
            if(element == segundoMaior)
                return i;
        }
    };

    return null;
};
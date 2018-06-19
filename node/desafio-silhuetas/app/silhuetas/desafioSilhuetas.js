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
            let linha = element.split(' ');

             
            
            
            casos.push(linha);

        }

    });

    var indexLinha = 0;

    for(indexLinha; indexLinha < casos.length; indexLinha++) {

        var linha = casos[indexLinha];
        let valor = 0;
        let valorParcial = 0;
        var index = 0;

        

        if(linha[linha.length-1] == '')
            linha = linha.slice(0, linha.length-1);

        for(index; index < linha.length; index++) {

            valor = linha[index];
            var indexMaior = buscarIndexMaior(linha, index);



            if(indexMaior == null){
                if(index + 1 == linha.length)
                    break;

                var indexSegundoMaior = buscarSegundoMaior(linha, index);
                valor = linha[indexSegundoMaior];

                if(indexSegundoMaior == (index + 1))
                    continue;
                else{

                    let linha1 = linha.slice(index + 1, indexSegundoMaior);

                    for(var element in linha1)
                        valorParcial = valorParcial + (valor - linha1[element]);

                    index = indexSegundoMaior - 1;
               
                }

            }else if(indexMaior == (index+1)){
                continue;
            }else{
                let linha1 = linha.slice(index +1, indexMaior);

                for(var element in linha1)
                    valorParcial = valorParcial + (valor - linha1[element]);
                

                index = indexMaior - 1;
            }


        };
            console.log(valorParcial);
            returnable.push(valorParcial);
            

     

    };
    

}


function buscarIndexMaior(lista, index){

    let numero = parseInt(lista[index]);

    var i=0;
    for(i; i<lista.length; i++){
        var element = parseInt(lista[i]);
        if(element > numero && index < i)
            return i;
    };

    return null;

};


function buscarSegundoMaior(lista, index){

    let count = index;
    let segundoMaior = 0
    let listAux = lista.slice(index+1, lista.length);
    if(index + 1  == lista.length-1)
        segundoMaior = lista[index+1]
    else
        segundoMaior = Math.max.apply(Math, listAux);




    if(segundoMaior == parseInt(lista[index])){

        var subList = lista.slice(index+1, lista.length);
        count = index+1;

        var i=0;
        for (var i in subList){
            let element = subList[i];
            if(element == segundoMaior)
                return count;
            
            count++;
        }

    }else{
        var subList = lista.slice(index, lista.length);

        for(var i in subList){
            let element = subList[i];
            if(element == segundoMaior)
                return count;

            count = count + 1;
        }
    };

    return null;
};
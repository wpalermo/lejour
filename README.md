# Desafio Lejour
### Desafio do alagamento de silhuetas


Foi feito em apenas uma classe com void main() e não foi criado nenhum objeto e apesar de ter sido criado um projeto maven não foi adicionada nenhuma dependencia, apenas dois metodos auxiliares um para buscar a maior silhueta e outro para buscar a segunda maior silhueta. 

Algumas libs do JAVA8 foram usadas, então é necessário JDK8 ou mais recente.

O projeto contem a pasta "Resources", nela estão os arquivos que serao lidos e é nela que o arquivo de resultado será gravado.
Caso queira trocar os caminho e nome de aruqivos é só editar os seguintes atributos
```
FILE_NAME = nome do arquivo
RESOURCE_PATH = caminho do arquivo
RESULT_FILE = nome do arquivo de saida (<nome do arquivo de entrada> + _RESULTADO.txt)
```
 


#### A ideia do que foi implentado é a seguinte. 
##### Para cada caso são feitos os seguintes passos: 
 - Varre-se a linha buscando sempre a próxima silhueta que é maior que a atual. Quando essa silhueta é encontrada, uma sublista é gerada da sulhueta atual até a próxima silhueta maior que a atual e usando essa sublista é calculado o valor de agua que caberia naquele intervalo.
 
 - Uma vez que não ja se tenha chegado a maior silhueta, passa-se a ser calculada as silhuetas menores que esta, procurando a segunda maior e usando a mesma lógica, alterando apenas o atributo usado para o calculo da água. 
 
 
 - É sempre verificado se a proxima silhueta maior ou menor (dependendo do caso) é adjacente a atual. Caso seja nada é adicionado ao valor final.
 
 

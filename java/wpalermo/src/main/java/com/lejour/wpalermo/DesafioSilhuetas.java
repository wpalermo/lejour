package com.lejour.wpalermo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * O Atributo FILE_NAME é o nome do arquivo.
 * 
 * O Atributo RESOURCE_PATH é o caminho da pasta onde o arquivo está (no caso
 * esta apontando para a pasta resources do projeto)
 * 
 * O Atributo RESULT_FILE é o nome do arquivo copm o resultado do processamento,
 * eh o mesmo nome do arquivo de entrada com _RESULTADO no final. Este será
 * gravado na mesma pasta do RESORUCE_PATH
 * 
 * @author william.palermo
 *
 */

public class DesafioSilhuetas {

	final static String FILE_NAME = "caso1.txt";
	final static String RESOURCE_PATH = "./src/main/resources/casos/";
	final static String RESULT_FILE = FILE_NAME.split("\\.")[0] + "_RESULTADO.txt";

	private static Integer qtdCasos = 0;
	private static Integer linhasCount = 0;
	private static Stream<String> lines;

	public static void main(String[] args) throws IOException {

		// Lista com todos os valores
		List<List<Integer>> values = new ArrayList<List<Integer>>();

		// Le todas as linhas do arquivo e joga dentro de um stream
		lines = Files.lines(Paths.get(RESOURCE_PATH + FILE_NAME));

		// Varre o stream e coloca as linhas como String em uma lista com todos os casos
		List<String> casos = lines.map(String::valueOf).collect(Collectors.toList());

		// Remove a primeira linha com a quantidade de casos no total.
		qtdCasos = Integer.valueOf(casos.remove(0));

		// Varre todos os casos e pega apenas as linhas com os valores a serem
		// processados
		casos.forEach(line -> {

			linhasCount++;

			if (linhasCount % 2 != 1) {
				List<Integer> list = new ArrayList<Integer>();
				for (String s : line.split(" "))
					list.add(Integer.valueOf(s));
				values.add(list);
			}

		});

		// Apenas verifica se a quantidade de linhas 'e igual a quantidade de casos e
		// imprime um WARN como System.out (sem logger)
		if (linhasCount / 2 != qtdCasos)
			System.out.println("WARN - Quantidade de linhas diferente da quantidade passada no header");

		List<String> returnable = new ArrayList<String>();

		// LOOP percorre todos os casos
		for (List<Integer> lista : values) {

			Integer valorParcial = 0;
			Integer indexMaior = 0;
			Integer indexSegundoMaior = 0;
			Integer valor = 0;

			// Loop percorre a linha do caso propriamente dita
			for (int i = 0; i < lista.size(); i++) {

				valor = lista.get(i);

				indexMaior = buscarIndexMaior(lista, i);

				// No caso de ja ter processado a maior silhueta
				// Comeca a procurar e contar de maneira descrescente, buscando sempre a segunda
				// maior e aplicando (qse) a mesma logica
				if (indexMaior == null) {
					if (i + 1 == lista.size())
						break;

					indexSegundoMaior = buscarIndexSegundoMaior(lista, i);
					valor = lista.get(indexSegundoMaior);

					if (indexSegundoMaior == (i + 1))
						continue;
					else {

						for (Integer j : lista.subList(i + 1, indexSegundoMaior))
							valorParcial += valor - j;

						// System.out.println(valorParcial);
						i = indexSegundoMaior - 1;

					}

					// else para o caso de a maior silhueta ainda nao ter sido encontrada
					// IF controla o caso de a proxima maior ser adjacente a atual. Sendo assim,
					// nada pode ser somado no resultado final
				} else if (indexMaior == (i + 1))
					continue;
				else {
					// Caso "normal", quando existe uma silhueta maior e esta nao esta adjacente a
					// atual
					for (Integer j : lista.subList(i + 1, indexMaior))
						valorParcial += valor - j;

					i = indexMaior - 1;
				}

			}

			returnable.add(valorParcial.toString());

		}

		// Escreve o arquivo
		Files.write(Paths.get(RESOURCE_PATH + RESULT_FILE), returnable);
	}

	/**
	 * Metodo procura na lista se existe alguma outra coluna maior do que a atual. E
	 * verifica se essa coluna vem depois da atual na seguencia.
	 * 
	 * @param lista
	 * @param index
	 * @return
	 */
	private static Integer buscarIndexMaior(List<Integer> lista, Integer index) {

		Integer count = 0;
		Integer numero = lista.get(index);

		for (Integer i : lista) {
			if (i > numero && index < count)
				return count;
			count++;
		}

		return null;

	}

	/**
	 * No caso de ja ter encontrado a maior coluna, esse metodo eh responsavel por
	 * encontrar a segunda maior. A partir desse momento, a segunda maior passa a
	 * ser considerada como a maior e a logica segue a mesma.
	 * 
	 * @param lista
	 * @param index
	 * @return
	 */
	private static Integer buscarIndexSegundoMaior(List<Integer> lista, Integer index) {

		Integer count = index;
		Integer numero = lista.get(index);

		Integer segundoMaior = lista.subList(index + 1, lista.size()).stream().max(Integer::compare).get();

		// If para o caso de ter 2 numeros iguais seguidos e esse ser igual ao maior.
		// Nao se pode retornar o mesmo indice recebido. Sabendo que os 2 numeros
		// adjacentes sao iguais ao maior, este retorna o proximo indice.
		if (segundoMaior == numero) {
			count = index + 1;
			for (Integer i : lista.subList(index + 1, lista.size())) {
				if (i == segundoMaior)
					return count;
				count++;
			}
		} else {
			// em casos "normais" que o segundo maior nao 'e igual ao numero do indice
			// passado
			for (Integer i : lista.subList(index, lista.size())) {
				if (i == segundoMaior)
					return count;
				count++;
			}
		}

		return null;

	}
}

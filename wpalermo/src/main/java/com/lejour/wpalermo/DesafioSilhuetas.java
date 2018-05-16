package com.lejour.wpalermo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author william.palermo
 *
 */


public class DesafioSilhuetas {

	final static String RESOURCE_PATH = "./src/main/resources/casos/";
	final static String FILE_NAME = "caso1.txt";
	final static String RESULT_FILE = FILE_NAME.split("\\.")[0] + "_RESULTADO.txt";

	private static Integer qtdCasos = 0;
	private static Integer linhasCount = 0;
	private static Stream<String> lines;

	public static void main(String[] args) throws IOException {

		List<List<Integer>> values = new ArrayList<List<Integer>>();

		Path readPath = Paths.get(RESOURCE_PATH + FILE_NAME);
		
		lines = Files.lines(readPath);

		
		List<String> casos = lines.map(String::valueOf).collect(Collectors.toList());
		
		qtdCasos = Integer.valueOf(casos.remove(0));

		casos.forEach(line -> {

			linhasCount++;

			if (linhasCount % 2 != 1) {
				List<Integer> list = new ArrayList<Integer>();
				for (String s : line.split(" "))
					list.add(Integer.valueOf(s));
				values.add(list);
			}

		});

		if (linhasCount / 2 != qtdCasos)
			System.out.println("WARN - Quantidade de linhas diferente da quantidade passada no header");

		List<String> returnable = new ArrayList<String>();

		for (List<Integer> lista : values) {

			Integer valorParcial = 0;
			Integer indexMaior = 0;
			Integer indexSegundoMaior = 0;
			Integer valor = 0;

			for (int i = 0; i < lista.size(); i++) {

				valor = lista.get(i);

				indexMaior = buscarIndexMaior(lista, i);

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

						//System.out.println(valorParcial);
						i = indexSegundoMaior - 1;

					}

				} else if (indexMaior == (i + 1))
					continue;
				else {
					for (Integer j : lista.subList(i + 1, indexMaior))
						valorParcial += valor - j;

					//System.out.println(valorParcial);
					i = indexMaior - 1;
				}

			}

			returnable.add(valorParcial.toString());

		}

		//System.out.println("-------RESPOSTA------");
		//returnable.forEach(System.out::println);
		
		Files.write(Paths.get(RESOURCE_PATH + RESULT_FILE), returnable);
	}

	
	/**
	 * Metodo procura na lista se existe alguma outra coluna maior do que a atual.
	 * E verifica se essa coluna vem depois da atual na seguencia. 
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
	 * No caso de ja ter encontrado a maior coluna, esse metodo eh responsavel por encontrar a segunda maior. 
	 * A partir desse momento, a segunda maior passa a ser considerada como a maior e a logica segue a mesma.
	 * @param lista
	 * @param index
	 * @return
	 */
	private static Integer buscarIndexSegundoMaior(List<Integer> lista, Integer index) {

		Integer count = index;
		Integer numero = lista.get(index);

		Integer segundoMaior = lista.subList(index + 1, lista.size()).stream().max(Integer::compare).get();

		//If para o caso de ter 2 numeros iguais seguidos e esse ser igual ao maior
		if (segundoMaior == numero) {
			count = index+1;
			for (Integer i : lista.subList(index + 1, lista.size())) {
				if (i == segundoMaior)
					return count;
				count++;
			}
		} else {

			for (Integer i : lista.subList(index, lista.size())) {
				if (i == segundoMaior)
					return count;
				count++;
			}
		}

		return null;

	}
}

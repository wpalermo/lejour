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
 * Hello world!
 *
 */
public class App2 {

	final static String PATH = "./src/main/resources/casos/caso1.txt";

	private static Integer qtdCasos = 0;
	private static Integer linhasCount = 0;

	public static void main(String[] args) throws IOException {

		List<List<Integer>> values = new ArrayList<List<Integer>>();

		Path path = Paths.get(PATH);

		Stream<String> lines = Files.lines(path);

		List<String> teste = lines.map(String::valueOf).collect(Collectors.toList());

		qtdCasos = Integer.valueOf(teste.remove(0));

		teste.forEach(line -> {

			linhasCount++;

			Integer tamanhoCaso = 0;

			if (linhasCount % 2 == 1)
				tamanhoCaso = Integer.valueOf(line);

			else {
				List<Integer> list = new ArrayList<Integer>();
				for (String s : line.split(" "))
					list.add(Integer.valueOf(s));
				values.add(list);
			}

		});

		if (linhasCount / 2 != qtdCasos)
			System.out.println("WARN - Quantidade de linhas diferente da quantidade passada no header");

		List<Integer> returnable = new ArrayList<Integer>();

		for (List<Integer> lista : values) {

			Integer index = 0;
			Integer valorParcial = 0;
			Integer maior = 0;
			Integer indexMaior = 0;
			Integer indexSegundoMaior = 0;
			Integer valor = 0;

			for (int i = 0; i < lista.size(); i++) {

				valor = lista.get(i);

				indexMaior = buscarIndexMaior(lista, i);

				if (indexMaior == null) {
					// System.out.println("Nao tem maior, procurar decrescente");

					if (i + 1 == lista.size())
						break;

					indexSegundoMaior = buscarIndexSegundoMaior(lista, i);
					valor = lista.get(indexSegundoMaior);

					if (indexSegundoMaior == (i + 1))
						continue;
					else {

						maior = lista.get(indexSegundoMaior);
						for (Integer j : lista.subList(i + 1, indexSegundoMaior))
							valorParcial += valor - j;

						System.out.println(valorParcial);
						i = indexSegundoMaior - 1;

					}

				} else if (indexMaior == (i + 1))
					continue;
				else {
					maior = lista.get(indexMaior);
					for (Integer j : lista.subList(i + 1, indexMaior))
						valorParcial += valor - j;

					System.out.println(valorParcial);
					i = indexMaior - 1;
				}

			}

			returnable.add(valorParcial);

		}

		System.out.println("-------RESPOSTA------");
		returnable.forEach(System.out::println);
	}

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

	private static Integer buscarIndexSegundoMaior(List<Integer> lista, Integer index) {

		Integer count = index;
		Integer numero = lista.get(index);

		Integer segundoMaior = lista.subList(index + 1, lista.size()).stream().max(Integer::compare).get();

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

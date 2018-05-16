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
public class App 
{
	
	final static String PATH = "./src/main/resources/casos/caso1.txt";
	
	private static Integer qtdCasos = 0;
	private static Integer linhasCount = 0;
	
    public static void main( String[] args ) throws IOException
    {


		
		List<List<Integer>> values = new ArrayList<List<Integer>>();
		
		Path path = Paths.get(PATH);
	
		Stream<String> lines = Files.lines(path);

		
		
		List<String> teste = lines.map(String::valueOf).collect(Collectors.toList());
		
		qtdCasos = Integer.valueOf(teste.remove(0));
	
		
		
		teste.forEach(line -> {
			
			linhasCount++;
			
			Integer tamanhoCaso = 0;
			
			if(linhasCount % 2 == 1)
				tamanhoCaso = Integer.valueOf(line);
				
			else {	
				List<Integer> list = new ArrayList<Integer>();
				for(String s : line.split(" ")) 
					list.add(Integer.valueOf(s));
				values.add(list);
			}
				
		});
		
		if(linhasCount/2 != qtdCasos )
			System.out.println("WARN - Quantidade de linhas diferente da quantidade passada no header");
		
		for(List<Integer> lista : values) {
			
			
			
		}
    	
    
    }
}

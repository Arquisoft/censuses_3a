package es.uniovi.asw.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.model.Voter;

/**
 * Clase que permite cargar los datos de los votantes de un fichero TXT
 * 
 * @author Dario Rodriguez Garcia (@dariorg)
 * @version 2016.02.09
 *
 */
public class LoadFromTxt {
	
	public List<Voter> loadUsers( String fichero ) throws IOException{
		
		assertFileExtension(fichero);
		assertExistFile(fichero);
		
		BufferedReader in = new BufferedReader(new FileReader(fichero));
		List<Voter> voters = new ArrayList<Voter>();
		String line;
		String [] campos;
		int i = 0;
		while((line = in.readLine()) != null){

			if(i > 0){
				campos = line.split("\t");
				voters.add(new Voter(campos[0], campos[0].replaceAll("\\s+",""), campos[1], 
						new Integer(campos[2]), new Integer(campos[3])));   
			}
			i++;
			
		}
		in.close();
		return voters;
	}

	private BufferedReader assertExistFile(String fichero) {
		BufferedReader in;
		try{
			in = new BufferedReader(new FileReader(fichero));
		}catch(FileNotFoundException fnfe){
			throw new IllegalArgumentException("No se encuentra el fichero especificado");
		}
		return in;
	}

	private void assertFileExtension(String fichero) {
		if(! fichero.contains("txt")){
			throw new IllegalArgumentException("El fichero especificado no es un fichero de texto (txt)");
		}
	}
	
	

}
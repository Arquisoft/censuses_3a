package es.uniovi.asw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import es.uniovi.asw.model.Voter;
import es.uniovi.asw.parser.ExcelParser;
import es.uniovi.asw.parser.Parser;
import es.uniovi.asw.parser.TxtParser;

/**
 * Main application
 * 
 * @author Darío Rodríguez García (@dariorg)
 *
 */
public class LoadUsers {
	
	@SuppressWarnings("unused")
	private List<Voter> voters;

	public static void main(String... args) {
		LoadUsers runner = new LoadUsers();
		try {
			runner.run();
		} catch (IOException e) {
			System.out.println( e.getMessage() );
		}
	}
	
	void run() throws IOException{
		
		Parser parser = null;
		
		System.out.print("Introduzca la extensión del fichero [excel/txt]:\n> ");
		String extension = new BufferedReader( new InputStreamReader(System.in) ).readLine();
		
		System.out.print("Introduzca el nombre del fichero:\n> ");
		String fichero = new BufferedReader( new InputStreamReader(System.in) ).readLine();
		
		if(extension.equals("excel")){
			parser = new ExcelParser();
		}else if(extension.equals("txt")){
			parser = new TxtParser();
		}else{
			System.out.println("Extensión no válida");
			run();
		}
		try{
			voters = parser.loadUsers(fichero);
			
		}catch( IllegalArgumentException iae ){
			System.out.println( iae.getMessage() );
			run();
		}
		
		System.out.println("CARGA DE DATOS CORRECTA");
		new BufferedReader( new InputStreamReader(System.in) ).readLine();
	}
}

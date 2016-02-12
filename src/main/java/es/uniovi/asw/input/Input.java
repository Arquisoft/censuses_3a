package es.uniovi.asw.input;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * @author Darío Rodríguez García (@dariorg)
 *
 */
public class Input {
	
	/**
	 * Método para pedir los parámetros que permiten seleccionar la extensión del
	 * fichero a cargar
	 * 
	 * @param args
	 */
	public void getDataInput(String...args){
		Options options = options();
		CommandLineParser p = new DefaultParser();
		try {
			CommandLine cmd = p.parse( options, args );
			if(cmd.hasOption("x")){
				args[0] = cmd.getOptionValue("x"); args[1] = "x";
			}else if(cmd.hasOption("txt")){
				args[0] = cmd.getOptionValue("txt"); args[1] = "txt";
			}else{
				System.out.println("Opción no válida");
			}
		}catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private Options options() {
		Options options = new Options();
		options.addOption("x", true, "Cargar datos a partir de un fichero Excel (xlsx)");
		options.addOption("txt", true, "Cargar datos a partir de un fichero de texto (txt)");
		return options;
	}

}

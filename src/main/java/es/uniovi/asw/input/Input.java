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
		Options options = fileOptions();
		CommandLineParser p = new DefaultParser();
		try {
			CommandLine cmd = p.parse( options, args );
			if(cmd.hasOption("x")){
				args[0] = cmd.getOptionValue("x"); args[1] = "x";
				System.out.println("Entra por excel");
			}else if(cmd.hasOption("t")){
				args[0] = cmd.getOptionValue("t"); args[1] = "t";
				System.out.println("Entra por txt");
			}else{
				throw new RuntimeException("Formato de entrada no válido");
			}
			getOutputFormat( args );
		}catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void getOutputFormat(String...args){
		if(! args[2].equals("txt") && ! args[2].equals("word")){
			throw new RuntimeException("Se debe escoger el formato de salida de las cartas");
		}
	}
	
	private Options fileOptions() {
		Options options = new Options();
		options.addOption("x", true, "Cargar datos a partir de un fichero Excel (xlsx)");
		options.addOption("t", true, "Cargar datos a partir de un fichero de texto (txt)");
		return options;
	}

}

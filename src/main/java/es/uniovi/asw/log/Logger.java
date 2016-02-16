package es.uniovi.asw.log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Clase que registra las incidencias en un fichero de LOG
 * para que éstas puedan ser reportadas
 * 
 * Patrón Singleton
 * 
 * @author Dario Rodríguez García (@dariorg)
 *
 */
public class Logger {
	
	private static Logger logger = null;
	
	private DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	private static Date date;
	
	private Logger(){
		date = new Date(System.currentTimeMillis());
	}
	
	public static Logger getInstance(){
		if(logger == null){
			logger = new Logger();
		}
		date = new Date(System.currentTimeMillis());
		return logger;
	}
	
	public void writeReport(String fichero, Exception e){
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/main/resources/log.txt", true)))) {
			String line = "[" + format.format(date) + "]" + " FILE: " + fichero + " EXCEPTION TRACE: " + e.getMessage();
		    out.println(line);
		}catch (IOException ex) {
		   ex.printStackTrace();
		}
	}

}

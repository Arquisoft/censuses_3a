package es.uniovi.asw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es.uniovi.asw.model.Voter;

/**
 * Main application
 * 
 * @author Darío Rodríguez García (@dariorg)
 *
 */
public class LoadUsers {
	
	private List<Voter> voters;

	public static void main(String... args) throws IOException {
		LoadUsers runner = new LoadUsers();
		System.out.print("Introduzca el nombre del fichero:\n> ");
		String fichero = new BufferedReader(
				new InputStreamReader(System.in)).readLine();
		try{
			runner.run(fichero);
		}catch(RuntimeException re){
			System.out.println(re.getMessage());
		}
	}
	
	void run(String... args) {
		try {
			loadUsers(args[0]);
		} catch (IOException fnfe) {
			throw new RuntimeException("No se encuentra el fichero especificado");
		} 
	}
	
	/**
	 * Método que permite cargar los datos de los votantes de un censo
	 * 
	 * @param fichero - Nombre del fichero del que cargar datos
	 * @return - Lista con los votantes de un censo, cargados con sus datos
	 * @throws IOException
	 */
	public List<Voter> loadUsers( String fichero ) throws IOException {
		voters = new ArrayList<Voter>();
		FileInputStream file = new FileInputStream( new File( fichero ) );
		
		//Obtenemos la hoja de votantes del censo
		XSSFWorkbook listaVotantes = new XSSFWorkbook( file ); 
		
		 //Obtenemos la primera hoja del libro excel
		XSSFSheet hoja = listaVotantes.getSheetAt(0);
		
		//Iteramos sobre cada fila de la primera hoja
		Iterator<Row> rowIterator = hoja.iterator(); 
	    rowIterator.next();
	    while( rowIterator.hasNext() ) {
	        Row row = rowIterator.next();
	        
	        //Para cada fila, iteramos a través de cada una de sus columnas
	        Iterator<Cell> columnas = row.cellIterator(); 
	        loadDataVoter( columnas ); //Cargamos los datos del votante
	    }
	    listaVotantes.close();
		return voters;
	}


	private void loadDataVoter( Iterator<Cell> columnas ) {
		String nombre, dni;
		int pollingStationCode, table;
		
		nombre = columnas.next().getStringCellValue();
		dni = columnas.next().getStringCellValue();
		pollingStationCode = (int) columnas.next().getNumericCellValue();
		table = ( int ) columnas.next().getNumericCellValue();
		voters.add( 
				new Voter(nombre, nombre.replaceAll("\\s+",""), dni, 
						pollingStationCode, table ) );
	}
}

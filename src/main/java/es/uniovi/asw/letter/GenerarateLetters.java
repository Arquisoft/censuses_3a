package es.uniovi.asw.letter;

import java.util.List;

import es.uniovi.asw.model.Voter;

/**
 * @author Dario Rodríguez García (@dariorg)
 * @version 2016.02.14
 *
 */
public class GenerarateLetters {
	
	/**
	 * Método que permite generar las cartas con los datos del censo para
	 * los distintos votantes dependiendo del formato
	 * 
	 * @param format, txt o word
	 * @param voters, lista de votantes de un censo
	 */
	public static void generateLetter( String format, List<Voter> voters ){
		WriteLetter formatLetter;
		if(format.equals("txt")){
			formatLetter = new WriteTxtLetter();
		}else{
			formatLetter = new WriteWordLetter();
		}
		for(Voter voter : voters){
			formatLetter.writeLetter(voter);
		}
	}

}

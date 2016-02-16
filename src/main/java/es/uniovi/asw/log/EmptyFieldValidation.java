package es.uniovi.asw.log;

import es.uniovi.asw.model.Voter;

/**
 * Clase que comprueba los campos vacíos
 * 
 * @author Dario Rodríguez García (@dariorg)
 *
 */
public class EmptyFieldValidation implements Validate{

	@Override
	public void validation(Voter voter, String field) {
		if(field.trim().length() == 0){
			throw new IllegalStateException("Existen campos vacíos para el votante: "
					+ voter.getNombre());
		}
	}
	
	

}

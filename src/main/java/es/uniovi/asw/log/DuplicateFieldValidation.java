package es.uniovi.asw.log;

import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.model.Voter;

/**
 * Clase que comprueba los campos duplicados
 * 
 * @author Dario Rodríguez García (@dariorg)
 *
 */
public class DuplicateFieldValidation implements Validate{
	
	private List<String> fields = new ArrayList<String>();

	@Override
	public void validation( Voter voter, String field ) {
		if( fields.contains( field ) ){
			throw new IllegalStateException("DNI " + field + " duplicado");
		}
		fields.add(field);
	}	
}

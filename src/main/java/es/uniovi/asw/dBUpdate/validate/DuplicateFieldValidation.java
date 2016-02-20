package es.uniovi.asw.dBUpdate.validate;

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
	public void validation( Voter voter ) {
		if( fields.contains( voter.getNif() ) ){
			throw new IllegalStateException("DNI " + voter.getNif() + " duplicado");
		}else{
			fields.add( voter.getNif() );
		}
	}	
}

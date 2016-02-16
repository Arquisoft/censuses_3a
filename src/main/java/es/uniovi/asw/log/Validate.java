package es.uniovi.asw.log;

import es.uniovi.asw.model.Voter;

/**
 * Interfaz para los distintos métodos de validación
 * 
 * Patrón Strategy
 * 
 * @author Dario Rodríguez García (@dariorg)
 *
 */
public interface Validate {
	
	void validation( Voter voter, String field ) throws IllegalStateException;

}

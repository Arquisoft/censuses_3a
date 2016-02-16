package es.uniovi.asw.log;

import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.model.Voter;

/**
 * Clase aplica todas las validaciones necesarias sobre los
 * distintos campos de un votante
 * 
 * @author Dario Rodríguez García (@dariorg)
 *
 */
public class Validation {
	
	private List<Validate> nifValidations, pollingValidations;
	
	public Validation(){
		nifValidations = pollingValidations = new ArrayList<Validate>();
		nifValidations.add( new EmptyFieldValidation() );
		nifValidations.add( new DuplicateFieldValidation() );
		pollingValidations.add( new EmptyFieldValidation() );
	}
	
	public void validateAll( String fichero, Voter voter, List<Voter> voters ){
		try{
			validate(fichero, voter);
			voters.add(voter);
		}catch(IllegalStateException ex){
			Logger.getInstance().writeReport(fichero, ex);
		}
	}
	
	private void validate( String fichero, Voter voter ){
		for(Validate v : nifValidations){
			v.validation( voter, voter.getNif() );
		}
		for(Validate v : pollingValidations){
			v.validation( voter,
					String.valueOf(voter.getPollingStationCode()) );
		}
	}

}

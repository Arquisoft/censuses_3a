package es.uniovi.asw.dBUpdate;

import java.util.List;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;

import es.uniovi.asw.dBUpdate.validate.Validation;
import es.uniovi.asw.model.Voter;
import es.uniovi.asw.parser.letter.GenerarateLetters;
import es.uniovi.asw.reportWriter.WreportR;


/**
 * Clase que verifica los datos y genera el LOG con los datos
 * inconsistentes
 * 
 * @author Darío Rodríguez García (@dariorg)
 *
 */
@SpringBootApplication
@EntityScan("es.uniovi.asw.model")
public class InsertP implements Insert{
	
	private static List<Voter> voters;
	
	public void saveVoters(List<Voter> v, String...args ) {
		voters = v;
		run( args );
	}
	
	public static void run(String...args){
		SpringApplication.run( InsertP.class, args );
	}
	
	@Bean
	public CommandLineRunner load(VoterRepository repository) {
		return (args) -> {
			Voter v = null;
			Validation validation = new Validation();
			GenerarateLetters.generateLetter(args[2], voters);
			for(Voter voter: voters){
				encryptData(voter);
				v = validation.validateAll( args[1], voter );
				saveVoter(repository, v, args[1]);
			}
		};
	}

	private void encryptData(Voter voter) {
		StrongPasswordEncryptor e = new StrongPasswordEncryptor();
		voter.setPassword(e.encryptPassword(voter.getPassword()));
	}
	
	private void saveVoter(VoterRepository repository, Voter voter, String fichero) {
		try{
			if(voter != null){
				repository.save(voter);
			}
		}catch(Exception error){
			WreportR.getInstance().writeReport(fichero, 
					new IllegalStateException(
							"Ya existe un registro con los mismos datos almacenados"));
		}
	}

}

package es.uniovi.asw;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import es.uniovi.asw.input.Input;
import es.uniovi.asw.letter.GenerarateLetters;
import es.uniovi.asw.model.Voter;
import es.uniovi.asw.parser.ExcelParser;
import es.uniovi.asw.parser.Parser;
import es.uniovi.asw.parser.TxtParser;
import es.uniovi.asw.password.GenerarClave;
import es.uniovi.asw.persistence.VoterRepository;

/**
 * Main application
 * 
 * @author Darío Rodríguez García (@dariorg)
 *
 */
@SpringBootApplication
public class LoadUsers {
	
	private List<Voter> voters;

	public static void main(String... args) {
		new Input().getDataInput( args );
		SpringApplication.run( LoadUsers.class, args );
	}
	
	@Bean
	public CommandLineRunner load(VoterRepository repository) {
		return (args) -> {
			Parser parser;
			if( args[1].equals("x") ){ 
				parser = new ExcelParser();
			} else { 
				parser = new TxtParser(); }
			voters = parser.loadUsers( args[0] );
			for( Voter voter : voters ){
				voter.setPassword(GenerarClave.getPassword(8));
				repository.save(voter);
			}
			GenerarateLetters.generateLetter(args[2], voters);
		};
	}
}

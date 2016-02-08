package es.uniovi.asw;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import es.uniovi.asw.model.Voter;

public class LoadUsersTest {

	private LoadUsers loadUsers = new LoadUsers();

	/**
	 * Método que comprueba la excepción por fichero no encontrado
	 * @throws IOException, Si no se encuentra el fichero
	 */
	@Test (expected = FileNotFoundException.class)
	public void testFicheroNoEncontrado() throws IOException {
		@SuppressWarnings("unused")
		List<Voter> voters = loadUsers.loadUsers("src/test/resources/test1.xlsx");
	}
	
	/**
	 * Método que comprueba la existencia del fichero
	 * 
	 * @throws IOException
	 */
	@Test
	public void testFicheroEncontrado() throws IOException {
		@SuppressWarnings("unused")
		List<Voter> voters = loadUsers.loadUsers( "src/test/resources/test.xlsx" );
	}
	
	/**
	 * Método que comprueba si es correcto el número de votantes cargados de la 
	 * hoja Excel
	 * 
	 * @throws IOException
	 */
	@Test
	public void testNumeroVotantesFichero() throws IOException{
		List<Voter> voters = loadUsers.loadUsers( "src/test/resources/test.xlsx" );
		//Comprobamos numero de votantes de la hoja Excel (son 4 filas (1 de cabecera y 3 de votantes))
		assertFalse(voters.size() == 4);
		
		//Comprobamos numero de votantes de la hoja Excel
		assertEquals(3, voters.size()); 
	}
	
	
	/**
	 * Método que comprueba que los votantes se cargan con los datos adecuados
	 * 
	 * @throws IOException
	 */
	@Test
	public void testDatosVotantesFichero() throws IOException{
		List<Voter> voters = loadUsers.loadUsers( "src/test/resources/test.xlsx" );
		Voter voter = voters.get(0); //Primer ciudadano
		assertEquals("Juan Torres Pardo", voter.getNombre());
		assertEquals("90500084Y", voter.getNif());
		assertEquals(234, voter.getPollingStationCode());
		assertEquals(2, voter.getTable());
		
		voter = voters.get(1); //Segundo ciudadano
		assertEquals("Luis López Fernando", voter.getNombre());
		assertEquals("19160962F", voter.getNif());
		assertEquals(345, voter.getPollingStationCode());
		assertEquals(2, voter.getTable());
		
		voter = voters.get(2); //Segundo ciudadano
		assertEquals("Ana Torres Pardo", voter.getNombre());
		assertEquals("09940449X", voter.getNif());
		assertEquals(456, voter.getPollingStationCode());
		assertEquals(3, voter.getTable());
	}
}

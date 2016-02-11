package es.uniovi.asw.model;

import javax.persistence.Table;

/**
 * @author Dario Rodríguez García (@dariorg on GitHub)
 * 
 * @version 2016.x.x 
 *
 * Clase POJO del modelo de dominio que recoge los datos de los ciudadanos censados así como
 * el colegio electoral/mesa en el que emitir su voto.
 * 
 *///CrudRepository
@Table( name = "VOTER" )
public class Voter {
	
	private String nombre;
	private String email;
	private String nif;
	private String password;
	
	//Polling station
	private int pollingStationCode;
	private int table;
	
	
	public Voter(String nombre, String email, String nif, int pollingStationCode, int table) {
		this.nombre = nombre;
		this.email = email;
		this.nif = nif;
		this.pollingStationCode = pollingStationCode;
		this.table = table;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getNombre() {
		return nombre;
	}


	public String getNif() {
		return nif;
	}
	
	
	public int getPollingStationCode() {
		return pollingStationCode;
	}
	
	
	public void setPollingStationCode(int pollingStationCode) {
		this.pollingStationCode = pollingStationCode;
	}
	
	
	public int getTable() {
		return table;
	}
	
	
	public void setTable(int table) {
		this.table = table;
	}


	@Override
	public String toString() {
		return "Voter [nombre=" + nombre + ", email=" + email + ", nif=" + nif + ", password=" + password
				+ ", pollingStationCode=" + pollingStationCode + ", table=" + table + "]";
	}
	

}

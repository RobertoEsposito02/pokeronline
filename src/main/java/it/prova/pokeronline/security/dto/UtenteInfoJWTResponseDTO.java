package it.prova.pokeronline.security.dto;

import java.util.Date;
import java.util.List;

public class UtenteInfoJWTResponseDTO {

	private String nome;
	private String cognome;
	private String type = "Bearer";
	private String username;
	private String email;
	private Date dateCreated;
	private List<String> roles;

	public UtenteInfoJWTResponseDTO(Date dateCreated,String nome, String cognome, String username, String email, List<String> roles) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.email = email;
		this.dateCreated = dateCreated;
		this.roles = roles;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	
}
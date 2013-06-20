package br.com.votefilme.model;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class Usuario {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key id;
	
	private String nome;
	private String email;
	private Map<Key,Integer> preferencias;
	
	
	public Usuario(String nome, String email) {
		super();
		this.nome = nome;
		this.email = email;
	}

	public Usuario(String nome, String email, Map<Key, Integer> preferencias) {
		super();
		this.nome = nome;
		this.email = email;
		this.preferencias = preferencias;
	}

	public Key getId() {
		return id;
	}

	public void setId(Key id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Map<Key, Integer> getPreferencias() {
		return preferencias;
	}

	public void setPreferencias(Map<Key, Integer> preferencias) {
		this.preferencias = preferencias;
	}
	
}

package br.com.votefilme.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class Filme implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3735049674280319893L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Key id;
	
	private String nome;
	private Integer votos;
	private String imagemUrl;
	
	public Filme(Key id, String nome, Integer votos, String imagemUrl) {
		super();
		this.id = id;
		this.nome = nome;
		this.votos = votos;
		this.imagemUrl = imagemUrl;
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

	public Integer getVotos() {
		return votos;
	}

	public void setVotos(Integer votos) {
		this.votos = votos;
	}

	public String getImagemUrl() {
		return imagemUrl;
	}

	public void setImagemUrl(String imagemUrl) {
		this.imagemUrl = imagemUrl;
	}
	
}

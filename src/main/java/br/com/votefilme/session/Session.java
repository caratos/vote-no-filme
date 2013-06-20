package br.com.votefilme.session;

import java.io.Serializable;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.votefilme.model.Filme;
import br.com.votefilme.model.Usuario;

@Component
@SessionScoped
public class Session implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6065601714669001846L;
	private Filme filme;
	private Usuario usuario;
	
	/**
	 * Constructor
	 */
	public Session(){
		this.filme = null;
		this.usuario = null;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}

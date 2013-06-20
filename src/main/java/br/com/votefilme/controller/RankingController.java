package br.com.votefilme.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.votefilme.dao.FilmeDao;
import br.com.votefilme.dao.UsuarioDao;
import br.com.votefilme.model.Filme;
import br.com.votefilme.session.Session;

import com.google.appengine.api.datastore.Key;

@Resource
public class RankingController {
	
	private final Session session;
	private final FilmeDao filmeDao;
	private final UsuarioDao usuarioDao;
	private final Result result;
	
	
	public RankingController(Session session, FilmeDao filmeDao,
			UsuarioDao usuarioDao, Result result) {
		super();
		this.session = session;
		this.filmeDao = filmeDao;
		this.usuarioDao = usuarioDao;
		this.result = result;
	}

	/**
	 * Vamos mostrar os rankings
	 */
	public void index() {
		
		List<Filme> preferencias = new ArrayList<Filme>();
		for( Map.Entry<Key, Integer> entry : session.getUsuario().getPreferencias().entrySet()){
			Filme tmp = filmeDao.getById(entry.getKey());
			tmp.setVotos(entry.getValue());
			preferencias.add(tmp);
		}
		
		// passando todos os filmes, o usuário e as preferencias dele
		result.include("filmes", filmeDao.getAllInOrder() );
		result.include("usuario", session.getUsuario() );
		result.include("preferencias", preferencias);
	}
}

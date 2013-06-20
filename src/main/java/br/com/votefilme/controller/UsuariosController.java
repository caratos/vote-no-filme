package br.com.votefilme.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.votefilme.dao.UsuarioDao;
import br.com.votefilme.model.Usuario;
import br.com.votefilme.session.Session;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.images.ImagesServiceFactory;

@Resource
public class UsuariosController {

	private UsuarioDao dao;

	private final Session session;
	private final Validator validator;
	private final HttpServletRequest req;
	private final BlobstoreService blobstoreService;
	private final Result result;

	/*
	 * Wired
	 */
	public UsuariosController(Session session, UsuarioDao dao,
			Validator validator, HttpServletRequest req, Result result) {
		this.dao = dao;
		this.session = session;
		this.validator = validator;
		this.req = req;
		this.blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		this.result = result;
	}

	@Post
	public void registrarVotacao(Usuario usuarioTemporal, String keyPreferido) {
		Map<Key, Integer> preferido = null;
		Key key = KeyFactory.stringToKey(keyPreferido);

		if (usuarioTemporal.getNome() == null)
			validator.add(new ValidationMessage("nome", "Preencher nome"));
		
		if (usuarioTemporal.getEmail() == null)
			validator.add(new ValidationMessage("email", "Preencher email"));
		else if ( !emailCorreto(usuarioTemporal.getEmail()) )
			validator.add(new ValidationMessage("email", "Email invalido"));

		validator.onErrorUsePageOf(FilmesController.class).index();
		

		Usuario usuario = dao.buscar(usuarioTemporal);
		if (usuario == null) {
			preferido = new HashMap<Key, Integer>();
			preferido.put(key, new Integer(1));

			usuario = new Usuario(usuarioTemporal.getNome(),
					usuarioTemporal.getEmail());

			usuario.setPreferencias(preferido);

			dao.adiciona(usuario);
		} else {
			preferido = usuario.getPreferencias();
			if (preferido.containsKey(key))
				preferido.put(key, preferido.get(key).intValue() + 1);
			else
				preferido.put(key, new Integer(1));

			usuario.setPreferencias(preferido);
			dao.atualiza(usuario);
		}
		
		session.setUsuario(usuario);
	}

	/**
	 * Devolve 'true' se o email for válido, 'false' caso contrario
	 * @param email
	 * @return
	 */
	private boolean emailCorreto(String email) {
		 String emailPattern= "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+
				 				"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		 Pattern pattern = Pattern.compile(emailPattern);
		 Matcher matcher = pattern.matcher(email);
		 return matcher.matches();
	}

}

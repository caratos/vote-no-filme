package br.com.votefilme.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.caelum.vraptor.validator.ValidationException;
import br.com.votefilme.controller.UsuariosController;
import br.com.votefilme.dao.UsuarioDao;
import br.com.votefilme.model.Usuario;
import br.com.votefilme.session.Session;
import br.com.votefilme.test.util.BlobstoreServiceMock;
import br.com.votefilme.test.util.EntityManagerMock;
import br.com.votefilme.test.util.HttpServletRequestMock;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class UsuarioTest {

	/**
	 * Neste teste, buscamos usuario que ainda nao existe
	 */
	@Test
	public void buscarUsuarioNaoExiste() {
		UsuarioDao dao = new UsuarioDao(new EntityManagerMock());
		Usuario usuario = new Usuario("Carlos Torres", "carlos@torres.com");

		assertEquals(true, dao.buscar(usuario) == null);
	}

	/**
	 * Neste teste, buscamos usuario que já existe
	 */
	@Test
	public void buscarUsuarioExiste() {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Key ancestor = KeyFactory.createKey("usuario", 1);

		UsuarioDao dao = new UsuarioDao(new EntityManagerMock());
		Usuario usuario = new Usuario("Carlos Torres", "carlos@torres.com");
		usuario.setId(ancestor);

		dao.adiciona(usuario);
		assertEquals(true, dao.buscar(usuario) != null);
	}

	/**
	 * Neste teste, inserimos um novo usuario
	 */
	@Test
	public void insereNovoUsuario() {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		UsuarioDao dao = new UsuarioDao(new EntityManagerMock());
		UsuariosController controller = new UsuariosController(new Session(),
				dao, new MockValidator(), new HttpServletRequestMock(),
				new MockResult());

		Usuario usuarioTemporal = new Usuario("Carlos Torres",
				"carlos@torres.com");

		Key key = KeyFactory.createKey("filme", 1);

		assertEquals(true, dao.buscar(usuarioTemporal) == null);
		controller.registrarVotacao(usuarioTemporal,
				KeyFactory.keyToString(key));
		assertEquals(true, dao.buscar(usuarioTemporal) != null);
	}

	/**
	 * Inserimos um usuario, e novamente votamos com outro filme.
	 */
	@Test
	public void atualizarDadosUsuario() {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		UsuarioDao dao = new UsuarioDao(new EntityManagerMock());
		UsuariosController controller = new UsuariosController(new Session(),
				dao, new MockValidator(), new HttpServletRequestMock(),
				new MockResult());

		Usuario usuarioTemporal = new Usuario("Carlos Torres",
				"carlos@torres.com");

		Key key = null;

		key = KeyFactory.createKey("filme", 1);
		controller.registrarVotacao(usuarioTemporal,
				KeyFactory.keyToString(key));
		key = KeyFactory.createKey("filme", 2);
		controller.registrarVotacao(usuarioTemporal,
				KeyFactory.keyToString(key));

		Usuario usuario = dao.buscar(usuarioTemporal);
		assertEquals(false, usuario == null);
		assertEquals(2, usuario.getPreferencias().size());
	}

	/**
	 * Um usuario nao pode inserir campos vazios o um email inválido
	 */

	@Test
	public void campoVazio() {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		UsuarioDao dao = new UsuarioDao(new EntityManagerMock());

		UsuariosController controller = new UsuariosController(new Session(),
				dao, new MockValidator(), new HttpServletRequestMock(),
				new MockResult());

		String keyString = KeyFactory.keyToString(KeyFactory.createKey("filme",1));

		boolean erros = false;
		try {
			controller.registrarVotacao(new Usuario(null, "carlos@torres.com"),keyString);
			controller.registrarVotacao(new Usuario("Carlos Torres", null),keyString);
			controller.registrarVotacao(new Usuario(null, null),keyString);
		} catch (ValidationException ve) {
			erros = true;
		}

		assertEquals(true, erros);
	}

	/**
	 * Email nao pode ser invalido
	 */
	@Test
	public void emailInvalido() {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		UsuarioDao dao = new UsuarioDao(new EntityManagerMock());

		UsuariosController controller = new UsuariosController(new Session(),
				dao, new MockValidator(), new HttpServletRequestMock(),
				new MockResult());
		
		String keyString = KeyFactory.keyToString(KeyFactory.createKey("filme",1));

		boolean erros;
		erros = false;
		try {  controller.registrarVotacao(new Usuario("Carlos Torres", "A@B@.com"), keyString); } 
		catch (ValidationException ve) { erros = true; }
		assertEquals(true, erros);

		erros = false;
		try {  controller.registrarVotacao(new Usuario("Carlos Torres", "@.com"), keyString); } 
		catch (ValidationException ve) { erros = true; }
		assertEquals(true, erros);
		
		erros = false;
		try {  controller.registrarVotacao(new Usuario("Carlos Torres", "A@B"), keyString); } 
		catch (ValidationException ve) { erros = true; }
		assertEquals(true, erros);
		
		erros = false;
		try {  controller.registrarVotacao(new Usuario("Carlos Torres", ""), keyString); } 
		catch (ValidationException ve) { erros = true; }
		assertEquals(true, erros);
	}
	
	

	/*
	 * Utilitarios
	 */
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());

	@Before
	public void setUp() {
		helper.setUp();
	}

	@After
	public void tearDown() {
		helper.tearDown();
	}
}

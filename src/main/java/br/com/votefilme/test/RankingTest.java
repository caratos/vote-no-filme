package br.com.votefilme.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.votefilme.controller.FilmesController;
import br.com.votefilme.controller.RankingController;
import br.com.votefilme.controller.UsuariosController;
import br.com.votefilme.dao.FilmeDao;
import br.com.votefilme.dao.UsuarioDao;
import br.com.votefilme.model.Filme;
import br.com.votefilme.model.Usuario;
import br.com.votefilme.session.Session;
import br.com.votefilme.test.util.BlobstoreServiceMock;
import br.com.votefilme.test.util.EntityManagerMock;
import br.com.votefilme.test.util.HttpServletRequestMock;
import br.com.votefilme.test.util.ImagesServiceMock;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class RankingTest {

	/**
	 * Ranking de uma votacao
	 */
	@Test 
	public void calculoDoRanking() {
		MockResult result = new MockResult();
		EntityManagerMock em = new EntityManagerMock();
		FilmeDao filmeDao = new FilmeDao(em);
		UsuarioDao usuarioDao = new UsuarioDao(em);
		
		Session sessionTemporal = new Session();
		FilmesController filmesController = new FilmesController(sessionTemporal, filmeDao, new HttpServletRequestMock(),result);
		RankingController rankingController = new RankingController( sessionTemporal, filmeDao,usuarioDao, result );
		UsuariosController usuariosController = new UsuariosController( 
				sessionTemporal, usuarioDao, new MockValidator(), new HttpServletRequestMock() , result);
		
		Key keyUsuario = KeyFactory.createKey("usuario", 1);
		Key keyFilme = KeyFactory.createKey("filme", 2);
		
		Usuario usuarioTemporal = new Usuario("Carlos","carlos@torres.com");
		Filme filmeTemporal = new Filme(keyFilme,"Harry Potter",1,BlobstoreServiceMock.stringUploadCreated);
		
		filmesController.adiciona(filmeTemporal);
		filmesController.uploadImageSubmit();
		
		usuariosController.registrarVotacao(usuarioTemporal, KeyFactory.keyToString(keyFilme));
		rankingController.index();
		
		// recuperar o usuario com o id
		usuarioTemporal = usuarioDao.buscar(usuarioTemporal);
		
		// O usuario foi enviado pra seguinte tela
		assertEquals( usuarioTemporal.getId(), ((Usuario)result.included("usuario")).getId());
		
		// O usuario possui uma preferencia
		assertEquals( 1, usuarioTemporal.getPreferencias().size());
		
		// O ranking marca apenas 1 filme
		assertEquals( 1, ((List<Filme>)result.included("preferencias")).size());
		
		// O usuario possui apenas 1 filme
		assertEquals( 1, ((List<Filme>)result.included("filmes")).size());
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

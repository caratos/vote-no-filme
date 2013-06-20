package br.com.votefilme.test;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.votefilme.controller.FilmesController;
import br.com.votefilme.dao.FilmeDao;
import br.com.votefilme.model.Filme;
import br.com.votefilme.session.Session;
import br.com.votefilme.test.util.BlobstoreServiceMock;
import br.com.votefilme.test.util.EntityManagerMock;
import br.com.votefilme.test.util.HttpServletRequestMock;
import br.com.votefilme.test.util.ImagesServiceMock;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class FilmeTest {

	@Test
	public void inserirNovaImagem() {
		//Criando um datastore local para poder gerar os Keys
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Key ancestor = KeyFactory.createKey("filme", 123);
				
		Session sessionTemporal = new Session();
		sessionTemporal.setFilme( new Filme(ancestor,"Filme Test", 0, null ) );
		
		FilmesController filmes = getFilmesControllerDefault( sessionTemporal, new EntityManagerMock());
		
		// Formulario para preparar o envio da imagem
		filmes.uploadImage();
		
		// Referencia ao filme da sessao
		Filme filmeTemporal = sessionTemporal.getFilme();
		
		// Recuperando a imagem e colocando no filme da sessao
		filmes.uploadImageSubmit();
		
		assertEquals( filmeTemporal.getImagemUrl(), BlobstoreServiceMock.stringUploadCreated );
	}
	
	
    /**
     * Neste teste, inserimos um novo filme e salvado no momento de inserir a nova imagem
     */
	@Test
	public void inserirNovoFilme(){
		//Criando um datastore local para poder gerar os Keys
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Key ancestor = KeyFactory.createKey("filme", 123);
		
		Session sessionTemporal = new Session();
		sessionTemporal.setFilme( new Filme(ancestor,"Filme Test", 0, BlobstoreServiceMock.stringUploadCreated ) );
		
		EntityManagerMock emm = new EntityManagerMock();
		FilmesController filmes = getFilmesControllerDefault( sessionTemporal, emm);
		
		// Formulario para preparar o envio da imagem
		filmes.uploadImage();
				
		// Referencia ao filme da sessao
		Filme filmeTemporal = sessionTemporal.getFilme();
				
		// Recuperando a imagem e colocando no filme da sessao
		filmes.uploadImageSubmit();
		
		assertEquals( emm.getElementById(ancestor.getId()), filmeTemporal );		
	}
	
	/**
	 * Neste teste, mostramos o json que iria vir para mostrar nenhum filme
	 */
	@Test
	public void mostrar0Filmes(){
		FilmesController controller = getFilmesControllerDefault( new Session(), new EntityManagerMock());
		
		assertEquals( "{\"filmes\":[]}", controller.showFilmesAsJSON(0));
	}
	
	/**
	 * Neste teste, mostramos o json de 5 filmes
	 */
	@Test
	public void mostrar5Filmes(){//Criando um datastore local para poder gerar os Keys
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Key ancestor = null;
		
		Session sessionTemporal = new Session();
		EntityManagerMock emm = new EntityManagerMock();
		FilmesController controller = getFilmesControllerDefault( sessionTemporal, emm);
		
		ancestor = KeyFactory.createKey("filme", 1);
		controller.adiciona( new Filme(ancestor,"Filme Test 1", 0, BlobstoreServiceMock.stringUploadCreated ) );
		controller.uploadImageSubmit();
		
		ancestor = KeyFactory.createKey("filme", 2);
		controller.adiciona( new Filme(ancestor,"Filme Test 2", 0, BlobstoreServiceMock.stringUploadCreated ) );
		controller.uploadImageSubmit();
		
		ancestor = KeyFactory.createKey("filme", 3);
		controller.adiciona( new Filme(ancestor,"Filme Test 3", 0, BlobstoreServiceMock.stringUploadCreated ) );
		controller.uploadImageSubmit();
		
		ancestor = KeyFactory.createKey("filme", 4);
		controller.adiciona( new Filme(ancestor,"Filme Test 4", 0, BlobstoreServiceMock.stringUploadCreated ) );
		controller.uploadImageSubmit();
		
		ancestor = KeyFactory.createKey("filme", 5);
		controller.adiciona( new Filme(ancestor,"Filme Test 5", 0, BlobstoreServiceMock.stringUploadCreated ) );
		controller.uploadImageSubmit();
		
		String json = "{\"filmes\":["+
						"{\"id\":\"filme(1)\",\"nome\":\"Filme Test 1\",\"img\":\"CODIGOGERADO-ACTIONUPLOAD\"},"+
						"{\"id\":\"filme(2)\",\"nome\":\"Filme Test 2\",\"img\":\"CODIGOGERADO-ACTIONUPLOAD\"},"+
						"{\"id\":\"filme(3)\",\"nome\":\"Filme Test 3\",\"img\":\"CODIGOGERADO-ACTIONUPLOAD\"},"+
						"{\"id\":\"filme(4)\",\"nome\":\"Filme Test 4\",\"img\":\"CODIGOGERADO-ACTIONUPLOAD\"},"+
						"{\"id\":\"filme(5)\",\"nome\":\"Filme Test 5\",\"img\":\"CODIGOGERADO-ACTIONUPLOAD\"}"+
					  "]}";
		
		assertEquals( 5, emm.countElements() );
		assertEquals( json, controller.showFilmesAsJSON(0));
	}
	
	/**
	 * Neste teste, lemos a quantidade de elementos na variavel QUANTIDADE no arquivo config.properties. 
	 * O valor inicial é 5
	 */
	@Test
	public void lerNdoArquivoConfiguracao() {
		FilmesController controller = getFilmesControllerDefault( new Session(), new EntityManagerMock());
		
		assertEquals(5, controller.getNumeroFilmesParaEscolher());
	}
	
	
	
	/*
	 * Utilitarios
	 */
	private final LocalServiceTestHelper helper =
			new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	
	@Before
	public void setUp() {
		helper.setUp();
	}
	
	@After
	public void tearDown() {
		helper.tearDown();
	}
	
	private final FilmesController getFilmesControllerDefault( Session sessionTemporal, EntityManager em ){
		return new FilmesController(
				sessionTemporal,
				new FilmeDao( em ), 
				new HttpServletRequestMock(), 
				new MockResult()
				);
	}
}

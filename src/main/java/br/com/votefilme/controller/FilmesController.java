package br.com.votefilme.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.votefilme.dao.FilmeDao;
import br.com.votefilme.model.Filme;
import br.com.votefilme.session.Session;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;


@Resource
public class FilmesController {
	private FilmeDao dao;
	
	private final Session session;
	private final HttpServletRequest req;
	private final BlobstoreService blobstoreService;
    private final ImagesService imagesService;
    private final Result result;
    
    public FilmesController(Session session, FilmeDao dao, HttpServletRequest req, Result result){
    	this.session = session;
    	this.dao = dao;
    	this.req = req;
    	this.result = result;
    	
    	blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
        imagesService = ImagesServiceFactory.getImagesService();
    }
    
    /*public FilmesController(Session session, FilmeDao dao, HttpServletRequest req, Result result, BlobstoreService blobStoreService, ImagesService imageService){
    	this.session = session;
    	this.dao = dao;
    	this.req = req;
    	this.result = result;
    	
    	this.blobstoreService = blobStoreService;
    	this.imagesService = imageService;
    }*/
    
    public void index(){
    	
    }
    
    public void uploadImage(){
    	result.include("filme",session.getFilme());
        result.include("uploadUrl", blobstoreService.createUploadUrl("/filmes/uploadImageSubmit"));
    }

    /**
     * Inserindo uma imagem 
     */
    @Post
	public void uploadImageSubmit() {
    	Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(this.req);
    	
    	Filme filme = session.getFilme();
    	if( blobs.containsKey("imagem")){
            List<BlobKey> listBlobKeys = blobs.get("imagem");
            try{
                    ServingUrlOptions urlOptions = ServingUrlOptions.Builder.withBlobKey(listBlobKeys.get(0));
                    String url = imagesService.getServingUrl(urlOptions);
                    filme.setImagemUrl( url );
            }catch(Exception ex){
                    ex.printStackTrace();
            }
    	}
    	
	    dao.adiciona(filme);
	    session.setFilme(null);
	
	    result.redirectTo(FilmesController.class).index();
	}
    
    @Post
    public void showFilmes(){
    	
    }
    
    
    /**
     * Transforma uma lista aleatoria de n filmes, a formato JSON 
     * 
     * @param n
     * @return
     */
    public final String showFilmesAsJSON( int n ) {
    	List<Filme> filmes = dao.listaAleatoria(n);
    	
    	StringBuilder buf = new StringBuilder();
    	buf.append("{\"filmes\":[");
    	for( int i=0; i<filmes.size(); i++){
    		buf.append("{\"id\":\""+filmes.get(i).getId()+"\",");
    		buf.append("\"nome\":\""+filmes.get(i).getNome()+"\",");
    		buf.append("\"img\":\""+filmes.get(i).getImagemUrl()+"\"}");
    		if( i+1 < filmes.size() ) buf.append(",");
    	}
    	buf.append("]}");
    	
    	return buf.toString();
    }
    
    /**
     * Lê do a quantidade de elementos do arquivo de configuracao "config.properties"
     * @return
     */
    public final int getNumeroFilmesParaEscolher() {
			
		Properties props = new Properties();
		try {
			InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
			if( input!= null)
				props.load(input);
		} catch(IOException ioex ){
			return 0;
		}
		
		if( props.containsKey("QUANTIDADE") ){
			try {
				return Integer.parseInt(props.getProperty("QUANTIDADE"));
			} catch(NumberFormatException nfe){
				return 0;
			}
		}
		
    	return 0;
    }

    @Post
	public void adiciona(Filme filme) {
		session.setFilme(filme);
		result.redirectTo(FilmesController.class).uploadImage();
	}
}

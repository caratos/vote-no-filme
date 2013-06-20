package br.com.votefilme.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.vraptor.ioc.Component;
import br.com.votefilme.model.Usuario;

@Component
public class UsuarioDao {
	private final EntityManager em;

	public UsuarioDao(EntityManager em) {
		super();
		this.em = em;
	}
	
	public Usuario buscar( Usuario usuario ){
		Query query = em.createQuery("SELECT x FROM "+Usuario.class.getName()+ " x WHERE x.email = :email AND x.nome = :nome" );
        query.setParameter("email", usuario.getEmail());
        query.setParameter("nome", usuario.getNome());
        
        List<Usuario> result = (List<Usuario>)query.getResultList();

        if( result == null || result.isEmpty() )
                return null;
        

        return result.get(0);
	}

	public void adiciona(Usuario usuario) {
		em.persist(usuario);
	}

	public void atualiza(Usuario usuario) {
		if( usuario.getId() != null )
			em.merge(usuario);
		
	}
	
}

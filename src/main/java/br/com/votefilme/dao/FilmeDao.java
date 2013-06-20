package br.com.votefilme.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.vraptor.ioc.Component;
import br.com.votefilme.model.Filme;

import com.google.appengine.api.datastore.Key;

@Component
public class FilmeDao {
	private final EntityManager em;

	public FilmeDao(EntityManager em) {
		super();
		this.em = em;
	}

	public void adiciona(Filme filme) {
		em.persist(filme);
	}

	public List<Filme> listaAleatoria(int n) {
		Query query = em.createQuery("SELECT x FROM " + Filme.class.getName()
				+ " x ORDER BY RAND() LIMIT " + n);
		return (List<Filme>) query.getResultList();
	}

	public List<Filme> getAllInOrder() {
		Query query = em.createQuery("SELECT x FROM " + Filme.class.getName()
				+ "x ORDER BY x.votos DESC");
		return (List<Filme>) query.getResultList();
	}

	/**
	 * Obtem um filme pelo id
	 * 
	 * @param id
	 * @return
	 */
	public Filme getById(Key id) {
		Query query = em.createQuery("SELECT x FROM " + Filme.class.getName()
				+ " x WHERE x.id = :id");
		query.setParameter("id", id);
		
		List<Filme> result = (List<Filme>) query.getResultList();

		if (result == null || result.isEmpty())
			return null;
		

		return result.get(0);
	}
}

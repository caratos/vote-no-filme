package br.com.votefilme.test.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Parameter;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.Metamodel;

import br.com.votefilme.model.Filme;
import br.com.votefilme.model.Usuario;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class EntityManagerMock implements EntityManager {
	private Map< Long, Object > data;
	
	public EntityManagerMock(){
		data = new HashMap<Long, Object>();
	}
	
	public Object getElementById( Long id ) {
		if( data.containsKey(id ) )
			return data.get(id);
		return null;
	}
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Query createNamedQuery(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Query createNativeQuery(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Query createNativeQuery(String arg0, Class arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Query createNativeQuery(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Query createQuery(String queryString) {
		return new QueryDefault(data, queryString);
	}
	@Override
	public <T> T find(Class<T> arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Object getDelegate() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FlushModeType getFlushMode() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> T getReference(Class<T> arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public EntityTransaction getTransaction() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void joinTransaction() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void lock(Object arg0, LockModeType arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public <T> T merge(T arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void persist(Object obj ) {
		if( obj instanceof Filme ){
			Filme filme = (Filme)obj;
			if( filme.getId() != null )
				data.put( filme.getId().getId(), filme);
			else {
				filme.setId( KeyFactory.createKey("filme", (int)(Math.random()*10000) ) );
				data.put( filme.getId().getId(), filme);
			}
		}
		else if( obj instanceof Usuario ){
			Usuario usuario = (Usuario)obj;
			if( usuario.getId() != null )
				data.put( usuario.getId().getId(), obj);
			else {
				usuario.setId( KeyFactory.createKey("usuario", (int)(Math.random()*10000) ) );
				data.put( usuario.getId().getId(), usuario);
			}
		}
		
	}
	@Override
	public void refresh(Object arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void remove(Object arg0) {
		data.remove(arg0.getClass());
		
	}
	@Override
	public void setFlushMode(FlushModeType arg0) {
		// TODO Auto-generated method stub
		
	}

	public int countElements() {
		return data.size();
	}

	@Override
	public <T> TypedQuery<T> createNamedQuery(String arg0, Class<T> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> TypedQuery<T> createQuery(CriteriaQuery<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> TypedQuery<T> createQuery(String arg0, Class<T> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void detach(Object arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> T find(Class<T> arg0, Object arg1, Map<String, Object> arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T find(Class<T> arg0, Object arg1, LockModeType arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T find(Class<T> arg0, Object arg1, LockModeType arg2,
			Map<String, Object> arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityManagerFactory getEntityManagerFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LockModeType getLockMode(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Metamodel getMetamodel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void lock(Object arg0, LockModeType arg1, Map<String, Object> arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Object arg0, Map<String, Object> arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Object arg0, LockModeType arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Object arg0, LockModeType arg1, Map<String, Object> arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProperty(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> T unwrap(Class<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}


class QueryDefault implements Query {

	private Map<Long, Object> data;
	
	public QueryDefault(Map<Long, Object> data, String strClass) {
		super();
		
		//copia da data
		this.data = new HashMap<Long, Object>();
		for( Long k : data.keySet() ) {
			if( strClass.contains("Filme")){
				if( data.get(k) instanceof Filme )
					this.data.put(k, data.get(k));
			}
			else {
				if( data.get(k) instanceof Usuario )
					this.data.put(k, data.get(k));
			}
		}
		
	}

	@Override
	public int executeUpdate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List getResultList() {
		return new ArrayList<Object>(data.values());
	}

	@Override
	public Object getSingleResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setFirstResult(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setFlushMode(FlushModeType arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setHint(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setMaxResults(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setParameter(String arg, Object obj) {
		if( obj instanceof Key ){
			if( data.containsKey( ((Key)obj).getId() ) ) {
					Map<Long,Object> novo = new HashMap<Long, Object>();
					novo.put( ((Key)obj).getId(), data.get(((Key)obj).getId()) );
					data = novo;
			}
		}
		else if( arg.equals("nome") ){
			Map<Long,Object> novo = new HashMap<Long, Object>();
			for( Map.Entry<Long, Object> entry : data.entrySet() ){
				if( entry.getValue() instanceof Usuario ){
					Usuario tmp = (Usuario)entry.getValue();
					if(tmp.getNome().equals(obj.toString()))
							novo.put(entry.getKey(), entry.getValue());
				}
			}
			data = novo;
		}
		else if( arg.equals("email")){
			Map<Long,Object> novo = new HashMap<Long, Object>();
			for( Map.Entry<Long, Object> entry : data.entrySet() ){
				if( entry.getValue() instanceof Usuario ){
					Usuario tmp = (Usuario)entry.getValue();
					if(tmp.getEmail().equals(obj.toString()))
							novo.put(entry.getKey(), entry.getValue());
				}
			}
			data = novo;
		}
		return this;
	}

	@Override
	public Query setParameter(int arg0, Object arg1) {
		return this;
	}

	@Override
	public Query setParameter(String arg0, Date arg1, TemporalType arg2) {
		return this;
	}

	@Override
	public Query setParameter(String arg0, Calendar arg1, TemporalType arg2) {
		return this;
	}

	@Override
	public Query setParameter(int arg0, Date arg1, TemporalType arg2) {
		return this;
	}

	@Override
	public Query setParameter(int arg0, Calendar arg1, TemporalType arg2) {
		return this;
	}

	@Override
	public int getFirstResult() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FlushModeType getFlushMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getHints() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LockModeType getLockMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxResults() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Parameter<?> getParameter(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Parameter<?> getParameter(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Parameter<T> getParameter(String arg0, Class<T> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Parameter<T> getParameter(int arg0, Class<T> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getParameterValue(Parameter<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getParameterValue(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getParameterValue(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isBound(Parameter<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Query setLockMode(LockModeType arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Query setParameter(Parameter<T> arg0, T arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setParameter(Parameter<Calendar> arg0, Calendar arg1,
			TemporalType arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setParameter(Parameter<Date> arg0, Date arg1, TemporalType arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
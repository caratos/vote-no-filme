package br.com.votefilme.dao;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;


@Component
@ApplicationScoped
public class EntityManagerFactoryCreator implements ComponentFactory<EntityManagerFactory>{

        private EntityManagerFactory factory;

        @PostConstruct
        public void create() {
                factory = Persistence.createEntityManagerFactory("transactions-optional");
        }

        public EntityManagerFactory getInstance() {
                return factory;
        }

        @PreDestroy
        public void destroy() {
                factory.close();
        }
        
}
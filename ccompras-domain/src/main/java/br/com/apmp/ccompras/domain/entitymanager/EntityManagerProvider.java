package br.com.apmp.ccompras.domain.entitymanager;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerProvider {

	@Produces
	@PersistenceContext( unitName = "ccomprasPU" )
	private EntityManager entityManager;

}

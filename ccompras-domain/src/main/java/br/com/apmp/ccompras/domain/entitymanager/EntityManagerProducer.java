package br.com.apmp.ccompras.domain.entitymanager;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerProducer {

	@Produces
	@PersistenceContext( unitName = "apmpPU" )
	private EntityManager entityManager;

}

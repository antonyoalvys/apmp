package br.com.apmp.ccompras.domain.repository.impl;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.apmp.ccompras.domain.entities.State;
import br.com.apmp.ccompras.domain.repository.StateRepository;

@Named
@Dependent
public class StateRepositoryImpl extends BaseRepositoryImpl<State> implements StateRepository {

	private static final long serialVersionUID = -1306039151512134985L;

	@PersistenceContext
	private EntityManager em;

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	@Override
	public Class<State> getClassT() {
		return State.class;
	}

	@Override
	public State findByName( String name ) {
		return null;
	}

}

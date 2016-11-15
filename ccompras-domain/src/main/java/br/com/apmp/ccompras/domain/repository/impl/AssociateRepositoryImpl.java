package br.com.apmp.ccompras.domain.repository.impl;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.apmp.ccompras.domain.entities.Associate;
import br.com.apmp.ccompras.domain.repository.AssociateRepository;

@Named
@Dependent
public class AssociateRepositoryImpl extends BaseRepositoryImpl<Associate> implements AssociateRepository, Serializable {

	private static final long serialVersionUID = -2405170068869477294L;

	@Inject
	private EntityManager em;

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	@Override
	public Class<Associate> getClassT() {
		return Associate.class;
	}

}

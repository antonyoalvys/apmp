package br.com.apmp.ccompras.domain.repository.impl;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.apmp.ccompras.domain.entities.Permission;
import br.com.apmp.ccompras.domain.repository.PermissionRepository;

@Named
@Dependent
public class PermissionRepositoryImpl extends BaseRepositoryImpl<Permission> implements PermissionRepository {

	private static final long serialVersionUID = 2058220605705420890L;
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public EntityManager getEntityManager() {
		return this.em;
	}

	@Override
	public Class<Permission> getClassT() {
		return Permission.class;
	}

}

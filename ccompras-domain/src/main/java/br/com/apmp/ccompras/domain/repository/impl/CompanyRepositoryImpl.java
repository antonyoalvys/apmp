package br.com.apmp.ccompras.domain.repository.impl;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.apmp.ccompras.domain.entities.Company;
import br.com.apmp.ccompras.domain.repository.CompanyRepository;

@Named
@Dependent
public class CompanyRepositoryImpl extends BaseRepositoryImpl<Company> implements CompanyRepository {

	private static final long serialVersionUID = -565522666966305843L;

	@PersistenceContext
	private EntityManager em;

	@Override
	public Company findByCnpj( String cnpj ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Company> findByName( String name ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityManager getEntityManager() {
		return this.em;
	}

	@Override
	public Class<Company> getClassT() {
		return Company.class;
	}

}

package br.com.apmp.ccompras.domain.repository.impl;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.apmp.ccompras.domain.entities.City;
import br.com.apmp.ccompras.domain.repository.CityRepository;

@Named
@Dependent
public class CityRepositoryImpl extends BaseRepositoryImpl<City> implements CityRepository {

	private static final long serialVersionUID = 8465549197776107110L;

	@PersistenceContext
	private EntityManager em;

	@Override
	public City findByName( String name ) {
		return null;
	}

	@Override
	public EntityManager getEntityManager() {
		return this.em;
	}

	@Override
	public Class<City> getClassT() {
		return City.class;
	}

}

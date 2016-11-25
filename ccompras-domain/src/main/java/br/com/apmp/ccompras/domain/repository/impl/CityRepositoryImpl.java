package br.com.apmp.ccompras.domain.repository.impl;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;

import br.com.apmp.ccompras.domain.entities.City;
import br.com.apmp.ccompras.domain.entities.QCity;
import br.com.apmp.ccompras.domain.repository.CityRepository;

@Named
@Dependent
public class CityRepositoryImpl extends BaseRepositoryImpl<City> implements CityRepository {

	private static final long serialVersionUID = 8465549197776107110L;

	@PersistenceContext
	private EntityManager em;

	@Override
	public EntityManager getEntityManager() {
		return this.em;
	}

	@Override
	public Class<City> getClassT() {
		return City.class;
	}

	@Override
	public List<City> autocomplete( String queryName, String stateName ) {
		QCity qCity = QCity.city;
		BooleanBuilder bb = new BooleanBuilder();
		JPAQuery<City> query = new JPAQuery<City>( em );

		if ( queryName != null && !queryName.trim().isEmpty() )
			bb.and( qCity.name.containsIgnoreCase( queryName ) );
		if ( stateName != null )
			bb.and( qCity.state.name.equalsIgnoreCase( stateName ) );
		return query.from( qCity ).where( bb ).orderBy( qCity.name.asc() ).fetch();
	}

	@Override
	public List<String> autocompleteCitiesNames( String queryName, String stateName ) {
		QCity qCity = QCity.city;
		BooleanBuilder bb = new BooleanBuilder();
		JPAQuery<City> query = new JPAQuery<City>( em );

		if ( queryName != null && !queryName.trim().isEmpty() )
			bb.and( qCity.name.containsIgnoreCase( queryName ) );
		if ( stateName != null )
			bb.and( qCity.state.name.equalsIgnoreCase( stateName ) );
		return query.select( qCity.name ).from( qCity ).where( bb ).orderBy( qCity.name.asc() ).fetch();
	}

}

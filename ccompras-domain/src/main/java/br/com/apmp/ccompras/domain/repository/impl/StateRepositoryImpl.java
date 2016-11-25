package br.com.apmp.ccompras.domain.repository.impl;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;

import br.com.apmp.ccompras.domain.entities.QState;
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
	public List<State> findByName( String name ) {
		QState qState = QState.state;
		BooleanBuilder bb = new BooleanBuilder();
		JPAQuery<State> query = new JPAQuery<State>( em );

		if ( name != null && !name.trim().isEmpty() )
			bb.and( qState.name.containsIgnoreCase( name ) );

		return query.from( qState ).where( bb ).orderBy( qState.name.asc() ).fetch();
	}

	@Override
	public List<String> autocompleteNames( String name ) {
		QState qState = QState.state;
		BooleanBuilder bb = new BooleanBuilder();
		JPAQuery<State> query = new JPAQuery<State>( em );

		if ( name != null && !name.trim().isEmpty() )
			bb.and( qState.name.containsIgnoreCase( name ) );

		return query.select( qState.name ).where( bb ).orderBy( qState.name.asc() ).from( qState ).fetch();
	}

	@Override
	public List<String> autocompleteShortNames( String shortName ) {
		QState qState = QState.state;
		BooleanBuilder bb = new BooleanBuilder();
		JPAQuery<State> query = new JPAQuery<State>( em );

		if ( shortName != null && !shortName.trim().isEmpty() )
			bb.and( qState.shortName.containsIgnoreCase( shortName ) );

		return query.select( qState.shortName ).where( bb ).orderBy( qState.shortName.asc() ).from( qState ).fetch();
	}

}

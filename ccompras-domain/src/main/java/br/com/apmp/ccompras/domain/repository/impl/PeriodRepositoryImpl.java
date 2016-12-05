package br.com.apmp.ccompras.domain.repository.impl;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;

import br.com.apmp.ccompras.domain.entities.Period;
import br.com.apmp.ccompras.domain.entities.QPeriod;
import br.com.apmp.ccompras.domain.enums.PeriodStatus;
import br.com.apmp.ccompras.domain.repository.PeriodRepository;

@Named
@Dependent
public class PeriodRepositoryImpl extends BaseRepositoryImpl<Period> implements PeriodRepository {

	private static final long serialVersionUID = -1331308931352935314L;

	@PersistenceContext
	private EntityManager em;

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	@Override
	public Class<Period> getClassT() {
		return Period.class;
	}

	@Override
	public List<Period> findByEntity( Period entity ) {
		QPeriod qPeriod = QPeriod.period;
		BooleanBuilder bb = new BooleanBuilder();
		JPQLQuery<Period> query = new JPAQuery<Period>( em );

		if ( entity == null )
			return query.from( qPeriod ).orderBy( qPeriod.description.desc() ).fetch();

		if ( entity.getDescription() != null && !entity.getDescription().trim().isEmpty() )
			bb.and( qPeriod.description.containsIgnoreCase( entity.getDescription() ) );
		if ( entity.getPeriodStatus() != null )
			bb.and( qPeriod.periodStatus.eq( entity.getPeriodStatus() ) );

		return query.from( qPeriod ).where( bb ).orderBy( qPeriod.description.desc() ).fetch();
	}

	@Override
	public List<Period> findByDescription( String queryPeriod ) {
		QPeriod qPeriod = QPeriod.period;
		BooleanBuilder bb = new BooleanBuilder();
		JPQLQuery<Period> query = new JPAQuery<Period>( em );

		bb.and( qPeriod.description.containsIgnoreCase( queryPeriod ) );
		bb.and( qPeriod.periodStatus.eq( PeriodStatus.OPEN ) );

		return query.from( qPeriod ).where( bb ).orderBy( qPeriod.description.desc() ).fetch();

	}

}

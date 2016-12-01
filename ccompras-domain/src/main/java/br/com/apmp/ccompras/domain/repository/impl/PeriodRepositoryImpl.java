package br.com.apmp.ccompras.domain.repository.impl;

import java.time.LocalDate;
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
import br.com.apmp.ccompras.domain.repository.PeriodRepository;

@Named
@Dependent
public class PeriodRepositoryImpl extends BaseRepositoryImpl<Period> implements PeriodRepository {

	private static final long serialVersionUID = -1331308931352935314L;

	@PersistenceContext
	private EntityManager em;

	@Override
	public Period findByDate( LocalDate date ) {
		QPeriod qPeriod = QPeriod.period;
		BooleanBuilder bb = new BooleanBuilder();
		JPQLQuery<Period> query = new JPAQuery<Period>( em );

		if ( date == null )
			return null;

		bb.and( qPeriod.beginDate.loe( date ) );
		bb.and( qPeriod.endDate.goe( date ) );

		return query.from( qPeriod ).fetchOne();
	}

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
			return query.from( qPeriod ).orderBy( qPeriod.endDate.desc() ).fetch();

		if ( entity.getBeginDate() != null )
			bb.and( qPeriod.beginDate.goe( entity.getBeginDate() ) );
		if ( entity.getEndDate() != null )
			bb.and( qPeriod.endDate.loe( entity.getEndDate() ) );
		if ( entity.getPeriodStatus() != null )
			bb.and( qPeriod.periodStatus.eq( entity.getPeriodStatus() ) );

		return query.from( qPeriod ).where( bb ).orderBy( qPeriod.endDate.desc() ).fetch();
	}

	@Override
	public Period findByBeginDate( Period entity ) {
		QPeriod qPeriod = QPeriod.period;
		BooleanBuilder bb = new BooleanBuilder();
		JPQLQuery<Period> query = new JPAQuery<Period>( em );

		if ( entity == null || entity.getBeginDate() == null )
			throw new NullPointerException();

		bb.and( qPeriod.beginDate.loe( entity.getBeginDate() ) );
		bb.and( qPeriod.endDate.goe( entity.getBeginDate() ) );

		return query.from( qPeriod ).where( bb ).fetchOne();

	}
	
	@Override
	public Period findByEndDate( Period entity ) {
		QPeriod qPeriod = QPeriod.period;
		BooleanBuilder bb = new BooleanBuilder();
		JPQLQuery<Period> query = new JPAQuery<Period>( em );

		if ( entity == null || entity.getEndDate() == null )
			throw new NullPointerException();

		bb.and( qPeriod.beginDate.loe( entity.getEndDate() ) );
		bb.and( qPeriod.endDate.goe( entity.getEndDate() ) );

		return query.from( qPeriod ).where( bb ).fetchOne();
	}

}

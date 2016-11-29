package br.com.apmp.ccompras.domain.repository.impl;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;

import br.com.apmp.ccompras.domain.entities.FileCovenant;
import br.com.apmp.ccompras.domain.entities.Period;
import br.com.apmp.ccompras.domain.entities.QFileCovenant;
import br.com.apmp.ccompras.domain.repository.FileCovenantRepository;

@Named
@Dependent
public class FileCovenantRepositoryImpl extends BaseRepositoryImpl<FileCovenant> implements FileCovenantRepository {

	private static final long serialVersionUID = -1438013831626247960L;

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<FileCovenant> findByPeriod( Period beginPeriod, Period endPeriod ) {
		QFileCovenant qFileCovenant = QFileCovenant.fileCovenant;
		BooleanBuilder bb = new BooleanBuilder();
		JPQLQuery<FileCovenant> query = new JPAQuery<FileCovenant>( em );

		if ( beginPeriod != null ) {
			if ( beginPeriod.getBeginDate() != null )
				bb.and( qFileCovenant.period.beginDate.goe( beginPeriod.getBeginDate() ) );
			if ( beginPeriod.getEndDate() != null )
				bb.and( qFileCovenant.period.endDate.goe( beginPeriod.getEndDate() ) );
		}

		if ( endPeriod != null ) {
			if ( endPeriod.getBeginDate() != null )
				bb.and( qFileCovenant.period.beginDate.loe( endPeriod.getBeginDate() ) );
			if ( endPeriod.getEndDate() != null )
				bb.and( qFileCovenant.period.endDate.loe( endPeriod.getEndDate() ) );
		}

		return query.from( qFileCovenant ).orderBy( qFileCovenant.period.endDate.desc() ).fetch();
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	@Override
	public Class<FileCovenant> getClassT() {
		return FileCovenant.class;
	}

}

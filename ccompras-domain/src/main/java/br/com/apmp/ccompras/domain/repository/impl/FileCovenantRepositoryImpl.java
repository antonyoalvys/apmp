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
	public List<FileCovenant> findByPeriod( Period period ) {
		QFileCovenant qFileCovenant = QFileCovenant.fileCovenant;
		BooleanBuilder bb = new BooleanBuilder();
		JPQLQuery<FileCovenant> query = new JPAQuery<FileCovenant>( em );

		if ( period != null ) {
			if ( period.getDescription() != null && !period.getDescription().isEmpty() )
				bb.and( qFileCovenant.period.description.eq( period.getDescription() ) );
		}

		return query.from( qFileCovenant ).where( bb ).orderBy( qFileCovenant.period.description.desc() ).fetch();
	}

	@Override
	public FileCovenant findByPeriodId( Long id ) {
		QFileCovenant qFileCovenant = QFileCovenant.fileCovenant;
		BooleanBuilder bb = new BooleanBuilder();
		JPQLQuery<FileCovenant> query = new JPAQuery<FileCovenant>( em );

		if ( id == null )
			return null;

		bb.and( qFileCovenant.period.id.eq( id ) );

		return query.from( qFileCovenant ).where( bb ).fetchOne();
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

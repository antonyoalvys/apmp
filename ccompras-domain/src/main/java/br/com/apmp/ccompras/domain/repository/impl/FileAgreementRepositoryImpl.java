package br.com.apmp.ccompras.domain.repository.impl;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;

import br.com.apmp.ccompras.domain.entities.FileAgreement;
import br.com.apmp.ccompras.domain.entities.Period;
import br.com.apmp.ccompras.domain.entities.QFileAgreement;
import br.com.apmp.ccompras.domain.repository.FileAgreementRepository;

@Named
@Dependent
public class FileAgreementRepositoryImpl extends BaseRepositoryImpl<FileAgreement> implements FileAgreementRepository {

	private static final long serialVersionUID = -1438013831626247960L;

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<FileAgreement> findByPeriod( Period period ) {
		QFileAgreement qFileCovenant = QFileAgreement.fileAgreement;
		BooleanBuilder bb = new BooleanBuilder();
		JPQLQuery<FileAgreement> query = new JPAQuery<FileAgreement>( em );

		if ( period != null ) {
			if ( period.getDescription() != null && !period.getDescription().isEmpty() )
				bb.and( qFileCovenant.period.description.eq( period.getDescription() ) );
		}

		return query.from( qFileCovenant ).where( bb ).orderBy( qFileCovenant.period.description.desc() ).fetch();
	}

	@Override
	public FileAgreement findByPeriodId( Long id ) {
		QFileAgreement qFileCovenant = QFileAgreement.fileAgreement;
		BooleanBuilder bb = new BooleanBuilder();
		JPQLQuery<FileAgreement> query = new JPAQuery<FileAgreement>( em );

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
	public Class<FileAgreement> getClassT() {
		return FileAgreement.class;
	}

}

package br.com.apmp.ccompras.domain.repository.impl;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;

import br.com.apmp.ccompras.domain.entities.Agreement;
import br.com.apmp.ccompras.domain.entities.QAgreement;
import br.com.apmp.ccompras.domain.repository.AgreementRepository;

@Named
@Dependent
public class AgreementRepositoryImpl extends BaseRepositoryImpl<Agreement> implements AgreementRepository {

	private static final long serialVersionUID = -228506115982298771L;

	@PersistenceContext
	private EntityManager em;

	@Override
	public EntityManager getEntityManager() {
		return this.em;
	}

	@Override
	public Class<Agreement> getClassT() {
		return Agreement.class;
	}

	@Override
	public List<Agreement> findByName( String queryName ) {
		BooleanBuilder bb = new BooleanBuilder();
		QAgreement qAgreement = QAgreement.agreement;
		JPQLQuery<Agreement> query = new JPAQuery<Agreement>( this.em );

		bb.and( qAgreement.active.isTrue() );

		if ( queryName != null && !queryName.trim().isEmpty() )
			bb.and( qAgreement.name.containsIgnoreCase( queryName ) );

		return query.from( qAgreement ).where( bb ).orderBy( qAgreement.name.asc() ).fetch();
	}

}

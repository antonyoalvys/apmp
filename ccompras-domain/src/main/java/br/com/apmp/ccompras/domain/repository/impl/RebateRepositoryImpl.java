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
import br.com.apmp.ccompras.domain.entities.Company;
import br.com.apmp.ccompras.domain.entities.QRebate;
import br.com.apmp.ccompras.domain.entities.Rebate;
import br.com.apmp.ccompras.domain.repository.RebateRepository;

@Named
@Dependent
public class RebateRepositoryImpl extends BaseRepositoryImpl<Rebate> implements RebateRepository {

	private static final long serialVersionUID = 6005952070525894008L;

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Rebate> findByEntity( Rebate entity ) {
		BooleanBuilder bb = new BooleanBuilder();
		QRebate qRebate = QRebate.rebate;
		JPQLQuery<Rebate> query = new JPAQuery<Rebate>( this.em );

		if ( entity != null && entity.getCompany() != null )
			bb.and( qRebate.company.id.eq( entity.getCompany().getId() ) );

		if ( entity != null && entity.getAgreement() != null )
			bb.and( qRebate.agreement.id.eq( entity.getAgreement().getId() ) );

		return query.from( qRebate ).where( bb ).orderBy( qRebate.percentage.asc() ).fetch();
	}

	@Override
	public Rebate findByCompanyAndAgreement( Company company, Agreement agreement ) {
		BooleanBuilder bb = new BooleanBuilder();
		QRebate qRebate = QRebate.rebate;
		JPQLQuery<Rebate> query = new JPAQuery<Rebate>( this.em );

		if ( company == null || agreement == null )
			return null;

		bb.and( qRebate.company.id.eq( company.getId() ) );

		bb.and( qRebate.agreement.id.eq( agreement.getId() ) );

		return query.from( qRebate ).where( bb ).fetchOne();

	}

	@Override
	public EntityManager getEntityManager() {
		return this.em;
	}

	@Override
	public Class<Rebate> getClassT() {
		return Rebate.class;
	}

}

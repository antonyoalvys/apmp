package br.com.apmp.ccompras.domain.repository.impl;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;

import br.com.apmp.ccompras.domain.entities.Associate;
import br.com.apmp.ccompras.domain.entities.Company;
import br.com.apmp.ccompras.domain.entities.Period;
import br.com.apmp.ccompras.domain.entities.PurchaseTicket;
import br.com.apmp.ccompras.domain.entities.QPurchaseTicket;
import br.com.apmp.ccompras.domain.repository.PurchaseTicketRepository;

@Named
@Dependent
public class PurchaseTicketRepositoryImpl extends BaseRepositoryImpl<PurchaseTicket> implements PurchaseTicketRepository {

	private static final long serialVersionUID = -704198090660310231L;

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<PurchaseTicket> findByCompany( Company company ) {
		return null;
	}

	@Override
	public List<PurchaseTicket> findByAssociate( Associate associate ) {
		return null;
	}

	@Override
	public PurchaseTicket findByCode( String code ) {
		return null;
	}

	@Override
	public EntityManager getEntityManager() {
		return this.em;
	}

	@Override
	public Class<PurchaseTicket> getClassT() {
		return PurchaseTicket.class;
	}

	@Override
	public List<PurchaseTicket> findByEntity( PurchaseTicket entity, Period period ) {
		QPurchaseTicket qPurchaseTicket = QPurchaseTicket.purchaseTicket;
		BooleanBuilder bb = new BooleanBuilder();
		JPAQuery<PurchaseTicket> query = new JPAQuery<PurchaseTicket>( em );

		if ( entity.getCode() != null && !entity.getCode().trim().isEmpty() )
			bb.and( qPurchaseTicket.code.eq( entity.getCode() ) );
		if ( entity.getAssociate() != null )
			bb.and( qPurchaseTicket.associate.id.eq( entity.getAssociate().getId() ) );
		if ( entity.getCompany() != null ) {
			bb.and( qPurchaseTicket.company.id.eq( entity.getCompany().getId() ) );
		}
		if ( period != null ) {
			if ( period.getBeginDate() != null )
				bb.and( qPurchaseTicket.usageDate.goe( period.getBeginDate() ) );
			if ( period.getEndDate() != null )
				bb.and( qPurchaseTicket.usageDate.loe( period.getEndDate() ) );
		}

		return query.from( qPurchaseTicket ).where( bb ).orderBy( qPurchaseTicket.usageDate.desc() ).fetch();
	}

}

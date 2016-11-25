package br.com.apmp.ccompras.domain.repository.impl;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;

import br.com.apmp.ccompras.domain.entities.Associate;
import br.com.apmp.ccompras.domain.entities.QAssociate;
import br.com.apmp.ccompras.domain.repository.AssociateRepository;

@Named
@Dependent
public class AssociateRepositoryImpl extends BaseRepositoryImpl<Associate> implements AssociateRepository, Serializable {

	private static final long serialVersionUID = -2405170068869477294L;

	@PersistenceContext
	private EntityManager em;

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	@Override
	public Class<Associate> getClassT() {
		return Associate.class;
	}

	@Override
	public List<Associate> findByEntity( Associate associateSearch ) {

		QAssociate qAssociate = QAssociate.associate;
		BooleanBuilder bb = new BooleanBuilder();
		JPAQuery<Associate> query = new JPAQuery<Associate>( em );

		bb.and( qAssociate.active.isTrue() );
		
		if ( associateSearch.getName() != null && !associateSearch.getName().trim().isEmpty() )
			bb.and( qAssociate.name.containsIgnoreCase( associateSearch.getName() ) );
		if ( associateSearch.getEnrollment() != null && !associateSearch.getName().trim().isEmpty() )
			bb.and( qAssociate.enrollment.eq( associateSearch.getEnrollment() ) );
		if ( associateSearch.getAssociateType() != null ) {
			bb.and( qAssociate.associateType.eq( associateSearch.getAssociateType() ) );
		}

		return query.from( qAssociate ).where( bb ).orderBy( qAssociate.name.asc() ).fetch();

	}

	@Override
	public void disable( Associate associate ) {
		associate.setActive( false );
		this.save( associate );
	}

}

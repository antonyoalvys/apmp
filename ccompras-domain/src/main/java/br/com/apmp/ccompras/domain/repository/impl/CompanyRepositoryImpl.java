package br.com.apmp.ccompras.domain.repository.impl;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;

import br.com.apmp.ccompras.domain.entities.Company;
import br.com.apmp.ccompras.domain.entities.QCompany;
import br.com.apmp.ccompras.domain.repository.CompanyRepository;

@Named
@Dependent
public class CompanyRepositoryImpl extends BaseRepositoryImpl<Company> implements CompanyRepository {

	private static final long serialVersionUID = -565522666966305843L;

	@PersistenceContext
	private EntityManager em;

	@Override
	public Company findByCnpj( String cnpj ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Company> findByName( String name ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityManager getEntityManager() {
		return this.em;
	}

	@Override
	public Class<Company> getClassT() {
		return Company.class;
	}

	@Override
	public List<Company> findByEntity( Company entity ) {

		QCompany qCompany = QCompany.company;
		BooleanBuilder bb = new BooleanBuilder();
		JPAQuery<Company> query = new JPAQuery<Company>( em );

		bb.and( qCompany.active.isTrue() );

		if ( entity.getName() != null && !entity.getName().trim().isEmpty() )
			bb.and( qCompany.name.containsIgnoreCase( entity.getName() ).or( qCompany.fantasyName.containsIgnoreCase( entity.getName() ) ) );
		if ( entity.getCnpj() != null && !entity.getCnpj().trim().isEmpty() )
			bb.and( QCompany.company.cnpj.eq( entity.getCnpj() ) );

		return query.from( qCompany ).where( bb ).orderBy( qCompany.name.asc() ).fetch();

	}

	@Override
	public void disable( Company company ) {
		company.setActive( false );
		this.save( company );
	}

	@Override
	public List<Company> findByNameOrCnpj( String queryCompany ) {
		QCompany qCompany = QCompany.company;
		BooleanBuilder bb = new BooleanBuilder();
		JPAQuery<Company> query = new JPAQuery<Company>( em );

		bb.and( qCompany.active.isTrue() );

		if ( queryCompany == null || queryCompany.isEmpty() )
			return query.from( qCompany ).where( bb ).orderBy( qCompany.name.asc() ).fetch();

		try {
			Integer.parseInt( queryCompany );
			bb.and( qCompany.cnpj.contains( queryCompany ) );
		} catch ( NumberFormatException ex ) {
			bb.and( qCompany.name.containsIgnoreCase( queryCompany ) );
		}

		return query.from( qCompany ).where( bb ).orderBy( qCompany.name.asc() ).fetch();
	}

}

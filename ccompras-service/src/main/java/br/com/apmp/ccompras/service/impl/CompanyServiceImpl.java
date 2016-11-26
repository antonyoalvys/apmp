package br.com.apmp.ccompras.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import br.com.apmp.ccompras.domain.entities.Company;
import br.com.apmp.ccompras.domain.repository.CompanyRepository;
import br.com.apmp.ccompras.service.CompanyService;

@Stateless
@TransactionManagement( TransactionManagementType.CONTAINER )
public class CompanyServiceImpl implements CompanyService {

	private static final long serialVersionUID = 5691589499512483036L;

	@Inject
	private CompanyRepository companyRepository;

	@Override
	@TransactionAttribute( TransactionAttributeType.NEVER )
	public Company findById( Long id ) {
		return this.companyRepository.findById( id );
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.NEVER )
	public Company findByCnpj( String cnpj ) {
		return this.companyRepository.findByCnpj( cnpj );
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.NEVER )
	public List<Company> findByName( String name ) {
		return this.companyRepository.findByName( name );
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.NEVER )
	public List<Company> findAll() {
		return this.companyRepository.findAll();
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.REQUIRED )
	public void save( Company entity ) {
		this.companyRepository.save( entity );
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.REQUIRED )
	public void disable( Company entity ) {
		this.companyRepository.disable( entity );
	}

	@Override
	public List<Company> findByEntity( Company entity ) {
		return this.companyRepository.findByEntity( entity );
	}

	@Override
	public List<Company> findByNameOrCnpj( String queryCompany ) {
		return this.companyRepository.findByNameOrCnpj( queryCompany );
	}

}

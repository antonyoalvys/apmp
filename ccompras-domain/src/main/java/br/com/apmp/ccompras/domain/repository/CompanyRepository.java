package br.com.apmp.ccompras.domain.repository;

import java.util.List;

import br.com.apmp.ccompras.domain.entities.Company;

public interface CompanyRepository extends BaseRepository<Company> {

	public Company findByCnpj( String cnpj );

	public List<Company> findByName( String name );

	public List<Company> findByEntity( Company entity );
	
	public void disable( Company company );

	public List<Company> findByNameOrCnpj( String queryCompany );

}

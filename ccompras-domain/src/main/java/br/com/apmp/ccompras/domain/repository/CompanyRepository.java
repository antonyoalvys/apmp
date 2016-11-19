package br.com.apmp.ccompras.domain.repository;

import java.util.List;

import br.com.apmp.ccompras.domain.entities.Company;

public interface CompanyRepository extends BaseRepository<Company> {

	public Company findByCnpj( String cnpj );

	public List<Company> findByName( String name );

}

package br.com.apmp.ccompras.service;

import java.io.Serializable;
import java.util.List;

import br.com.apmp.ccompras.domain.entities.Company;

public interface CompanyService extends Serializable {

	public Company findById( Long id );

	public Company findByCnpj( String cnpj );

	public List<Company> findByName( String name );

	public List<Company> findAll();

	public void save( Company entity );

	public void disable( Company entity );

	public List<Company> findByEntity( Company entity );

}

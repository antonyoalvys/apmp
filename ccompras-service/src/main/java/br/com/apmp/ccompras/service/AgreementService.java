package br.com.apmp.ccompras.service;

import java.util.List;

import br.com.apmp.ccompras.domain.entities.Agreement;

public interface AgreementService {

	public void save( Agreement entity );

	public void delete( Agreement entity );

	public Agreement findById( Long id );

	public List<Agreement> findByName( String queryName );

	public List<Agreement> findAll();

}

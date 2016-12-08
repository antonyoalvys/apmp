package br.com.apmp.ccompras.domain.repository;

import java.util.List;

import br.com.apmp.ccompras.domain.entities.Agreement;

public interface AgreementRepository extends BaseRepository<Agreement> {

	public List<Agreement> findByName( String queryName );

}

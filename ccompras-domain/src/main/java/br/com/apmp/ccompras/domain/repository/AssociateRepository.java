package br.com.apmp.ccompras.domain.repository;

import java.util.List;

import br.com.apmp.ccompras.domain.entities.Associate;

public interface AssociateRepository extends BaseRepository<Associate> {

	public List<Associate> findByEntity( Associate associateSearch );

}

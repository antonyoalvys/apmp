package br.com.apmp.ccompras.service;

import java.io.Serializable;
import java.util.List;

import br.com.apmp.ccompras.domain.entities.Associate;

public interface AssociateService extends Serializable {

	public void save( Associate entity );

	public void delete( Associate entity );

	public List<Associate> findAll();

	public List<Associate> findByEntity( Associate associateSearch );

	public void disable( Associate associate );

}

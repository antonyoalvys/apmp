package br.com.apmp.ccompras.service;

import java.util.List;

import br.com.apmp.ccompras.domain.entities.Agreement;
import br.com.apmp.ccompras.domain.entities.Company;
import br.com.apmp.ccompras.domain.entities.Rebate;

public interface RebateService {

	public void save( Rebate entity );

	public void delete( Rebate entity );

	public List<Rebate> findByEntity( Rebate entity );

	public Rebate findByCompanyAndAgreement( Company company, Agreement agreement );

	public List<Rebate> findAll();

}

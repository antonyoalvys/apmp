package br.com.apmp.ccompras.domain.repository;

import java.util.List;

import br.com.apmp.ccompras.domain.entities.Agreement;
import br.com.apmp.ccompras.domain.entities.Company;
import br.com.apmp.ccompras.domain.entities.Rebate;

public interface RebateRepository extends BaseRepository<Rebate> {

	public List<Rebate> findByEntity( Rebate entity );

	public Rebate findByCompanyAndAgreement( Company company, Agreement agreement );

}

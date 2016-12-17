package br.com.apmp.ccompras.domain.repository;

import java.util.List;

import br.com.apmp.ccompras.domain.entities.FileAgreement;
import br.com.apmp.ccompras.domain.entities.Period;

public interface FileAgreementRepository extends BaseRepository<FileAgreement> {

	public List<FileAgreement> findByPeriod( Period period );

	public FileAgreement findByPeriodId( Long id );

}

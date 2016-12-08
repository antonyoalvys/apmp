package br.com.apmp.ccompras.service;

import java.io.Serializable;
import java.util.List;

import br.com.apmp.ccompras.domain.entities.FileAgreement;
import br.com.apmp.ccompras.domain.entities.Period;

public interface FileAgreementService extends Serializable {

	public List<FileAgreement> findByPeriod( Period period );

	public void generate( Period period );

	public void delete( FileAgreement entity );
	
	public enum SideFit {
		LEFT,
		RIGHT;

	}

}

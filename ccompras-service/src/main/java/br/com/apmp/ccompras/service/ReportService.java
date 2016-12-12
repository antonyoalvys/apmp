package br.com.apmp.ccompras.service;

import java.io.OutputStream;
import java.io.Serializable;

import br.com.apmp.ccompras.domain.entities.Period;
import br.com.apmp.ccompras.domain.enums.ReportType;

public interface ReportService extends Serializable {

	public void generate( ReportType reportType, Period period, OutputStream responseStream );

}

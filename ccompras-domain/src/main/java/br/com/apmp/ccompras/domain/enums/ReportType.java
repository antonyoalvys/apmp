package br.com.apmp.ccompras.domain.enums;

public enum ReportType {

	ANALYTICAL_REPORT_BY_COMPANY( "Relatório Analítico de Descontos de Convênio por Empresa" );

	private final String description;

	private ReportType( String description ) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}

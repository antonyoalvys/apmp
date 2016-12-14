package br.com.apmp.ccompras.domain.enums;

public enum ReportType {

	ANALYTICAL_REPORT_BY_COMPANY( "Analítico (Por Fornecedor)", "1" ),
	ANALYTICAL_REPORT_BY_ASSOCIATE("Analítico (Por Associado)", "2"),
	SYNTHETIC_REPORT_FOR_PAYROLL("Sintético (Para Folha)", "3");

	private final String description;
	private final String code;

	private ReportType( String description, String code ) {
		this.description = description;
		this.code = code;
	}

	public String getDescription() {
		return description;
	}
	
	public String getCode() {
		return code;
	}

}

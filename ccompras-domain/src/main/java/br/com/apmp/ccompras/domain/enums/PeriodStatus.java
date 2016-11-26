package br.com.apmp.ccompras.domain.enums;

public enum PeriodStatus {

	OPEN( "Aberto" ),
	CLOSE( "Fechado" );

	private String description;

	private PeriodStatus( String description ) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription( String description ) {
		this.description = description;
	}
	
	

}

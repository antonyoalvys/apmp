package br.com.apmp.ccompras.domain.enums;

public enum PhoneType {

	LANDLINE( "Telefone Fixo" ),
	MOBILE( "Telefone Móvel" );

	private String description;

	private PhoneType( String description ) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}

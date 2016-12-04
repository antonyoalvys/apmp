package br.com.apmp.ccompras.jsf.enums;

public enum MaskOptions {

	MOBILE_PHONE_MASK( "(99) 9 9999-9999" ),
	LANDLINE_PHONE_MASK( "(99) 9999-9999" ),
	CNPJ_MASK( "99.999.999/9999-99" ),
	CPF_MASK( "999.999.999-99" ),
	ZIPCODE_MASK( "99999-999" );

	private String description;

	private MaskOptions( String description ) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription( String description ) {
		this.description = description;
	}

}

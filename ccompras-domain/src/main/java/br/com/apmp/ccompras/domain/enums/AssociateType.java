package br.com.apmp.ccompras.domain.enums;

public enum AssociateType {
	PROMOTOR("Promotor"),
	PROCURADOR("Procurador");
	
	private String description;
	
	private AssociateType(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}

}

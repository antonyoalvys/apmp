package br.com.apmp.ccompras.domain.entities;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
public class Address {

	@Size( max = 100 )
	private String street;

	@Size( max = 100 )
	private String neighborhood;

	@Size( max = 10 )
	private String number;

	@Size( max = 100 )
	private String complement;

	@Size( max = 100 )
	private String city;

	@Size( max = 100 )
	private String state;

	@Size( max = 9 )
	private String zipCode;

	public String getStreet() {
		return street;
	}

	public void setStreet( String street ) {
		this.street = street;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood( String neighborhood ) {
		this.neighborhood = neighborhood;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber( String number ) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement( String complement ) {
		this.complement = complement;
	}

	public String getCity() {
		return city;
	}

	public void setCity( String city ) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState( String state ) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode( String zipCode ) {
		this.zipCode = zipCode;
	}

}

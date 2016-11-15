package br.com.apmp.ccompras.domain.entities;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@ManyToOne
	@JoinColumn( name = "fk_city" )
	private City city;

	@ManyToOne
	@JoinColumn( name = "fk_state" )
	private State state;

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

	public City getCity() {
		return city;
	}

	public void setCity( City city ) {
		this.city = city;
	}

	public State getState() {
		return state;
	}

	public void setState( State state ) {
		this.state = state;
	}

}

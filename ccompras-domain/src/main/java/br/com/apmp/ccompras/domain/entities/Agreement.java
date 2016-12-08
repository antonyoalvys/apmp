package br.com.apmp.ccompras.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table( name = "agreement" )
public class Agreement implements BaseEntity {

	private static final long serialVersionUID = -4537838701062462808L;

	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "PK_AGREEMENT_GENERATOR" )
	@SequenceGenerator( name = "PK_AGREEMENT_GENERATOR", sequenceName = "SEQ_AGREEMENT", allocationSize = 1 )
	@Column( name = "pk_agreement" )
	private Long id;

	@NotBlank
	@Size( max = 100 )
	@Column( name = "name", unique = true )
	private String name;

	@Column( name = "active" )
	private Boolean active;

	public Agreement() {
		this.active = true;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive( Boolean active ) {
		this.active = active;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId( Long id ) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		Agreement other = (Agreement) obj;
		if ( name == null ) {
			if ( other.name != null )
				return false;
		} else if ( !name.equals( other.name ) )
			return false;
		return true;
	}

}

package br.com.apmp.ccompras.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table( name = "city" )
public class City implements BaseEntity {

	private static final long serialVersionUID = -3605264643320637441L;

	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "PK_CITY_GENERATOR" )
	@SequenceGenerator( name = "PK_CITY_GENERATOR", sequenceName = "SEQ_CITY" )
	@Column( name = "pk_city" )
	private Long id;

	@NotNull
	@Size( max = 100 )
	private String name;

	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "fk_state" )
	private State state;

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId( Long id ) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
		result = prime * result + ( ( state == null ) ? 0 : state.hashCode() );
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
		City other = (City) obj;
		if ( name == null ) {
			if ( other.name != null )
				return false;
		} else if ( !name.equals( other.name ) )
			return false;
		if ( state == null ) {
			if ( other.state != null )
				return false;
		} else if ( !state.equals( other.state ) )
			return false;
		return true;
	}

}

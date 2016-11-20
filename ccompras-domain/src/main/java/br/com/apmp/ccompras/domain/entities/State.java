package br.com.apmp.ccompras.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table( name = "state" )
public class State implements BaseEntity {

	private static final long serialVersionUID = 8470553003553432444L;

	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "PK_STATE_GENERATOR" )
	@SequenceGenerator( name = "PK_STATE_GENERATOR", sequenceName = "SEQ_STATE", allocationSize = 1 )
	@Column( name = "pk_state" )
	private Long id;

	@NotNull
	@Size( max = 100 )
	@Column( name = "name" )
	private String name;

	@NotNull
	@Size( max = 2 )
	@Column( name = "short_name" )
	private String shortName;

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

	public String getShortName() {
		return shortName;
	}

	public void setShortName( String shortName ) {
		this.shortName = shortName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
		result = prime * result + ( ( shortName == null ) ? 0 : shortName.hashCode() );
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
		State other = (State) obj;
		if ( name == null ) {
			if ( other.name != null )
				return false;
		} else if ( !name.equals( other.name ) )
			return false;
		if ( shortName == null ) {
			if ( other.shortName != null )
				return false;
		} else if ( !shortName.equals( other.shortName ) )
			return false;
		return true;
	}

}

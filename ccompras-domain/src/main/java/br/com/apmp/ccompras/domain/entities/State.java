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
@Table( name = "state")
public class State implements BaseEntity {

	private static final long serialVersionUID = 8470553003553432444L;

	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "PK_STATE_GENERATOR" )
	@SequenceGenerator( name = "PK_STATE_GENERATOR", sequenceName = "SEQ_STATE" )
	@Column( name = "pk_state" )
	private Long id;

	@NotNull
	@Size( max = 100 )
	@Column( name = "name" )
	private String name;

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
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
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
		State other = (State) obj;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		if ( name == null ) {
			if ( other.name != null )
				return false;
		} else if ( !name.equals( other.name ) )
			return false;
		return true;
	}

}

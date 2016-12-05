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

import org.hibernate.validator.constraints.NotBlank;

import br.com.apmp.ccompras.domain.enums.PeriodStatus;

@Entity
@Table( name = "period" )
public class Period implements BaseEntity {

	private static final long serialVersionUID = -5758356866037644182L;

	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "PK_PERIOD_GENERATOR" )
	@SequenceGenerator( name = "PK_PERIOD_GENERATOR", sequenceName = "SEQ_PERIOD", allocationSize = 1 )
	@Column( name = "pk_period" )
	private Long id;

	@NotBlank
	@Size( max = 23 )
	@Column( name = "description", unique = true )
	private String description;

	@NotNull
	@Column( name = "period_status" )
	private PeriodStatus periodStatus;

	@Column( name = "has_file_covenant" )
	private Boolean hasFileCovenant;

	public Period() {
		this.periodStatus = PeriodStatus.OPEN;
		this.hasFileCovenant = false;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId( Long id ) {
		this.id = id;
	}

	public PeriodStatus getPeriodStatus() {
		return periodStatus;
	}

	public void setPeriodStatus( PeriodStatus periodStatus ) {
		this.periodStatus = periodStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription( String description ) {
		this.description = description;
	}

	public Boolean getHasFileCovenant() {
		return hasFileCovenant;
	}

	public void setHasFileCovenant( Boolean hasFileCovenant ) {
		this.hasFileCovenant = hasFileCovenant;
	}

	public String nameForPath() {
		return this.description.replaceAll( "/", "" );
	}

	@Override
	public String toString() {
		return this.description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( description == null ) ? 0 : description.hashCode() );
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
		Period other = (Period) obj;
		if ( description == null ) {
			if ( other.description != null )
				return false;
		} else if ( !description.equals( other.description ) )
			return false;
		return true;
	}

}

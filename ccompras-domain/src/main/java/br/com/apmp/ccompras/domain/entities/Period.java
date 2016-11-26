package br.com.apmp.ccompras.domain.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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

	@NotNull
	@Column( name = "begin_date" )
	private LocalDateTime beginDate;
	
	@NotNull
	@Column( name = "end_date" )
	private LocalDateTime endDate;
	
	@NotNull
	@Column( name = "period_status" )
	private PeriodStatus periodStatus;

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId( Long id ) {
		this.id = id;
	}

	public LocalDateTime getBeginDate() {
		return beginDate;
	}

	public void setBeginDate( LocalDateTime beginDate ) {
		this.beginDate = beginDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate( LocalDateTime endDate ) {
		this.endDate = endDate;
	}

	public PeriodStatus getPeriodStatus() {
		return periodStatus;
	}

	public void setPeriodStatus( PeriodStatus periodStatus ) {
		this.periodStatus = periodStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( beginDate == null ) ? 0 : beginDate.hashCode() );
		result = prime * result + ( ( endDate == null ) ? 0 : endDate.hashCode() );
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
		if ( beginDate == null ) {
			if ( other.beginDate != null )
				return false;
		} else if ( !beginDate.equals( other.beginDate ) )
			return false;
		if ( endDate == null ) {
			if ( other.endDate != null )
				return false;
		} else if ( !endDate.equals( other.endDate ) )
			return false;
		return true;
	}

}

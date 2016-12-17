package br.com.apmp.ccompras.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table( name = "rebate", uniqueConstraints = { @UniqueConstraint( columnNames = { "fk_company", "fk_agreement" } ) } )
public class Rebate implements BaseEntity {

	private static final long serialVersionUID = 962738106020400746L;

	@Id
	@SequenceGenerator( name = "PK_REBATE_GENERATOR", allocationSize = 1, sequenceName = "SEQ_REBATE" )
	@GeneratedValue( generator = "PK_REBATE_GENERATOR", strategy = GenerationType.SEQUENCE )
	private Long id;

	@NotNull
	@ManyToOne
	@JoinColumn( name = "fk_company" )
	private Company company;

	@NotNull
	@ManyToOne
	@JoinColumn( name = "fk_agreement" )
	private Agreement agreement;

	@Column( name = "register_date" )
	@NotNull
	private LocalDateTime registerDate;

	@Column( name = "observation" )
	private String observation;

	@NotNull
	private BigDecimal percentage;

	public Rebate() {
		this.registerDate = LocalDateTime.now();
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId( Long id ) {
		this.id = id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany( Company company ) {
		this.company = company;
	}

	public Agreement getAgreement() {
		return agreement;
	}

	public void setAgreement( Agreement agreement ) {
		this.agreement = agreement;
	}

	public BigDecimal getPercentage() {
		return percentage;
	}

	public void setPercentage( BigDecimal percentage ) {
		this.percentage = percentage;
	}

	public LocalDateTime getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate( LocalDateTime registerDate ) {
		this.registerDate = registerDate;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation( String observation ) {
		this.observation = observation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( agreement == null ) ? 0 : agreement.hashCode() );
		result = prime * result + ( ( company == null ) ? 0 : company.hashCode() );
		result = prime * result + ( ( percentage == null ) ? 0 : percentage.hashCode() );
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
		Rebate other = (Rebate) obj;
		if ( agreement == null ) {
			if ( other.agreement != null )
				return false;
		} else if ( !agreement.equals( other.agreement ) )
			return false;
		if ( company == null ) {
			if ( other.company != null )
				return false;
		} else if ( !company.equals( other.company ) )
			return false;
		if ( percentage == null ) {
			if ( other.percentage != null )
				return false;
		} else if ( !percentage.equals( other.percentage ) )
			return false;
		return true;
	}

}

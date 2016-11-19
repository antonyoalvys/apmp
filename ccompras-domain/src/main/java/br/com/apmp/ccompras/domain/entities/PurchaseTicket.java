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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table( name = "purchase_ticket" )
public class PurchaseTicket implements BaseEntity {

	private static final long serialVersionUID = 6349870609667777150L;

	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "PK_PURCHASE_TICKET_GENERATOR" )
	@SequenceGenerator( name = "PK_PURCHASE_TICKET_GENERATOR", sequenceName = "SEQ_PURCHASE_TICKET" )
	@Column( name = "pk_purchase_ticket" )
	private Long id;

	@Column( name = "code", unique = true )
	@Size( max = 20 )
	private String code;

	@Column( name = "ticket_value" )
	private BigDecimal ticketValue;

	@ManyToOne
	@JoinColumn( name = "fk_company" )
	@NotNull
	private Company company;

	@ManyToOne
	@JoinColumn( name = "fk_associate" )
	@NotNull
	private Associate associate;

	@Column( name = "usage_date" )
	private LocalDateTime usageDate;

	@Column( name = "register_date" )
	@NotNull
	private LocalDateTime registerDate;

	@Column( name = "active" )
	@NotNull
	private Boolean active;

	@Column( name = "description" )
	@Size( max = 200 )
	private String description;

	@Column( name = "observation" )
	@Size( max = 255 )
	private String observation;

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId( Long id ) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode( String code ) {
		this.code = code;
	}

	public BigDecimal getTicketValue() {
		return ticketValue;
	}

	public void setTicketValue( BigDecimal ticketValue ) {
		this.ticketValue = ticketValue;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany( Company company ) {
		this.company = company;
	}

	public Associate getAssociate() {
		return associate;
	}

	public void setAssociate( Associate associate ) {
		this.associate = associate;
	}

	public LocalDateTime getUsageDate() {
		return usageDate;
	}

	public void setUsageDate( LocalDateTime usageDate ) {
		this.usageDate = usageDate;
	}

	public LocalDateTime getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate( LocalDateTime registerDate ) {
		this.registerDate = registerDate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive( Boolean active ) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription( String description ) {
		this.description = description;
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
		result = prime * result + ( ( active == null ) ? 0 : active.hashCode() );
		result = prime * result + ( ( associate == null ) ? 0 : associate.hashCode() );
		result = prime * result + ( ( code == null ) ? 0 : code.hashCode() );
		result = prime * result + ( ( company == null ) ? 0 : company.hashCode() );
		result = prime * result + ( ( registerDate == null ) ? 0 : registerDate.hashCode() );
		result = prime * result + ( ( ticketValue == null ) ? 0 : ticketValue.hashCode() );
		result = prime * result + ( ( usageDate == null ) ? 0 : usageDate.hashCode() );
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
		PurchaseTicket other = (PurchaseTicket) obj;
		if ( active == null ) {
			if ( other.active != null )
				return false;
		} else if ( !active.equals( other.active ) )
			return false;
		if ( associate == null ) {
			if ( other.associate != null )
				return false;
		} else if ( !associate.equals( other.associate ) )
			return false;
		if ( code == null ) {
			if ( other.code != null )
				return false;
		} else if ( !code.equals( other.code ) )
			return false;
		if ( company == null ) {
			if ( other.company != null )
				return false;
		} else if ( !company.equals( other.company ) )
			return false;
		if ( registerDate == null ) {
			if ( other.registerDate != null )
				return false;
		} else if ( !registerDate.equals( other.registerDate ) )
			return false;
		if ( ticketValue == null ) {
			if ( other.ticketValue != null )
				return false;
		} else if ( !ticketValue.equals( other.ticketValue ) )
			return false;
		if ( usageDate == null ) {
			if ( other.usageDate != null )
				return false;
		} else if ( !usageDate.equals( other.usageDate ) )
			return false;
		return true;
	}

}

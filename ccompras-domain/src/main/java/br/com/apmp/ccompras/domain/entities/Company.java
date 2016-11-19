package br.com.apmp.ccompras.domain.entities;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.apmp.ccompras.domain.enums.PhoneType;

@Entity
@Table( name = "company" )
public class Company implements BaseEntity {

	private static final long serialVersionUID = 8158037719681769330L;

	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "PK_COMPANY_GENERATOR" )
	@SequenceGenerator( name = "PK_COMPANY_GENERATOR", sequenceName = "SEQ_COMPANY" )
	@Column( name = "pk_company" )
	private Long id;

	@Column( name = "cnpj", unique = true )
	@Size( max = 20 )
	private String cnpj;

	@Column( name = "name" )
	@Size( max = 100 )
	@NotNull
	private String name;

	@Column( name = "phone" )
	@Size( max = 20 )
	private String phone;

	@Enumerated( EnumType.STRING )
	@Column( name = "phone_type" )
	private PhoneType phoneType;

	@Column( name = "mail" )
	@Size( max = 100 )
	private String mail;

	@Column( name = "active" )
	@NotNull
	private Boolean active;

	@Embedded
	private Address address;

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId( Long id ) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj( String cnpj ) {
		this.cnpj = cnpj;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone( String phone ) {
		this.phone = phone;
	}

	public PhoneType getPhoneType() {
		return phoneType;
	}

	public void setPhoneType( PhoneType phoneType ) {
		this.phoneType = phoneType;
	}

	public String getMail() {
		return mail;
	}

	public void setMail( String mail ) {
		this.mail = mail;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive( Boolean active ) {
		this.active = active;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress( Address address ) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( active == null ) ? 0 : active.hashCode() );
		result = prime * result + ( ( cnpj == null ) ? 0 : cnpj.hashCode() );
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
		Company other = (Company) obj;
		if ( active == null ) {
			if ( other.active != null )
				return false;
		} else if ( !active.equals( other.active ) )
			return false;
		if ( cnpj == null ) {
			if ( other.cnpj != null )
				return false;
		} else if ( !cnpj.equals( other.cnpj ) )
			return false;
		if ( name == null ) {
			if ( other.name != null )
				return false;
		} else if ( !name.equals( other.name ) )
			return false;
		return true;
	}

}

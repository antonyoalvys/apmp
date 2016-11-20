package br.com.apmp.ccompras.domain.entities;

import java.time.LocalDateTime;

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

import br.com.apmp.ccompras.domain.enums.AssociateType;
import br.com.apmp.ccompras.domain.enums.PhoneType;

@Entity
@Table( name = "associate" )
public class Associate implements BaseEntity {

	private static final long serialVersionUID = 9206873889830454944L;

	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "PK_ASSOCIATE_GENERATOR" )
	@SequenceGenerator( name = "PK_ASSOCIATE_GENERATOR", sequenceName = "SEQ_ASSOCIATE", allocationSize = 1 )
	@Column( name = "pk_associate" )
	private Long id;

	@NotNull
	@Size( max = 100 )
	@Column( name = "name" )
	private String name;

	@NotNull
	@Size( max = 20 )
	@Column( name = "enrollment", unique = true )
	private String enrollment;

	@NotNull
	@Size( max = 20 )
	@Column( name = "main_phone" )
	private String mainPhone;

	@Enumerated( EnumType.STRING )
	@Column( name = "main_phone_type" )
	private PhoneType mainPhoneType;

	@Size( max = 20 )
	@Column( name = "secondary_phone" )
	private String secondaryPhone;

	@Enumerated( EnumType.STRING )
	@Column( name = "secondary_phone_type" )
	private PhoneType secondaryPhoneType;

	@Enumerated( EnumType.STRING )
	@Column( name = "associate_type" )
	private AssociateType associateType;

	@Column( name = "register_date" )
	@NotNull
	private LocalDateTime registerDate;

	@Size( max = 100 )
	@Column( name = "mail" )
	private String mail;

	@Column( name = "active" )
	@NotNull
	private Boolean active;

	@Embedded
	private Address address;

	public Associate() {
		this.address = new Address();
	}

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

	public String getEnrollment() {
		return enrollment;
	}

	public void setEnrollment( String enrollment ) {
		this.enrollment = enrollment;
	}

	public String getMainPhone() {
		return mainPhone;
	}

	public void setMainPhone( String mainPhone ) {
		this.mainPhone = mainPhone;
	}

	public PhoneType getMainPhoneType() {
		return mainPhoneType;
	}

	public void setMainPhoneType( PhoneType mainPhoneType ) {
		this.mainPhoneType = mainPhoneType;
	}

	public String getSecondaryPhone() {
		return secondaryPhone;
	}

	public void setSecondaryPhone( String secondaryPhone ) {
		this.secondaryPhone = secondaryPhone;
	}

	public PhoneType getSecondaryPhoneType() {
		return secondaryPhoneType;
	}

	public void setSecondaryPhoneType( PhoneType secondaryPhoneType ) {
		this.secondaryPhoneType = secondaryPhoneType;
	}

	public AssociateType getAssociateType() {
		return associateType;
	}

	public void setAssociateType( AssociateType associateType ) {
		this.associateType = associateType;
	}

	public LocalDateTime getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate( LocalDateTime registerDate ) {
		this.registerDate = registerDate;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress( Address address ) {
		this.address = address;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( active == null ) ? 0 : active.hashCode() );
		result = prime * result + ( ( associateType == null ) ? 0 : associateType.hashCode() );
		result = prime * result + ( ( enrollment == null ) ? 0 : enrollment.hashCode() );
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
		Associate other = (Associate) obj;
		if ( active == null ) {
			if ( other.active != null )
				return false;
		} else if ( !active.equals( other.active ) )
			return false;
		if ( associateType != other.associateType )
			return false;
		if ( enrollment == null ) {
			if ( other.enrollment != null )
				return false;
		} else if ( !enrollment.equals( other.enrollment ) )
			return false;
		if ( name == null ) {
			if ( other.name != null )
				return false;
		} else if ( !name.equals( other.name ) )
			return false;
		return true;
	}

}

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

import org.hibernate.validator.constraints.NotBlank;

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

	@NotBlank
	@Size( max = 100 )
	@Column( name = "name" )
	private String name;

	@NotBlank
	@Size( max = 20 )
	@Column( name = "enrollment", unique = true )
	private String enrollment;

	@Size( max = 14 )
	@Column( name = "cpf" )
	private String cpf;

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

	@NotNull
	@Column( name = "register_date" )
	private LocalDateTime registerDate;

	@Column( name = "retired" )
	private Boolean retired;

	@NotNull
	@Column( name = "active" )
	private Boolean active;

	@Embedded
	private Address address;

	public Associate() {
		this.address = new Address();
		this.active = true;
		this.retired = false;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive( Boolean active ) {
		this.active = active;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf( String cpf ) {
		this.cpf = cpf;
	}

	public Boolean getRetired() {
		return retired;
	}

	public void setRetired( Boolean retired ) {
		this.retired = retired;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( associateType == null ) ? 0 : associateType.hashCode() );
		result = prime * result + ( ( cpf == null ) ? 0 : cpf.hashCode() );
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
		if ( associateType != other.associateType )
			return false;
		if ( cpf == null ) {
			if ( other.cpf != null )
				return false;
		} else if ( !cpf.equals( other.cpf ) )
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

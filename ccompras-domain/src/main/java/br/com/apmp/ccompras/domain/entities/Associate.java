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

@Entity
@Table( name = "associate" )
public class Associate implements BaseEntity {

	private static final long serialVersionUID = 9206873889830454944L;

	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "PK_ASSOCIATE_GENERATOR" )
	@SequenceGenerator( name = "PK_ASSOCIATE_GENERATOR", sequenceName = "SEQ_ASSOCIATE" )
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

	@Size( max = 20 )
	@Column( name = "secundary_phone" )
	private String secundaryPhone;

	@Enumerated( EnumType.STRING )
	@Column( name = "associate_type" )
	private AssociateType associateType;

	@Column( name = "register_date" )
	@NotNull
	private LocalDateTime registerDate;

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

	public AssociateType getAssociateType() {
		return associateType;
	}

	public void setAssociateType( AssociateType associateType ) {
		this.associateType = associateType;
	}

	public String getSecundaryPhone() {
		return secundaryPhone;
	}

	public void setSecundaryPhone( String secundaryPhone ) {
		this.secundaryPhone = secundaryPhone;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( associateType == null ) ? 0 : associateType.hashCode() );
		result = prime * result + ( ( enrollment == null ) ? 0 : enrollment.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( mainPhone == null ) ? 0 : mainPhone.hashCode() );
		result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
		result = prime * result + ( ( registerDate == null ) ? 0 : registerDate.hashCode() );
		result = prime * result + ( ( secundaryPhone == null ) ? 0 : secundaryPhone.hashCode() );
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
		if ( enrollment == null ) {
			if ( other.enrollment != null )
				return false;
		} else if ( !enrollment.equals( other.enrollment ) )
			return false;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		if ( mainPhone == null ) {
			if ( other.mainPhone != null )
				return false;
		} else if ( !mainPhone.equals( other.mainPhone ) )
			return false;
		if ( name == null ) {
			if ( other.name != null )
				return false;
		} else if ( !name.equals( other.name ) )
			return false;
		if ( registerDate == null ) {
			if ( other.registerDate != null )
				return false;
		} else if ( !registerDate.equals( other.registerDate ) )
			return false;
		if ( secundaryPhone == null ) {
			if ( other.secundaryPhone != null )
				return false;
		} else if ( !secundaryPhone.equals( other.secundaryPhone ) )
			return false;
		return true;
	}

}

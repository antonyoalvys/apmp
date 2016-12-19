package br.com.apmp.ccompras.domain.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table( name = "security_user" )
public class User implements BaseEntity {

	private static final long serialVersionUID = 8104038206319459277L;

	@Id
	@GeneratedValue( generator = "PK_SECURITY_USER_GENERATOR", strategy = GenerationType.SEQUENCE )
	@SequenceGenerator( name = "PK_SECURITY_USER_GENERATOR", allocationSize = 1, sequenceName = "SEQ_SECURITY_USER" )
	@Column( name = "PK_SECURITY_USER" )
	private Long id;

	@NotBlank
	@Size( max = 50 )
	@Column( name = "username", unique = true )
	private String username;
	@NotBlank
	@Size( max = 50 )
	@Column( name = "password" )
	private String password;
	@NotBlank
	@Size( max = 100 )
	@Column( name = "mail", unique = true )
	@Email
	private String mail;

	@Size( max = 100 )
	@Column( name = "salt" )
	private String salt;

	@NotNull
	@Column( name = "active" )
	private Boolean active;

	@NotNull
	private LocalDate registerDate;

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId( Long id ) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername( String username ) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword( String password ) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail( String mail ) {
		this.mail = mail;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt( String salt ) {
		this.salt = salt;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive( Boolean active ) {
		this.active = active;
	}

	public LocalDate getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate( LocalDate registerDate ) {
		this.registerDate = registerDate;
	}

}

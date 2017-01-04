package br.com.apmp.ccompras.domain.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table( name = "user_account" )
@NamedQueries( { @NamedQuery( name = "User.findAll", query = "select u from User u" ), @NamedQuery( name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username" ) } )
public class User implements BaseEntity {

	private static final long serialVersionUID = 8104038206319459277L;

	@Id
	@GeneratedValue( generator = "PK_USER_ACCOUNT_GENERATOR", strategy = GenerationType.SEQUENCE )
	@SequenceGenerator( name = "PK_USER_ACCOUNT_GENERATOR", allocationSize = 1, sequenceName = "SEQ_USER_ACCOUNT" )
	@Column( name = "PK_USER_ACCOUNT" )
	private Long id;

	@NotBlank
	@Size( max = 50 )
	@Column( name = "username", unique = true )
	private String username;
	@NotBlank
	@Column( name = "password" )
	private String password;
	@NotBlank
	@Size( max = 100 )
	@Column( name = "mail", unique = true )
	@Email
	private String mail;

	@ManyToOne
	@JoinColumn( name = "fk_role" )
	private Role role;

	@NotNull
	@Column( name = "enabled" )
	private Boolean enabled;

	@Version
	@Column( name = "version", nullable = false )
	private Timestamp version;

	private LocalDateTime registerDate;

	public User() {
		this.enabled = true;
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

	public Role getRole() {
		return role;
	}

	public void setRole( Role role ) {
		this.role = role;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled( Boolean enabled ) {
		this.enabled = enabled;
	}

	public Timestamp getVersion() {
		return version;
	}

	public void setVersion( Timestamp version ) {
		this.version = version;
	}

	public LocalDateTime getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate( LocalDateTime registerDate ) {
		this.registerDate = registerDate;
	}

}

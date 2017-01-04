package br.com.apmp.ccompras.domain.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table( name = "role" )
@NamedQueries( { @NamedQuery( name = "Role.findAll", query = "SELECT r FROM Role r" ) } )
public class Role implements BaseEntity {

	private static final long serialVersionUID = 8882033532863239136L;

	@Id
	@GeneratedValue( generator = "PK_ROLE_GENERATOR", strategy = GenerationType.SEQUENCE )
	@SequenceGenerator( name = "PK_ROLE_GENERATOR", allocationSize = 1, sequenceName = "SEQ_ROLE" )
	@Column( name = "PK_ROLE" )
	private Long id;

	@NotBlank
	@Size( max = 100 )
	@Column( name = "name", unique = true )
	private String name;

	private LocalDateTime creation;

	@Column( name = "enabled", nullable = false )
	private Boolean enabled;

	@OneToMany( fetch = FetchType.EAGER )
	@JoinTable( name = "role_permission", joinColumns = @JoinColumn( name = "pk_role" ), inverseJoinColumns = @JoinColumn( name = "pk_permission" ) )
	private List<Permission> permissions;

	public Role() {
		this.enabled = true;
		this.creation = LocalDateTime.now();
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

	public LocalDateTime getCreation() {
		return creation;
	}

	public void setCreation( LocalDateTime creation ) {
		this.creation = creation;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled( Boolean enabled ) {
		this.enabled = enabled;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions( List<Permission> permissions ) {
		this.permissions = permissions;
	}

	public Set<String> permissionsToSet() {
		Set<String> permissions = new HashSet<String>();
		if ( this.permissions != null ) {
			for ( Permission permission : this.permissions ) {
				permissions.add( permission.getRight() );
			}
		}
		return permissions;
	}

}

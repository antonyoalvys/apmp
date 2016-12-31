package br.com.apmp.ccompras.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table( name = "permission" )
public class Permission implements BaseEntity {

	private static final long serialVersionUID = -8143664907598126391L;

	@Id
	@GeneratedValue( generator = "PK_PERMISSION_GENERATOR", strategy = GenerationType.SEQUENCE )
	@SequenceGenerator( name = "PK_PERMISSION_GENERATOR", allocationSize = 1, sequenceName = "SEQ_PERMISSION" )
	@Column( name = "PK_PERMISSION" )
	private Long id;

	@NotBlank
	@Column( name = "name" )
	private String name;

	@NotBlank
	@Column( name = "_right", unique = true )
	private String right;

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

	public String getRight() {
		return right;
	}

	public void setRight( String right ) {
		this.right = right;
	}

}

package br.com.apmp.ccompras.domain.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table( name = "file_covenant" )
public class FileCovenant implements BaseEntity {

	private static final long serialVersionUID = -2736736591183532705L;

	@Id
	@GeneratedValue( generator = "FILE_COVENANT_GENERATOR", strategy = GenerationType.SEQUENCE )
	@SequenceGenerator( name = "FILE_COVENANT_GENERATOR", sequenceName = "SEQ_FILE_COVENANT", allocationSize = 1 )
	@Column( name = "pk_file_covenant" )
	private Long id;

	@Lob
	@Column( name = "file" )
	@NotNull
	private byte[] file;

	@OneToOne
	@JoinColumn( name = "fk_period" )
	@NotNull
	private Period period;

	@NotNull
	@Column( name = "generate_date" )
	private LocalDateTime generateDate;

	@NotNull
	@Column( name = "name" )
	private String name;

	public FileCovenant() {
		this.generateDate = LocalDateTime.now();
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId( Long id ) {
		this.id = id;
	}
	
	public byte[] getFile() {
		return file;
	}

	public void setFile( byte[] file ) {
		this.file = file;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod( Period period ) {
		this.period = period;
	}

	public LocalDateTime getGenerateDate() {
		return generateDate;
	}

	public void setGenerateDate( LocalDateTime generateDate ) {
		this.generateDate = generateDate;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( generateDate == null ) ? 0 : generateDate.hashCode() );
		result = prime * result + ( ( period == null ) ? 0 : period.hashCode() );
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
		FileCovenant other = (FileCovenant) obj;
		if ( generateDate == null ) {
			if ( other.generateDate != null )
				return false;
		} else if ( !generateDate.equals( other.generateDate ) )
			return false;
		if ( period == null ) {
			if ( other.period != null )
				return false;
		} else if ( !period.equals( other.period ) )
			return false;
		return true;
	}

}

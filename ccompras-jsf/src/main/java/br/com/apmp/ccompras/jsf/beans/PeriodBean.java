package br.com.apmp.ccompras.jsf.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.apmp.ccompras.domain.entities.Period;
import br.com.apmp.ccompras.service.PeriodService;

@Named
@ViewScoped
public class PeriodBean implements Serializable {

	private static final long serialVersionUID = -5391076748088986414L;

	@Inject
	private PeriodService periodService;

	private Period entity;

	@PostConstruct
	public void init() {
		entityClear();
	}

	public void save() {
		periodService.save( this.entity );
		String message = String.format( "O período %s foi registrado com sucesso.", this.entity.getDescription() );
		FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( FacesMessage.SEVERITY_INFO, message, null ) );
		entityClear();
	}

	public void entityClear() {
		this.entity = new Period();
	}

	public Period getEntity() {
		return entity;
	}

	public void setEntity( Period entity ) {
		this.entity = entity;
	}

	public void clear() {
		entityClear();
	}

}

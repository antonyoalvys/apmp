package br.com.apmp.ccompras.jsf.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.apmp.ccompras.domain.entities.Agreement;
import br.com.apmp.ccompras.service.AgreementService;

@Named
@ViewScoped
public class AgreementBean implements Serializable {

	private static final long serialVersionUID = -5391076748088986414L;

	@Inject
	private AgreementService agreementService;

	private Agreement entity;

	@PostConstruct
	public void init() {
		entityClear();
	}

	public void save() {
		agreementService.save( this.entity );
		String message = String.format( "O Convênio %s foi registrado com sucesso.", this.entity.getName() );
		FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( FacesMessage.SEVERITY_INFO, message, null ) );
		entityClear();
	}

	public void entityClear() {
		this.entity = new Agreement();
	}

	public Agreement getEntity() {
		return entity;
	}

	public void setEntity( Agreement entity ) {
		this.entity = entity;
	}

	public void clear() {
		entityClear();
	}

}

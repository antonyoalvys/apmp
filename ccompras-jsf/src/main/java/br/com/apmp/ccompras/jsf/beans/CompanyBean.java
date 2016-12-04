package br.com.apmp.ccompras.jsf.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.apmp.ccompras.domain.entities.Company;
import br.com.apmp.ccompras.domain.enums.PhoneType;
import br.com.apmp.ccompras.service.CompanyService;

@Named
@ViewScoped
public class CompanyBean implements Serializable {

	private static final long serialVersionUID = -5391076748088986414L;

	@Inject
	private CompanyService companyService;
	private Company entity;

	@PostConstruct
	public void init() {
		entityClear();
	}

	public void register() {
		companyService.save( this.entity );
		String message = String.format( "O estabelecimento comercial %s foi registrado com sucesso.", this.entity.getName() );
		FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( FacesMessage.SEVERITY_INFO, message, null ) );
		entityClear();
	}

	public void changeState( ValueChangeEvent event ) {
		if ( event != null && event.getNewValue() != null ) {
			this.entity.getAddress().setState( (String) event.getNewValue() );
			this.entity.getAddress().setCity( null );
		}
	}

	public void entityClear() {
		this.entity = new Company();
		this.entity.setPhoneType( PhoneType.LANDLINE );
	}

	public Company getEntity() {
		return entity;
	}

	public void setEntity( Company entity ) {
		this.entity = entity;
	}

	public void clear() {
		entityClear();
	}

}

package br.com.apmp.ccompras.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.apmp.ccompras.domain.entities.Associate;
import br.com.apmp.ccompras.domain.enums.PhoneType;
import br.com.apmp.ccompras.service.AssociateService;

@Named
@ViewScoped
public class AssociateBean implements Serializable {

	private static final long serialVersionUID = -5391076748088986414L;

	@Inject
	private AssociateService associateService;
	private Associate entity;

	@PostConstruct
	public void init() {
		entityClear();
	}

	public void register() {
		associateService.save( this.entity );
		String message = String.format( "O associado %s foi registrado com sucesso.", this.entity.getName() );
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
		this.entity = new Associate();
		this.entity.setMainPhoneType( PhoneType.LANDLINE );
		this.entity.setSecondaryPhoneType( PhoneType.MOBILE );
	}

	public Associate getEntity() {
		return entity;
	}

	public void setEntity( Associate entity ) {
		this.entity = entity;
	}

	public void clear() {
		entityClear();
	}

}

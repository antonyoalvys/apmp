package br.com.apmp.ccompras.beans.search;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.apmp.ccompras.domain.entities.Associate;
import br.com.apmp.ccompras.domain.exceptions.RepositoryException;
import br.com.apmp.ccompras.service.AssociateService;
import br.com.apmp.ccompras.service.exceptions.ServiceException;

@Named
@ViewScoped
public class AssociateSearchBean extends BaseBeanSearch<Associate> {

	private static final long serialVersionUID = 8374277006321260557L;

	@Inject
	private AssociateService associateService;
	
	private Associate associateSearch;

	private List<Associate> associateList;
	
	
	@PostConstruct
	public void init() {
		clear();
	}
	
    public void findByEntity( Associate entity ) throws ServiceException {
        this.associateList = (List) associateService.findByEntity(this.associateSearch);
    }
	
    public void show(Associate associate) {
        setShowEntity(associate);
        super.show();
    }    


    public void delete(Associate associate) throws ServiceException {
        associateService.delete( associate );
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Associado desativado com sucesso.", null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
	public void clear() {
		this.associateSearch = new Associate();
		this.associateList = new ArrayList<Associate>();
	}

	@Override
	protected List<Associate> getList() throws ServiceException, RepositoryException {
		return associateService.findAll();
	}

	@Override
	protected Class<Associate> searchEntityClass() {
		return Associate.class;
	}

	public List<Associate> getAssociateList() {
		return associateList;
	}

	public void setAssociateList( List<Associate> associateList ) {
		this.associateList = associateList;
	}

}

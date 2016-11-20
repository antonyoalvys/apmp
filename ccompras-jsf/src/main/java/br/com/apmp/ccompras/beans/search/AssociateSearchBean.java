package br.com.apmp.ccompras.beans.search;

import java.util.List;

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

	@Override
	protected List<Associate> getList() throws ServiceException, RepositoryException {
		return associateService.findAll();
	}

	@Override
	protected Class<Associate> searchEntityClass() {
		return Associate.class;
	}

}

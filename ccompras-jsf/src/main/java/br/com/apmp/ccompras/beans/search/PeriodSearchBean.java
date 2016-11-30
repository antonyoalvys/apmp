package br.com.apmp.ccompras.beans.search;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.tabview.Tab;

import br.com.apmp.ccompras.domain.entities.Period;
import br.com.apmp.ccompras.domain.exceptions.RepositoryException;
import br.com.apmp.ccompras.service.PeriodService;
import br.com.apmp.ccompras.service.exceptions.ServiceException;

@Named
@ViewScoped
public class PeriodSearchBean extends BaseBeanSearch<Period> {

	private static final long serialVersionUID = 8374277006321260557L;

	@Inject
	private PeriodService periodService;

	private Period periodSearch;

	private List<Period> periodList;

	private Tab searchTab;

	@PostConstruct
	public void init() {
		clear();
	}

	public void findByEntity() throws ServiceException {
		this.periodList = periodService.findByEntity( getSearchEntity() );
	}

	public void show( Period period ) {
		setShowEntity( period );
		super.show();
	}

	public void save() {
		periodService.save( getEditEntity() );
		String message = String.format( "O período %s - %s foi atualizado.", this.getEditEntity().getBeginDate().format( DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ), getEditEntity().getEndDate().format( DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ) );
		closeEdit();
		FacesMessage facesMessage = new FacesMessage( FacesMessage.SEVERITY_INFO, message, null );
		FacesContext.getCurrentInstance().addMessage( null, facesMessage );
	}

	public void delete() throws ServiceException {
		periodService.delete( getRemoveEntity() );
		String message = String.format( "O período %s - %s foi removido.", this.getRemoveEntity().getBeginDate().format( DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ), getRemoveEntity().getEndDate().format( DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ) );
		FacesMessage facesMessage = new FacesMessage( FacesMessage.SEVERITY_INFO, message, null );
		FacesContext.getCurrentInstance().addMessage( null, facesMessage );
	}

	public void closeShow() {
		setShowEntity( new Period() );
		closeTab( getShowTab() );
	}

	public void closeEdit() {
		setEditEntity( new Period() );
		closeTab( getEditTab() );
	}

	public void clear() {
		setSearchEntity( new Period() );
		this.periodList = new ArrayList<Period>();
	}

	@Override
	protected List<Period> getList() throws ServiceException, RepositoryException {
		return periodService.findAll();
	}

	@Override
	protected Class<Period> searchEntityClass() {
		return Period.class;
	}

	public Period getPeriodSearch() {
		return periodSearch;
	}

	public void setPeriodSearch( Period periodSearch ) {
		this.periodSearch = periodSearch;
	}

	public List<Period> getPeriodList() {
		return periodList;
	}

	public void setPeriodList( List<Period> periodList ) {
		this.periodList = periodList;
	}

	public Tab getSearchTab() {
		return searchTab;
	}

	public void setSearchTab( Tab searchTab ) {
		this.searchTab = searchTab;
	}

}

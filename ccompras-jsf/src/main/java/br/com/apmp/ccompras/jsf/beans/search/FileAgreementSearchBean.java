package br.com.apmp.ccompras.jsf.beans.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.apmp.ccompras.domain.entities.FileAgreement;
import br.com.apmp.ccompras.domain.entities.Period;
import br.com.apmp.ccompras.service.FileAgreementService;
import br.com.apmp.ccompras.service.PeriodService;

@Named
@ViewScoped
public class FileAgreementSearchBean implements Serializable {

	private static final long serialVersionUID = 2902477334787115141L;

	@Inject
	private FileAgreementService fileAgreementService;
	@Inject
	private PeriodService periodService;
	private List<FileAgreement> fileAgreements;
	private List<Period> periods;
	private Period period;
	private FileAgreement deleteFile;

	@PostConstruct
	public void init() {
		this.fileAgreements = new ArrayList<FileAgreement>();
		this.periods = new ArrayList<Period>();
		this.period = new Period();
	}

	public void findFiles() {
		this.fileAgreements = fileAgreementService.findByPeriod( period );
	}

	public void delete() {
		fileAgreementService.delete( deleteFile );
		String message = String.format( "O arquivo referente ao período %s foi removido.", deleteFile.getPeriod().getDescription() );
		FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( FacesMessage.SEVERITY_INFO, null, message ) );
	}

	public List<Period> autocompletePeriod( String queryPeriod ) {
		queryPeriod = queryPeriod.trim();
		this.periods = periodService.findByDescription( queryPeriod );
		return periods;
	}

	public List<FileAgreement> getFileAgreements() {
		return fileAgreements;
	}

	public void setFileAgreements( List<FileAgreement> fileAgreements ) {
		this.fileAgreements = fileAgreements;
	}

	public List<Period> getPeriods() {
		return periods;
	}

	public void setPeriods( List<Period> periods ) {
		this.periods = periods;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod( Period period ) {
		this.period = period;
	}

	public FileAgreement getDeleteFile() {
		return deleteFile;
	}

	public void setDeleteFile( FileAgreement deleteFile ) {
		this.deleteFile = deleteFile;
	}

}

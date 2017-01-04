package br.com.apmp.ccompras.jsf.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.apmp.ccompras.domain.entities.Period;
import br.com.apmp.ccompras.domain.enums.ReportType;
import br.com.apmp.ccompras.service.PeriodService;

@Named
@ViewScoped
public class ReportBean implements Serializable {

	private static final long serialVersionUID = 5619513553405565878L;

	@Inject
	private PeriodService periodService;

	private List<Period> periodList;
	private ReportType reportType;
	private Period period;

	@PostConstruct
	public void init() {
		entityClear();
	}

	public void generate() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect( FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/relatorios/extratoDesconto" + "?periodoId=" + period.getId() + "&periodoDesc=" + period.getDescription()+"&codeRel="+reportType.getCode()+ "&faces-redirect=true" );
		} catch ( IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Period> autocompletePeriod( String queryPeriod ) {
		queryPeriod = queryPeriod.trim();
		this.periodList = periodService.findByDescription( queryPeriod );
		return this.periodList;
	}

	public void entityClear() {
		this.periodList = new ArrayList<Period>();
	}

	public List<Period> getPeriodList() {
		return periodList;
	}

	public void setPeriodList( List<Period> periodList ) {
		this.periodList = periodList;
	}

	public ReportType getReportType() {
		return reportType;
	}

	public void setReportType( ReportType reportType ) {
		this.reportType = reportType;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod( Period period ) {
		this.period = period;
	}

}

package br.com.apmp.ccompras.jsf.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.apmp.ccompras.domain.entities.Period;
import br.com.apmp.ccompras.service.FileCovenantService;
import br.com.apmp.ccompras.service.PeriodService;

@Named
@ViewScoped
public class FileCovenantBean implements Serializable {

	private static final long serialVersionUID = 2487962749468137946L;

	@Inject
	private FileCovenantService fileCovenantService;
	@Inject
	private PeriodService periodService;
	private Period period;
	private List<Period> periodList;

	@PostConstruct
	public void init() {
		this.periodList = new ArrayList<Period>();
		clear();
	}

	public void generate() {
		fileCovenantService.generate( period );
	}

	public List<Period> autocompletePeriod( String queryPeriod ) {
		queryPeriod = queryPeriod.trim();
		this.periodList = periodService.findByDescription( queryPeriod );
		return this.periodList;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod( Period period ) {
		this.period = period;
	}

	public void clear() {
		this.period = new Period();
	}

	public List<Period> getPeriodList() {
		return periodList;
	}

	public void setPeriodList( List<Period> periodList ) {
		this.periodList = periodList;
	}

}

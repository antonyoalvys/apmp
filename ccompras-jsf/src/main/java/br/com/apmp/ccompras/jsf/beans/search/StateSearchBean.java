package br.com.apmp.ccompras.jsf.beans.search;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.apmp.ccompras.domain.entities.State;
import br.com.apmp.ccompras.service.StateService;

@Named
@ViewScoped
public class StateSearchBean implements Serializable {

	private static final long serialVersionUID = 674704193239127683L;

	@Inject
	private StateService stateService;
	private List<State> availableStates;
	private List<String> availableStatesNames;
	private List<String> availableStatesShortNames;

	public List<State> findByName( String name ) {
		name = name.trim();
		this.availableStates = stateService.findByName( name );
		return this.availableStates;
	}

	public List<String> autocompleteStatesNames( String query ) {
		query = query.trim();
		this.availableStatesNames = stateService.autocompleteNames( query );
		return this.availableStatesNames;
	}

	public List<String> autocompleteStatesShortNames( String query ) {
		query = query.trim();
		this.availableStatesShortNames = stateService.autocompleteShortNames( query );
		return this.availableStatesShortNames;
	}

	public List<State> getAvailableStates() {
		return availableStates;
	}

	public void setAvailableStates( List<State> availableStates ) {
		this.availableStates = availableStates;
	}

	public List<String> getAvailableStatesNames() {
		return availableStatesNames;
	}

	public void setAvailableStatesNames( List<String> availableStatesNames ) {
		this.availableStatesNames = availableStatesNames;
	}

	public List<String> getAvailableStatesShortNames() {
		return availableStatesShortNames;
	}

	public void setAvailableStatesShortNames( List<String> availableStatesShortNames ) {
		this.availableStatesShortNames = availableStatesShortNames;
	}

}

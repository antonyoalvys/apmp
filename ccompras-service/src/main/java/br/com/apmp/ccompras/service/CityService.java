package br.com.apmp.ccompras.service;

import java.io.Serializable;
import java.util.List;

import br.com.apmp.ccompras.domain.entities.City;

public interface CityService extends Serializable {

	public City findByName( String name );

	public City findById( Long id );

	public List<City> findAll();

}

package br.com.apmp.ccompras.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import br.com.apmp.ccompras.domain.entities.City;
import br.com.apmp.ccompras.domain.repository.CityRepository;
import br.com.apmp.ccompras.service.CityService;

@TransactionManagement( TransactionManagementType.CONTAINER )
@Stateless
public class CityServiceImpl implements CityService {

	private static final long serialVersionUID = -5684066819947371483L;

	@Inject
	private CityRepository cityRepository;

	@Override
	public City findByName( String name ) {
		return this.cityRepository.findByName( name );
	}

	@Override
	public City findById( Long id ) {
		return this.cityRepository.findById( id );
	}

	@Override
	public List<City> findAll() {
		return this.cityRepository.findAll();
	}

}

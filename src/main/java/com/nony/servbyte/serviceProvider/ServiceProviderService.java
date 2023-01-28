package com.nony.servbyte.serviceProvider;

import java.util.List;
import java.util.NoSuchElementException;

import com.nony.servbyte.exception.CityNotFoundException;
import com.nony.servbyte.exception.RestaurantNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ServiceProviderService {
	private final ServiceProviderRepository serviceProvideRepo;

	public ServiceProviderService(ServiceProviderRepository serviceProvideRepo) {
		this.serviceProvideRepo = serviceProvideRepo;
	}

	public List<ServiceProvider> listAll() {
		return serviceProvideRepo.findAll();
	}

	public ServiceProvider getServiceProviderById(Integer id) throws RestaurantNotFoundException {
		return serviceProvideRepo.findById(id).orElseThrow(RestaurantNotFoundException::new);
	}

	public List<ServiceProvider> getServiceProviderByCity(Integer cityId) throws CityNotFoundException {
		try {
			return serviceProvideRepo.findByCityId(cityId);
		} catch (NoSuchElementException exception) {
			throw new CityNotFoundException();
		}
	}

	public List<ServiceProvider> getServiceProviderByName(String name) throws RestaurantNotFoundException {
		try {
			return serviceProvideRepo.findByNameContainingIgnoreCase(name);
		} catch (NoSuchElementException exception) {
			throw new RestaurantNotFoundException();
		}
	}

	public List<ServiceProvider> getServiceProviderByType(Type type) throws RestaurantNotFoundException {
		try {
			return serviceProvideRepo.findByType(type);
		} catch (NoSuchElementException exception) {
			throw new RestaurantNotFoundException();
		}
	}

	public void deleteServiceProvider(Integer id) throws RestaurantNotFoundException {
		try {
			serviceProvideRepo.deleteById(id);
		} catch (EmptyResultDataAccessException exception) {
			throw new RestaurantNotFoundException();
		}
	}
}

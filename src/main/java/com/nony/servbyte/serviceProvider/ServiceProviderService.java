package com.nony.servbyte.serviceProvider;

import com.nony.servbyte.exception.RestaurantNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ServiceProviderService {
	private final ServiceProviderRepository serviceProvideRepo;

	public ServiceProviderService(ServiceProviderRepository serviceProvideRepo) {
		this.serviceProvideRepo = serviceProvideRepo;
	}

	public ServiceProvider getServiceProviderById(Integer id) throws RestaurantNotFoundException {
		return get(id);
	}

	public void deleteServiceProvider(Integer id) throws RestaurantNotFoundException {
		try {
			serviceProvideRepo.deleteById(id);
		} catch (EmptyResultDataAccessException exception) {
			throw new RestaurantNotFoundException();
		}
	}

	private ServiceProvider get(Integer id) throws RestaurantNotFoundException {
		return serviceProvideRepo.findById(id).orElseThrow(RestaurantNotFoundException::new);
	}
}

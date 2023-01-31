package com.nony.servbyte.serviceProvider;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import com.nony.servbyte.city.City;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ServiceProviderRepositoryTest {

	@Mock
	private ServiceProviderRepository serviceProviderRepository;

	@Test
	void testFindByCityId() {
		Integer cityId = 1;
		City city = new City(cityId, "city1");

		List<ServiceProvider> expectedServiceProviders = Arrays.asList(
				new ServiceProvider(1, "Restaurant A", Type.RESTAURANT, city),
				new ServiceProvider(2, "Restaurant B", Type.RESTAURANT, city)
		);

		when(serviceProviderRepository.findByCityId(cityId)).thenReturn(expectedServiceProviders);

		List<ServiceProvider> actualServiceProviders = serviceProviderRepository.findByCityId(cityId);
		assertEquals(expectedServiceProviders, actualServiceProviders);
	}

	@Test
	void testFindByNameContainingIgnoreCase() {
		String name = "restaurant";
		List<ServiceProvider> expectedServiceProviders = Arrays.asList(
				new ServiceProvider(1, "Restaurant A", Type.RESTAURANT, new City()),
				new ServiceProvider(2, "Restaurant B", Type.RESTAURANT, new City())
		);

		when(serviceProviderRepository.findByNameContainingIgnoreCase(name)).thenReturn(expectedServiceProviders);

		List<ServiceProvider> actualServiceProviders = serviceProviderRepository.findByNameContainingIgnoreCase(name);
		assertEquals(expectedServiceProviders, actualServiceProviders);
	}

	@Test
	void testFindByType() {
		Type type = Type.RESTAURANT;
		List<ServiceProvider> expectedServiceProviders = Arrays.asList(
				new ServiceProvider(1, "Restaurant A", type, new City()),
				new ServiceProvider(2, "Restaurant B", type, new City())
		);

		when(serviceProviderRepository.findByType(type)).thenReturn(expectedServiceProviders);

		List<ServiceProvider> actualServiceProviders = serviceProviderRepository.findByType(type);
		assertEquals(expectedServiceProviders, actualServiceProviders);
	}
}

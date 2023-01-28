package com.nony.servbyte.city;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CityRepositoryTest {

	@Mock
	private CityRepository cityRepository;

	@Test
	void whenFindByName_thenReturnCity() {
		City expectedCity = new City(1, "New York");

		when(cityRepository
				.findByName("New York"))
				.thenReturn(expectedCity);

		City city = cityRepository.findByName("New York");
		assertEquals(expectedCity, city);
	}
}

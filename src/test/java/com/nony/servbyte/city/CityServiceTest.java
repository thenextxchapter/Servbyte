package com.nony.servbyte.city;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.nony.servbyte.exception.CityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CityServiceTest {
	@Mock
	private CityRepository cityRepo;

	@InjectMocks
	private CityService cityService;

	@Test
	void findAll_ShouldReturnAllCities() {
		List<City> cities = Arrays.asList(
				new City(1, "city1"),
				new City(2, "city2"));

		when(cityRepo.findAll()).thenReturn(cities);

		List<City> result = cityService.findAll();

		assertThat(result).isEqualTo(cities);
	}

	@Test
	void findByName_ShouldReturnCity_WhenCityExists() throws CityNotFoundException {
		City city = new City(1, "city1");

		when(cityRepo.findByName("city1")).thenReturn(city);

		City result = cityService.findByName("city1");

		assertThat(result).isEqualTo(city);
	}

	@Test
	void findByName_ShouldThrowCityNotFoundException_WhenCityDoesNotExist() {
		when(cityRepo.findByName("city1"))
				.thenThrow(NoSuchElementException.class);

		assertThatThrownBy(() ->
				cityService.findByName("city1"))
				.isInstanceOf(CityNotFoundException.class);
	}

	@Test
	void findById_ShouldReturnCity_WhenCityExists() throws CityNotFoundException {
		City city = new City(1, "city1");
		when(cityRepo.findById(1)).thenReturn(Optional.of(city));

		City result = cityService.findById(1);

		assertThat(result).isEqualTo(city);
	}

	@Test
	void findById_ShouldThrowCityNotFoundException_WhenCityDoesNotExist() {
		when(cityRepo.findById(1)).thenReturn(Optional.empty());

		assertThatThrownBy(() ->
				cityService.findById(1))
				.isInstanceOf(CityNotFoundException.class);
	}
}

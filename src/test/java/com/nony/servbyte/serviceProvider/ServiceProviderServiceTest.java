/*
package com.nony.servbyte.serviceProvider;

import static org.junit.jupiter.api.Assertions.*;

import com.nony.servbyte.city.CityRepository;
import com.nony.servbyte.exception.RestaurantNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceProviderServiceTest {

	private ServiceProviderService service;

	@Mock
	private ServiceProviderRepository serviceProviderRepo;

	@Mock
	private CityRepository cityRepo;

	@BeforeEach
	public void setUp() {
		service = new ServiceProviderService(serviceProviderRepo, cityRepo);
	}

	*/
/*@Test
	public void listAll_ShouldReturnListOfServiceProviders() {
		ResponseEntity<RestaurantView> expected = Arrays.asList(new ServiceProvider(), new ServiceProvider());
		when(serviceProviderRepo.findAll()).thenReturn(expected);
		List<ServiceProvider> result = service.listAll();
		assertEquals(expected, result);
	}*//*


	@Test
	public void getServiceProviderById_ShouldReturnServiceProvider() throws RestaurantNotFoundException {
		ServiceProvider expected = new ServiceProvider();
		when(serviceProviderRepo.findById(1)).thenReturn(java.util.Optional.of(expected));
		ServiceProvider result = service.getServiceProviderById(1);
		assertEquals(expected, result);
	}

	@Test
	public void getServiceProviderById_ShouldThrowException_WhenIdNotFound() {
		when(serviceProviderRepo.findById(1)).thenReturn(java.util.Optional.empty());
		assertThrows(RestaurantNotFoundException.class, () -> service.getServiceProviderById(1));
	}

	*/
/*@Test
	public void getServiceProviderByCity_ShouldReturnListOfServiceProviders() throws CityNotFoundException {
		List<ServiceProvider> expected = Arrays.asList(new ServiceProvider(), new ServiceProvider());
		when(serviceProviderRepo.findByCityId(1)).thenReturn(expected);
		List<ServiceProvider> result = service.getServiceProviderByCity(1);
		assertEquals(expected, result);
	}

	@Test
	public void getServiceProviderByCity_ShouldThrowException_WhenCityIdNotFound() {
		when(serviceProviderRepo.findByCityId(1)).thenThrow(NoSuchElementException.class);
		assertThrows(CityNotFoundException.class, () -> service.getServiceProviderByCity(1));
	}*//*


	*/
/*@Test
	public void getServiceProviderByName_ShouldReturnListOfServiceProviders() throws RestaurantNotFoundException {
		List<ServiceProvider> expected = Arrays.asList(new ServiceProvider(), new ServiceProvider());
		when(serviceProviderRepo.findByNameContainingIgnoreCase("name")).thenReturn(expected);
		List<ServiceProvider> result = service.getServiceProviderByName("name");
		assertEquals(expected, result);
	}

	@Test
	void getServiceProviderByName_ShouldThrowRestaurantNotFoundException_WhenNameIsNotPresent() {
		// Given
		when(serviceProviderRepo.findByNameContainingIgnoreCase(anyString())).thenThrow(NoSuchElementException.class);

		// When & Then
		assertThrows(RestaurantNotFoundException.class, () -> service.getServiceProviderByName("Test"));
	}*//*


	*/
/*@Test
	void getServiceProviderByType_ShouldReturnServiceProviders_WhenTypeIsPresent() throws RestaurantNotFoundException {
		// Given
		List<ServiceProvider> expectedProviders = Arrays.asList(new ServiceProvider(), new ServiceProvider());
		when(serviceProviderRepo.findByType(any(Type.class))).thenReturn(expectedProviders);

		// When
		List<ServiceProvider> actualProviders = service.getServiceProviderByType(Type.RESTAURANT);

		// Then
		assertEquals(expectedProviders, actualProviders);
	}

	@Test
	void getServiceProviderByType_ShouldThrowRestaurantNotFoundException_WhenTypeIsNotPresent() {
		// Given
		when(serviceProviderRepo.findByType(any(Type.class))).thenThrow(NoSuchElementException.class);

		// When & Then
		assertThrows(RestaurantNotFoundException.class, () -> service.getServiceProviderByType(Type.RESTAURANT));
	}*//*


	@Test
	void deleteServiceProvider_ShouldDeleteServiceProvider_WhenIdIsPresent() throws RestaurantNotFoundException {
		// Given
		doNothing().when(serviceProviderRepo).deleteById(anyInt());

		// When
		service.deleteServiceProvider(1);

		// Then
		verify(serviceProviderRepo, times(1)).deleteById(1);
	}

	@Test
	void deleteServiceProvider_ShouldThrowRestaurantNotFoundException_WhenIdIsNotPresent() {
		// Given
		doThrow(EmptyResultDataAccessException.class).when(serviceProviderRepo).deleteById(anyInt());

		// When & Then
		assertThrows(RestaurantNotFoundException.class, () -> service.deleteServiceProvider(1));
	}

}

*/

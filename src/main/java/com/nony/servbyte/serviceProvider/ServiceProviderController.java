package com.nony.servbyte.serviceProvider;

import com.nony.servbyte.exception.RestaurantNotFoundException;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Service Provider")
@RequestMapping(value = "/service-provider", produces = MediaType.APPLICATION_JSON_VALUE)
@OpenAPIDefinition(info = @Info(title = "Servbyte API", version = "v1"))
public class ServiceProviderController {

	private final ServiceProviderService serviceProviderService;

	@Autowired
	public ServiceProviderController(ServiceProviderService serviceProviderService) {
		this.serviceProviderService = serviceProviderService;
	}

	@GetMapping(path = "{id}")
	public ResponseEntity<ServiceProvider> findRestaurantById(@PathVariable("id") Integer id)
			throws RestaurantNotFoundException {
		ServiceProvider serviceProvider = serviceProviderService.getServiceProviderById(id);
		return ResponseEntity.ok(serviceProvider);
	}

	@DeleteMapping(path = "{id}")
	public ResponseEntity<Void> deleteRestaurant(@PathVariable("id") Integer id) throws RestaurantNotFoundException {
		serviceProviderService.deleteServiceProvider(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}

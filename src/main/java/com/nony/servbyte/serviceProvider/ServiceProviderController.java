package com.nony.servbyte.serviceProvider;

import com.nony.servbyte.param.Paginated;
import com.nony.servbyte.serviceProvider.param.RestaurantSearchParam;
import com.nony.servbyte.serviceProvider.value.RestaurantRequest;
import com.nony.servbyte.serviceProvider.view.RestaurantView;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@PostMapping
	public ResponseEntity<RestaurantView> create(@RequestBody @Valid RestaurantRequest restaurantRequest) {
		RestaurantView restaurant = serviceProviderService.create(restaurantRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
	}

	@GetMapping
	public ResponseEntity<Paginated<RestaurantView>> list(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "cityId", required = false) Integer cityId,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		RestaurantSearchParam searchParam = RestaurantSearchParam.builder()
				.name(name)
				.type(type)
				.cityId(cityId)
				.build();

		Paginated<RestaurantView> restaurants = serviceProviderService.list(searchParam, page, size);
		return ResponseEntity.ok(restaurants);
	}

	@PutMapping(path = "{id}")
	public ResponseEntity<ServiceProvider> update(@PathVariable("id") Integer id,
			@RequestBody @Valid RestaurantRequest request) {
		ServiceProvider restaurant = serviceProviderService.update(id, request);
		return ResponseEntity.status(HttpStatus.OK).body(restaurant);
	}

	@GetMapping(path = "{id}")
	public ResponseEntity<ServiceProvider> findRestaurantById(@PathVariable("id") Integer id) {
		ServiceProvider serviceProvider = serviceProviderService.getServiceProviderById(id);
		return ResponseEntity.ok(serviceProvider);
	}

	@DeleteMapping(path = "{id}")
	public ResponseEntity<Void> deleteRestaurant(@PathVariable("id") Integer id) {
		serviceProviderService.deleteServiceProvider(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}

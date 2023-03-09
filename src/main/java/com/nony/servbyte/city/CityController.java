package com.nony.servbyte.city;

import java.util.List;

import com.nony.servbyte.city.view.CityView;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "City")
@RequestMapping(value = "/city", produces = MediaType.APPLICATION_JSON_VALUE)
public class CityController {

	private final CityService cityService;

	@Autowired
	public CityController(CityService cityService) {
		this.cityService = cityService;
	}

	@PostMapping
	public ResponseEntity<CityView> create(@RequestBody @Valid String name) {
		CityView city = cityService.create(name);
		return ResponseEntity.status(HttpStatus.CREATED).body(city);
	}

	@GetMapping
	public ResponseEntity<List<CityView>> list(
			@RequestParam(value = "name", required = false) String name
	) {

		List<CityView> cities = cityService.list(name);
		return ResponseEntity.ok(cities);
	}

	@GetMapping(path = "{id}")
	public ResponseEntity<City> get(@PathVariable("id") Integer id) {
		City city = cityService.findById(id);
		return ResponseEntity.ok(city);
	}
}

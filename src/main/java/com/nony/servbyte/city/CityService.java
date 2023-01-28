package com.nony.servbyte.city;

import java.util.List;
import java.util.NoSuchElementException;

import com.nony.servbyte.exception.CityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CityService {

	private final CityRepository cityRepo;

	public CityService(CityRepository cityRepo) {
		this.cityRepo = cityRepo;
	}

	public List<City> findAll() {
		return cityRepo.findAll();
	}

	public City findByName(String name) throws CityNotFoundException {
		try {
			return cityRepo.findByName(name);
		} catch (NoSuchElementException exception) {
			throw new CityNotFoundException();
		}
	}

	public City findById(Integer id) throws CityNotFoundException {
		try {
			return cityRepo.findById(id).get();
		} catch (NoSuchElementException exception) {
			throw new CityNotFoundException();
		}
	}
}

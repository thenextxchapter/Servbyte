package com.nony.servbyte.city;

import static com.nony.servbyte.util.Helper.isNotNull;

import java.util.List;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViewSetting;
import com.nony.servbyte.city.view.CityView;
import com.nony.servbyte.exception.BadRequestException;
import com.nony.servbyte.exception.NotFoundException;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

	private final CityRepository cityRepo;

	private final EntityManager em;
	private final CriteriaBuilderFactory cbf;
	private final EntityViewManager evm;

	@Autowired
	public CityService(CityRepository cityRepo, EntityManager em,
			CriteriaBuilderFactory cbf, EntityViewManager evm) {
		this.cityRepo = cityRepo;
		this.em = em;
		this.cbf = cbf;
		this.evm = evm;
	}

	public CityView create(String name) {
		if (cityRepo.existsByName(name))
			throw new BadRequestException("City with name " + name + " already exists");

		City city = City.builder()
				.name(name)
				.build();

		City persistedCity = cityRepo.save(city);
		return evm.find(em, CityView.class, persistedCity.getId());
	}

	public List<CityView> list(String name) {
		EntityViewSetting<CityView, CriteriaBuilder<CityView>> setting =
				EntityViewSetting.create(CityView.class);

		CriteriaBuilder<City> cb = cbf.create(em, City.class);
		cb.orderByAsc("name").orderByDesc("id");

		if (isNotNull(name))
			setting.addAttributeFilter("name", name);

		CriteriaBuilder<CityView> cbv = evm.applySetting(setting, cb);
		return cbv.getResultList();
	}

	public City findById(Integer id) {
		return cityRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("City with id " + id + " not found"));
	}
}

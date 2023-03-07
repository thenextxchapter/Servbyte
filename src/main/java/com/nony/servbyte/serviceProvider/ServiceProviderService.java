package com.nony.servbyte.serviceProvider;

import static com.nony.servbyte.util.Helper.isNotNull;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.PagedList;
import com.blazebit.persistence.PaginatedCriteriaBuilder;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViewSetting;
import com.nony.servbyte.city.CityRepository;
import com.nony.servbyte.exception.BadRequestException;
import com.nony.servbyte.exception.ConflictException;
import com.nony.servbyte.exception.NotFoundException;
import com.nony.servbyte.param.Paginated;
import com.nony.servbyte.serviceProvider.param.RestaurantSearchParam;
import com.nony.servbyte.serviceProvider.value.RestaurantRequest;
import com.nony.servbyte.serviceProvider.view.RestaurantView;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class ServiceProviderService {
	private final ServiceProviderRepository serviceProvideRepo;
	private final CityRepository cityRepo;

	private EntityManager em;
	private CriteriaBuilderFactory cbf;
	private EntityViewManager evm;

	public ServiceProviderService(ServiceProviderRepository serviceProvideRepo, CityRepository cityRepo, EntityManager em,
			CriteriaBuilderFactory cbf, EntityViewManager evm) {
		this.serviceProvideRepo = serviceProvideRepo;
		this.cityRepo = cityRepo;
		this.em = em;
		this.cbf = cbf;
		this.evm = evm;
	}

	public RestaurantView create(RestaurantRequest request) {
		if (serviceProvideRepo.existsByName(request.getName()))
			throw new BadRequestException("Restaurant with name " + request.getName() + " already exists");

		if (!cityRepo.existsByName(request.getCity().getName()))
			throw new BadRequestException("City with name " + request.getCity().getName() + " does not exist");

		ServiceProvider serviceProvider = ServiceProvider.builder()
				.name(request.getName())
				.type(request.getType())
				.email(request.getEmail())
				.phoneNumber(request.getPhoneNumber())
				.city(request.getCity())
				.build();

		ServiceProvider persistedServiceProvider = serviceProvideRepo.save(serviceProvider);
		return evm.find(em, RestaurantView.class, persistedServiceProvider.getId());
	}

	public ServiceProvider update(Integer id, RestaurantRequest request) {
		ServiceProvider serviceProvider = get(id);

		if (serviceProvideRepo.existsByNameAndIdNot(request.getName(), id))
			throw new ConflictException("Restaurant with name " + request.getName() + " already exists");

		if (!cityRepo.existsByName(request.getCity().getName()))
			throw new BadRequestException("City with name " + request.getCity().getName() + " does not exist");

		serviceProvider.setName(request.getName());
		serviceProvider.setType(request.getType());
		serviceProvider.setEmail(request.getEmail());
		serviceProvider.setPhoneNumber(request.getPhoneNumber());
		serviceProvider.setCity(request.getCity());

		return serviceProvideRepo.save(serviceProvider);
	}

	public Paginated<RestaurantView> list(RestaurantSearchParam searchParam, int page, int size) {
		EntityViewSetting<RestaurantView, PaginatedCriteriaBuilder<RestaurantView>> setting =
				EntityViewSetting.create(RestaurantView.class, page, size);

		CriteriaBuilder<ServiceProvider> cb = cbf.create(em, ServiceProvider.class);
		cb.orderByAsc("name").orderByDesc("id");

		if (isNotNull(searchParam.getName()))
			setting.addAttributeFilter("name", searchParam.getName());
		if (isNotNull(searchParam.getType()))
			setting.addAttributeFilter("type", searchParam.getType());
		if (isNotNull(searchParam.getCityId()))
			setting.addAttributeFilter("city.id", searchParam.getCityId());

		PaginatedCriteriaBuilder<RestaurantView> pcb = evm.applySetting(setting, cb);
		pcb.withCountQuery(true);
		PagedList<RestaurantView> result = pcb.getResultList();
		return Paginated.of(result.getTotalSize(), result.getTotalPages(), result);
	}

	public ServiceProvider getServiceProviderById(Integer id){
		return get(id);
	}

	public void deleteServiceProvider(Integer id) {
		ServiceProvider serviceProvider = get(id);
		serviceProvideRepo.delete(serviceProvider);
	}

	private ServiceProvider get(Integer id){
		return serviceProvideRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("Restaurant not found"));
	}
}

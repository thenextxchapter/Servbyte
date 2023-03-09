package com.nony.servbyte.city.view;

import com.blazebit.persistence.view.AttributeFilter;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.filter.ContainsIgnoreCaseFilter;
import com.nony.servbyte.city.City;

@EntityView(City.class)
public interface CityView {
	@IdMapping
	Integer getId();
	@AttributeFilter(ContainsIgnoreCaseFilter.class)
	String getName();
}

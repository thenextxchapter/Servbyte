package com.nony.servbyte.configuration;

import com.blazebit.persistence.Criteria;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.spi.CriteriaBuilderConfiguration;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViews;
import com.blazebit.persistence.view.spi.EntityViewConfiguration;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
public class BlazePersistenceConfiguration {
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	@Lazy(false)
	public CriteriaBuilderFactory createCriteriaBuilderFactory() {
		CriteriaBuilderConfiguration config = Criteria.getDefault();
		return config.createCriteriaBuilderFactory(entityManagerFactory);
	}

	@Bean
	public EntityViewConfiguration entityViewConfiguration() {
		return EntityViews.createDefaultConfiguration();
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	@Lazy(false)
	// inject the criteria builder factory which will be used along with the entity view manager
	public EntityViewManager createEntityViewManager(CriteriaBuilderFactory cbf, EntityViewConfiguration entityViewConfiguration) {
		return entityViewConfiguration.createEntityViewManager(cbf);
	}
}

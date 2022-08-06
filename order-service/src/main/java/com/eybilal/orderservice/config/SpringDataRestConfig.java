package com.eybilal.orderservice.config;

import com.eybilal.orderservice.model.entity.Order;
import com.eybilal.orderservice.model.entity.OrderItem;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class SpringDataRestConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.setBasePath("/api/v1")
              .exposeIdsFor(Order.class, OrderItem.class);
    }
}

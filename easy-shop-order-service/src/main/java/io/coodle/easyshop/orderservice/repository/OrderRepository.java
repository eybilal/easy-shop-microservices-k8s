package io.coodle.easyshop.orderservice.repository;

import io.coodle.easyshop.orderservice.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Bilal El Yousfi
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}

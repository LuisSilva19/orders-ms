package br.com.microservices.microservice.order.repository;

import br.com.microservices.microservice.order.entity.Order;
import br.com.microservices.microservice.order.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(value = "select o.* from orders o " +
            "join  order_item oi " +
            "on (oi.order_id = o.id) " +
            "where o.id = :id", nativeQuery = true)
    Optional<Order> orderWithItems(@Param("id")Integer id);

}

package br.com.microservices.microservice.order.repository;

import br.com.microservices.microservice.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

}

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

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update orders o set o.status = :status where o.id = :orderId", nativeQuery = true)
    void updateStatus(@Param("status")Status status,
                      @Param("orderId")Integer orderId);

    @Query(value = "select o.* from order_item oi " +
            "join orders o " +
            "on (o.order_item_id  = oi.id) " +
            "where o.id = :id", nativeQuery = true)
    Optional<Order> orderWithItems(@Param("id")Integer id);

}

package br.com.microservices.microservice.order.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "order_item")
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Positive
    private Integer amount;

    private String description;
    private Integer paymentId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}

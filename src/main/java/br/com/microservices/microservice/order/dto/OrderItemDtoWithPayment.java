package br.com.microservices.microservice.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDtoWithPayment {
    private Integer amount;
    private String description;
    private Integer paymentId;
//    @JsonIgnore
//    private OrderDto orderDto;
}

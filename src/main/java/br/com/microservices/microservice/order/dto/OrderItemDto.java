package br.com.microservices.microservice.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {
    private Integer amount;
    private String description;
}

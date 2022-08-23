package br.com.microservices.microservice.order.dto;

import br.com.microservices.microservice.order.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderDto {
    private Integer id;
    private LocalDateTime dateTime;
    private Status status;
    private List<OrderItemDto> items = new ArrayList<>();
}

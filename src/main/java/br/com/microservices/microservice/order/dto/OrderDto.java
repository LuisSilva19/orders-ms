package br.com.microservices.microservice.order.dto;

import br.com.microservices.microservice.order.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private LocalDateTime dateTime;
    private Status status;
    private List<OrderItemDto> items = new ArrayList<>();



}

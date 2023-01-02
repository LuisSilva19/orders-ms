package br.com.microservices.microservice.order.dto;

import br.com.microservices.microservice.order.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentDTO {
    private Integer id;
    private BigDecimal value;
    private String name;
    private String number;
    private String expiration;
    private String cod;
    private Status status;
    private Integer orderId;
    private Integer formOfPayment;
}

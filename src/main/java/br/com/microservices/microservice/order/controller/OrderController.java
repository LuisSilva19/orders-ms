package br.com.microservices.microservice.order.controller;

import br.com.microservices.microservice.order.dto.OrderDto;
import br.com.microservices.microservice.order.dto.StatusDto;
import br.com.microservices.microservice.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

        private final OrderService orderService;

        @GetMapping()
        public List<OrderDto> findAll() {
            return orderService.findAll();
        }

        @GetMapping("/{id}")
        public ResponseEntity<OrderDto> findByID(@PathVariable @NotNull Integer id) {
            OrderDto dto = orderService.findById(id);
            return  ResponseEntity.ok(dto);
        }

        @PostMapping
        public ResponseEntity<OrderDto> createdOrder(@RequestBody @Valid OrderDto dto, UriComponentsBuilder uriBuilder) {
            OrderDto orderMade = orderService.createdOrder(dto);
            URI address = uriBuilder.path("/order/{id}").buildAndExpand(orderMade.getId()).toUri();

            return ResponseEntity.created(address).body(orderMade);
        }

        @PutMapping("/{id}/status")
        public ResponseEntity<OrderDto> updateStatus(@PathVariable Integer id, @RequestBody StatusDto status){
           OrderDto dto = orderService.updateStatus(id, status);

           return ResponseEntity.ok(dto);
        }

        @PutMapping("/{id}/paid")
        public ResponseEntity<Void> approvePaymentOrder(@PathVariable @NotNull Integer id) {
            orderService.approvePaymentOrder(id);

            return ResponseEntity.ok().build();

        }
}

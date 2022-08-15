package br.com.microservices.microservice.order.service;

import br.com.microservices.microservice.order.dto.OrderDto;
import br.com.microservices.microservice.order.dto.StatusDto;
import br.com.microservices.microservice.order.entity.Order;
import br.com.microservices.microservice.order.enums.Status;
import br.com.microservices.microservice.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    
    public List<OrderDto> findAll() {
        return orderRepository.findAll().stream()
                .map(p -> modelMapper.map(p, OrderDto.class))
                .collect(Collectors.toList());
    }

    public OrderDto findById(Integer id) {
       Order order = orderRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return modelMapper.map(order, OrderDto.class);
    }

    public OrderDto createdOrder(OrderDto dto) {
        Order order = modelMapper.map(dto,Order.class);

        order.setDateTime(LocalDateTime.now());
        order.setStatus(Status.ACCOMPLISHED);
        order.getItems().forEach(item -> item.setOrder(order));
        Order salvo = orderRepository.save(order);

        return modelMapper.map(order, OrderDto.class);
    }

    public OrderDto updateStatus(Integer id, StatusDto dto) {

        Order order = orderRepository.orderWithItems(id);

        if (order == null) {
            throw new EntityNotFoundException();
        }

        order.setStatus(dto.getStatus());
        orderRepository.updateStatus(dto.getStatus(), order.getId());
        return modelMapper.map(order, OrderDto.class);
    }

    public void approvePaymentOrder(Integer id) {

        Order order = orderRepository.orderWithItems(id);

        if (order == null) {
            throw new EntityNotFoundException();
        }

        order.setStatus(Status.PAID);
        orderRepository.updateStatus(Status.PAID, order.getId());
    }
}

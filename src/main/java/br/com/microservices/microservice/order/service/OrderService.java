package br.com.microservices.microservice.order.service;

import br.com.microservices.microservice.order.dto.OrderDto;
import br.com.microservices.microservice.order.dto.StatusDto;
import br.com.microservices.microservice.order.entity.Order;
import br.com.microservices.microservice.order.entity.OrderItem;
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
    private final OrderItemService orderItemService;
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

    public OrderDto createdOrder(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        order.setDateTime(LocalDateTime.now());
        order.setStatus(Status.ACCOMPLISHED);

        Order salvo = orderRepository.save(order);
        saveOrderItem(order);

        return modelMapper.map(salvo, OrderDto.class);
    }

    private void saveOrderItem(Order order) {
        List<OrderItem> orderItem = order.getItems();
        orderItem.forEach(item -> item.setOrder(order));

        orderItem.forEach(orderItemService::createOrderItem);
    }

    public OrderDto updateStatus(Integer id, StatusDto dto) {
        Order order = orderWithItems(id);

        order.setStatus(dto.getStatus());
        orderRepository.updateStatus(dto.getStatus(), order.getId());
        return modelMapper.map(order, OrderDto.class);
    }

    public void approvePaymentOrder(Integer id) {
        Order order = orderWithItems(id);
        order.setStatus(Status.PAID);
        orderRepository.updateStatus(Status.PAID, order.getId());
    }

    public Order orderWithItems(Integer id){
        return orderRepository.orderWithItems(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}

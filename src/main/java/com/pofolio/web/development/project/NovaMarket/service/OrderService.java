package com.pofolio.web.development.project.NovaMarket.service;

import com.pofolio.web.development.project.NovaMarket.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order saveOrder(Order order);

    List<Order> getOrderList();

    Optional<Order> getOrderById(Long id);

    Boolean deleteOrderById(Long id);
}

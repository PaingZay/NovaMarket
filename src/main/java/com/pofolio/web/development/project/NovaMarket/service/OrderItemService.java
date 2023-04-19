package com.pofolio.web.development.project.NovaMarket.service;

import com.pofolio.web.development.project.NovaMarket.entity.Order;
import com.pofolio.web.development.project.NovaMarket.entity.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderItemService {
    OrderItem saveOrderItem(OrderItem orderItem);

    List<OrderItem> getOrderItemList();

    Optional<OrderItem> getOrderItemById(Long id);

    Boolean deleteOrderItemById(Long id);
}

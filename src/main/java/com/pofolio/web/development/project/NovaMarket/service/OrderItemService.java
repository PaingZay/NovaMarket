package com.pofolio.web.development.project.NovaMarket.service;

import com.pofolio.web.development.project.NovaMarket.entity.CartItem;
import com.pofolio.web.development.project.NovaMarket.entity.Order;
import com.pofolio.web.development.project.NovaMarket.entity.OrderItem;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface OrderItemService {
    OrderItem saveOrderItem(OrderItem orderItem);

    List<OrderItem> getOrderItemList();

    Optional<OrderItem> getOrderItemById(Long id);

    Boolean deleteOrderItemById(Long id);

    Page<OrderItem> getAllOrderItems(int pageSize, int pageNumber, Long orderId);
}

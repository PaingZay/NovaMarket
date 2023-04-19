package com.pofolio.web.development.project.NovaMarket.service;

import com.pofolio.web.development.project.NovaMarket.entity.Order;
import com.pofolio.web.development.project.NovaMarket.entity.OrderItem;
import com.pofolio.web.development.project.NovaMarket.repository.OrderItemRepository;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService{

    @Resource
    OrderItemRepository orderItemRepository;


    @Transactional
    @Override
    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public List<OrderItem> getOrderItemList() {
        return orderItemRepository.findAll();
    }

    @Override
    public Optional<OrderItem> getOrderItemById(Long id) {
        return orderItemRepository.findById(id);
    }

    @Override
    public Boolean deleteOrderItemById(Long id) {
        try {
            orderItemRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

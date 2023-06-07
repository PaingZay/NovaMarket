package com.pofolio.web.development.project.NovaMarket.service;

import com.pofolio.web.development.project.NovaMarket.entity.Order;
import com.pofolio.web.development.project.NovaMarket.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    @Resource
    OrderRepository orderRepository;

    @Transactional
    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrderList() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Boolean deleteOrderById(Long id) {
        try {
            orderRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<Order> getSpecificOrder(Long customerId) {
        System.out.println( "In the IMPL" + customerId);
        return orderRepository.findSpecificOrder(customerId);
    }


}

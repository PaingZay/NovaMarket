package com.pofolio.web.development.project.NovaMarket.service;

import com.pofolio.web.development.project.NovaMarket.entity.CartItem;
import com.pofolio.web.development.project.NovaMarket.entity.Order;
import com.pofolio.web.development.project.NovaMarket.entity.OrderItem;
import com.pofolio.web.development.project.NovaMarket.repository.OrderItemRepository;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService{

    @Resource
    OrderItemRepository orderItemRepository;

    @Resource
    CartItemService cartItemService;


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

    @Override
    public Page<OrderItem> getAllOrderItems(int pageSize, int pageNumber, Long orderId) {

//        System.out.println("Cart Id"+cartId+"PageSize"+pageSize+"PageNumber"+pageNumber);

        Pageable page = PageRequest.of(pageNumber,pageSize);

        return orderItemRepository.getAllOrderItemsById(page, orderId);
    }

    @Override
    public List<OrderItem> copyRows(OrderItem orderItem, Long cartItemId) {

        //Find all cart items by using primary key first
        List<CartItem> cartItems = cartItemService.getCartItemsByCartItemId(cartItemId);
        List<OrderItem> savedOrderItems = new ArrayList<>();
        
        for(CartItem cartItem: cartItems) {
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPricePerUnit(cartItem.getPricePerUnit());
            orderItem.setTotalPrice(cartItem.getTotalPrice());
            OrderItem savedOrderItem = orderItemRepository.save(orderItem);
            savedOrderItems.add(savedOrderItem);
        }

        return savedOrderItems;
    }
}

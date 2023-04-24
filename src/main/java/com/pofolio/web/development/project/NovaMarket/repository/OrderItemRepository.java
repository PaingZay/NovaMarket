package com.pofolio.web.development.project.NovaMarket.repository;

import com.pofolio.web.development.project.NovaMarket.entity.CartItem;
import com.pofolio.web.development.project.NovaMarket.entity.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Query("SELECT oi FROM OrderItem oi where oi.order.id = :orderId")
    public Page<OrderItem> getAllOrderItemsById(Pageable page, @Param("orderId")Long orderId);
}

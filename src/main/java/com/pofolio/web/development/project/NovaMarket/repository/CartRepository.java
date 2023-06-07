package com.pofolio.web.development.project.NovaMarket.repository;

import com.pofolio.web.development.project.NovaMarket.entity.Cart;
import com.pofolio.web.development.project.NovaMarket.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    @Query("SELECT c FROM Cart c where c.customer.id = :id AND c.status = 'Active' ORDER BY c.id DESC")
    Optional<Cart> findSpecificCart(@Param("id") Long id);
}

package com.pofolio.web.development.project.NovaMarket.repository;

import com.pofolio.web.development.project.NovaMarket.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
}

package com.pofolio.web.development.project.NovaMarket.service;

import com.pofolio.web.development.project.NovaMarket.entity.Cart;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CartService {
    Cart saveCart(Cart cart);

    Optional<Cart> getCartById(Long id);

    List<Cart> getCartList();

    Boolean deleteCartById(Long id);

    Optional<Cart> getSpecificCart(Long id);

    Cart updateCart(Cart cart, Long cartId);

    Page<Cart> getCartList(int pageSize, int pageNumber);

}

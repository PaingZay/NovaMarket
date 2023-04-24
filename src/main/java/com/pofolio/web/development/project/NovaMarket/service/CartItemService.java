package com.pofolio.web.development.project.NovaMarket.service;

import com.pofolio.web.development.project.NovaMarket.entity.CartItem;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CartItemService {
    Optional<CartItem> getCartItemById(Long cartItemId);

    Page<CartItem> getAllCartItems(int pageSize, int pageNumber, Long cartId);

    List<CartItem> getCartItemListByCartId(Long cartId);

    CartItem addCartItem(CartItem cartItem);

    Boolean deleteCartItemById(Long cartItemId);

    Boolean removeAllCartItemsById(Long cartId);
}

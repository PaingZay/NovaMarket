package com.pofolio.web.development.project.NovaMarket.repository;


import com.pofolio.web.development.project.NovaMarket.entity.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    //Return Page
    @Query("SELECT c FROM CartItem c where c.cart.id = :cartId")
    public Page<CartItem> getAllCartItemsById(Pageable page, @Param("cartId")Long cartId);

    //Return List
    @Query("SELECT c FROM CartItem c where c.cart.id = :cartId")
    public List<CartItem> findCartItemsByCartId(@Param("cartId")Long cartId);

    @Query("SELECT ci FROM CartItem ci where ci.id = :cartItemId")
    public List<CartItem> findCartItemsByCartItemId(@Param("cartItemId")Long cartItemId);

    @Query("SELECT ci FROM CartItem ci where ci.product.id = :productId And ci.cart.id = :cartId")
    public Optional<CartItem> findByProductIdAndCartId(@Param("productId")Long productId, @Param("cartId")Long cartId);

    @Query("delete from CartItem c where c.id=:cartItemId")
    public Boolean removeCartItemById(@Param("cartItemId")Long cartItemId);

    @Query("delete from CartItem c where c.cart.id=:cartId")
    Boolean removeAllCartItemsById(@Param("cartId") Long cartId);

}

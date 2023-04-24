package com.pofolio.web.development.project.NovaMarket.repository;


import com.pofolio.web.development.project.NovaMarket.entity.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    //Return Page
    @Query("SELECT c FROM CartItem c where c.cart.id = :cartId")
    public Page<CartItem> getAllCartItemsById(Pageable page, @Param("cartId")Long cartId);

    //Return List
    @Query("SELECT c FROM CartItem c where c.cart.id = :cartId")
    public List<CartItem> findCartItemsByCartId(@Param("cartId")Long cartId);

    @Query("delete from CartItem c where c.id=:cartItemId")
    public Boolean removeCartItemById(@Param("cartItemId")Long cartItemId);

    @Query("delete from CartItem c where c.cart.id=:cartId")
    Boolean removeAllCartItemsById(@Param("cartId") Long cartId);

}

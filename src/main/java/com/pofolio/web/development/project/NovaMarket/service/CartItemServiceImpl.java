package com.pofolio.web.development.project.NovaMarket.service;

import com.pofolio.web.development.project.NovaMarket.entity.CartItem;
import com.pofolio.web.development.project.NovaMarket.repository.CartItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService{

    @Autowired
    CartItemRepository cartItemRepository;

    @Override
    public Optional<CartItem> getCartItemById(Long cartItemId) {
        return cartItemRepository.findById(cartItemId);
    }

    @Override
    public Page<CartItem> getAllCartItems(int pageSize, int pageNumber, Long cartId) {

//        System.out.println("Cart Id"+cartId+"PageSize"+pageSize+"PageNumber"+pageNumber);

        Pageable page = PageRequest.of(pageNumber,pageSize);
        System.out.println("savedCartItems"+cartItemRepository.getAllCartItemsById(page, cartId));
        return cartItemRepository.getAllCartItemsById(page, cartId);
    }

    @Override
    public CartItem addCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public Boolean deleteCartItemById(Long cartItemId) {
        try {
//            cartItemRepository.removeCartItemById(cartItemId);
            cartItemRepository.deleteById(cartItemId);
            System.out.println("TRUE");
            return true;
        } catch (Exception e) {
            System.out.println("FALSE");
            return false;
        }
    }

    //This is retrieve cart item from repository then delete. The same with above one
//    @Override
//    public Boolean deleteCartItemById(Long cartItemId) {
//        try {
//            Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
//            if(!cartItemOptional.equals(null)){
//               CartItem cartItem =  cartItemOptional.get();
//               cartItemRepository.delete(cartItem);
//               return true;
//            }
//            return false;
//        } catch (Exception e) {
//            return false;
//        }
//    }




    @Override
    public Boolean removeAllCartItemsById(Long cartId) {
        return cartItemRepository.removeAllCartItemsById(cartId);
    }

}
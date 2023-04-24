package com.pofolio.web.development.project.NovaMarket.service;

import com.pofolio.web.development.project.NovaMarket.entity.Cart;
import com.pofolio.web.development.project.NovaMarket.entity.CartItem;
import com.pofolio.web.development.project.NovaMarket.repository.CartItemRepository;
import com.pofolio.web.development.project.NovaMarket.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Override
    public Cart saveCart(Cart cart) {
        return cartRepository.saveAndFlush(cart);
    }

    @Override
    public Optional<Cart> getCartById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public List<Cart> getCartList() {
        return cartRepository.findAll();
    }

    @Override
    public Boolean deleteCartById(Long id) {
        try {
            cartRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<Cart> getSpecificCart(Long id) {
        return cartRepository.findSpecificCart(id);
    }

    @Override
    public Cart updateCart(Cart cart, Long cartId) {

        System.out.println("Step1: cartId" + cartId);

        //Check cart existence
        Cart savedCart = cartRepository.findById(cartId).get();

        System.out.println("SavedCart" + savedCart);

//        List<CartItem> cartItems = cartItemRepository.findCartItemsByCartId(4L);
//        System.out.println(cartItems.get(0).getProduct());

        //Check cart has anything inside
        List<CartItem> cartItems = cartItemRepository.findCartItemsByCartId(cartId);

        //Looping retrieved cart_items by using cart id
//        if (cartItems.size() > 0){
//            for (CartItem cartItem : cartItems) {
//
//                System.out.println("Before updating child" + cartItem.getProduct());
//
//            }
//        }

        //If there is no item in the cart(Actually no need to set status) Just return cart object
        if (cartItems.isEmpty()) {
            cart.setStatus("Cart is Empty");
            return cart;
        }

        //If there is at least one item in the cart set Completed status to Cart table and set updated Cart object to every row with this cartId
        if (Objects.nonNull(cart.getStatus())) {

            //Update Cart (parent table)
             savedCart.setStatus(cart.getStatus());
             Cart updatedCart = cartRepository.save(savedCart);

            //Update CartItem (child table)
            for (CartItem cartItem : cartItems) {
                cartItem.setCart(updatedCart);
                cartItemRepository.save(cartItem);
            }
         }
        return cartRepository.save(savedCart);
    }
}

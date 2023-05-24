package com.pofolio.web.development.project.NovaMarket.service;

import com.pofolio.web.development.project.NovaMarket.entity.Cart;
import com.pofolio.web.development.project.NovaMarket.entity.CartItem;
import com.pofolio.web.development.project.NovaMarket.repository.CartItemRepository;
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

    @Autowired
    CartService cartService;

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
    public List<CartItem> getCartItemListByCartId(Long cartId) {
        return cartItemRepository.findCartItemsByCartId(cartId);
    }

    @Override
    public List<CartItem> getCartItemsByCartItemId(Long cartItemId) {
        return cartItemRepository.findCartItemsByCartItemId(cartItemId);
    }

    //Set cart object in cartItem object on client side
    //Then send cartItem object to server

    @Override
    public CartItem addCartItem(CartItem cartItem) {

        Optional<CartItem> optExistingCartItem = cartItemRepository.findByProductIdAndCartId(cartItem.getProduct().getId(),cartItem.getCart().getId());

        if(!optExistingCartItem.isEmpty()) {
            CartItem existingCartItem = optExistingCartItem.get();

            Long existingProductId = existingCartItem.getProduct().getId();

            //Set ID
            //In order to use saveAndFlush we need to set the existing id to the new object otherwise it will generate a new Id and add a new row
            //Or you can just set the new quantity to the existing object and save it again
            cartItem.setId(optExistingCartItem.get().getId());

            if(cartItem.getProduct().getId() == existingProductId) {
                //Set new quantity
                cartItem.setQuantity(existingCartItem.getQuantity() + cartItem.getQuantity());
            }
        } else {
            System.out.println("No existing products with the same Id of the new item");
        }



        var total = cartItem.getPricePerUnit() * cartItem.getQuantity();
        cartItem.setTotalPrice(total);

        return cartItemRepository.saveAndFlush(cartItem);
    }

    @Override
    public boolean deleteCartItemById(Long cartItemId) {
        try {
            //cartItemRepository.removeCartItemById(cartItemId);

            cartItemRepository.deleteById(cartItemId);

            //CartItem cartItemdb = cartItemRepository.findById(cartItemId).get();
            //cartItemRepository.delete(cartItemdb);

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

    @Override
    public CartItem increaseQuantity(Long customerId, Long productId ) {

        Cart savedCart = cartService.getSpecificCart(customerId).get();

        List<CartItem> cartItems = cartItemRepository.findCartItemsByCartId(savedCart.getId());

        CartItem updatedCartItem = updateQuantity(cartItems, 1, productId);

        calculateTotal(cartItems);

        return updatedCartItem;
    }

    @Override
    public CartItem decreaseQuantity(Long customerId, Long productId ) {

        Cart savedCart = cartService.getSpecificCart(customerId).get();

        List<CartItem> cartItems = cartItemRepository.findCartItemsByCartId(savedCart.getId());

        CartItem updatedCartItem = updateQuantity(cartItems, -1, productId);

        calculateTotal(cartItems);

        return updatedCartItem;
    }

    public void calculateTotal(List<CartItem> cartItems) {
        double subtotal = 0;
        for(CartItem cartItem: cartItems){
            subtotal = cartItem.getPricePerUnit() * cartItem.getQuantity();
            cartItem.setTotalPrice(subtotal);
            cartItemRepository.saveAndFlush(cartItem);
        }
    }

//    @Override
//    public double calculateSubtotal(Long customerId) {
//        Cart savedCart = cartService.getSpecificCart(customerId).get();
//        List<CartItem> cartItems = cartItemRepository.findCartItemsByCartId(savedCart.getId());
//
//        CartItem updatedCartItem = new CartItem();
//        double subtotal = 0;
//        double total = 0;
//        for(CartItem cartItem: cartItems){
//                subtotal = cartItem.getPricePerUnit() * cartItem.getQuantity();
//                cartItem.setTotalPrice(subtotal);
//                cartItemRepository.saveAndFlush(cartItem);
//                total += subtotal;
//            }
//        return total;
//    }

    public CartItem updateQuantity(List<CartItem> cartItems, int num,Long productId) {
        CartItem updatedCartItem = new CartItem();
        for(CartItem cartItem: cartItems)
            if(cartItem.getProduct().getId() == productId) {
                cartItem.setQuantity(cartItem.getQuantity()+num);
                updatedCartItem = cartItemRepository.saveAndFlush(cartItem);
            }
        return updatedCartItem;
    }

}

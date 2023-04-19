package com.pofolio.web.development.project.NovaMarket.service;

import com.pofolio.web.development.project.NovaMarket.entity.Wishlist;

import java.util.Optional;

public interface WishlistService {
    Wishlist addToWishlist(Wishlist wishlist);

    Optional<Wishlist> findWishlistById(Long id);
}

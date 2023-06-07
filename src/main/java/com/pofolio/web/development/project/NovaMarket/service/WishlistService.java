package com.pofolio.web.development.project.NovaMarket.service;

import com.pofolio.web.development.project.NovaMarket.entity.Wishlist;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface WishlistService {
    Wishlist addToWishlist(Wishlist wishlist);

    Optional<Wishlist> findWishlistById(Long id);

    Page<Wishlist> getAllWishlists(int pageSize, int pageNumber);
}

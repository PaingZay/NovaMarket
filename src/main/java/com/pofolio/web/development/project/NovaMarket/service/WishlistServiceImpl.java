package com.pofolio.web.development.project.NovaMarket.service;

import com.pofolio.web.development.project.NovaMarket.entity.Wishlist;
import com.pofolio.web.development.project.NovaMarket.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistServiceImpl implements WishlistService{

    @Autowired
    WishlistRepository wishlistRepository;

    @Override
    public Wishlist addToWishlist(Wishlist wishlist) {
        System.out.println("ServiceImpl Wishlist" + wishlist);
        return wishlistRepository.save(wishlist);
    }

    @Override
    public Optional<Wishlist> findWishlistById(Long id) {
        return wishlistRepository.findById(id);
    }

    @Override
    public Page<Wishlist> getAllWishlists(int pageSize, int pageNumber) {
        Pageable page = PageRequest.of(pageNumber,pageSize);
        return wishlistRepository.findAll(page);
    }
}

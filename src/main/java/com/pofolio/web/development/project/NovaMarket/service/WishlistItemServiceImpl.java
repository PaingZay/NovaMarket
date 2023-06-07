package com.pofolio.web.development.project.NovaMarket.service;

import com.pofolio.web.development.project.NovaMarket.entity.WishlistItem;
import com.pofolio.web.development.project.NovaMarket.repository.WishlistItemRepository;
import com.pofolio.web.development.project.NovaMarket.service.WishlistItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistItemServiceImpl implements WishlistItemService {

    @Autowired
    WishlistItemRepository wishlistItemRepository;

    @Override
    public WishlistItem createWishlistItem(WishlistItem wishlistItem) {
        return wishlistItemRepository.save(wishlistItem);
    }
}

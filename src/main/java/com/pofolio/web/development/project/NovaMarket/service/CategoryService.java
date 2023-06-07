package com.pofolio.web.development.project.NovaMarket.service;

import com.pofolio.web.development.project.NovaMarket.entity.Category;

import java.util.Optional;

public interface CategoryService {
    Optional<Category> getCategoriesByName(String categoryName);
}

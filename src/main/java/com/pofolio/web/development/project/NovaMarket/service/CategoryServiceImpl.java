package com.pofolio.web.development.project.NovaMarket.service;

import com.pofolio.web.development.project.NovaMarket.entity.Category;
import com.pofolio.web.development.project.NovaMarket.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Optional<Category> getCategoriesByName(String categoryName) {
        return categoryRepository.findCategoryByName(categoryName);
    }
}

package com.pofolio.web.development.project.NovaMarket.repository;

import com.pofolio.web.development.project.NovaMarket.entity.Category;
import com.pofolio.web.development.project.NovaMarket.entity.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT cat FROM Category cat where cat.categoryName = :categoryName")
    public Optional<Category> findCategoryByName(@Param("categoryName") String categoryName);
}

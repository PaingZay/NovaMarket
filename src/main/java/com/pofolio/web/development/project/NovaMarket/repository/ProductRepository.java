package com.pofolio.web.development.project.NovaMarket.repository;

import com.pofolio.web.development.project.NovaMarket.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

//    @Query("SELECT e FROM Employee e where e.managerId = :mgrid")
//    List<Employee> findEmployeesByManagerId(@Param("mgrid") String mgrid);

    @Query("SELECT p FROM Product p where p.productName = :productName")
    Page<Product> findProductByName(Pageable page,@Param("productName") String productName);

    @Query("SELECT p FROM Product p WHERE p.productName LIKE %?1%"
            + " OR p.description LIKE %?1%"
//            + " OR p.madein LIKE %?1%"
            + " OR CONCAT(p.price, '') LIKE %?1%")
    public Page<Product> searchProducts(Pageable page,String keyword);

    @Query("SELECT p FROM Product p where price>:startPrice and price<:endPrice")
    public Page<Product> filterByPriceRange(Pageable page, @Param("startPrice")double startPrice, @Param("endPrice") double endPrice);

    @Query("SELECT p FROM Product p where p.category.id = :categoryId")
    Page<Product> findProductsByCategoryId(Pageable page,@Param("categoryId") Long categoryId);

}

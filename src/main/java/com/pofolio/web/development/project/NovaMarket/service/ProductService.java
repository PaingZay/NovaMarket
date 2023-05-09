package com.pofolio.web.development.project.NovaMarket.service;

import com.pofolio.web.development.project.NovaMarket.entity.Product;
import com.pofolio.web.development.project.NovaMarket.entity.Wishlist;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product saveProduct(Product product);

    Page<Product> getProductList(int pageSize, int pageNumber);

    Optional<Product> getProductById(Long id);

    Boolean deleteProductById(Long id);

    List<Product> saveProductList(List<Product> productList);

    Page<Product> findProductByName(String productName, int pageNumber, int pageSize);

    Product saveProduct2(String product, MultipartFile file) throws IOException;

    Page<Product> searchProduct(String keyword, int pageNumber, int pageSize);

    Page<Product> filterByPriceRange(int pageSize, int pageNumber, double startPrice, double endPrice);

    Optional<Product> findProductDetailsById(Long id);

    Page<Product> searchByCategory(int pageSize, int pageNumber, Long categoryId);
}

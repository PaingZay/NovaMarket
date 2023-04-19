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

    List<Product> findProductByName(String productName);

    Product saveProduct2(String product, MultipartFile file) throws IOException;

    List<Product> searchProduct(String keyword);

    Page<Product> filterByPriceRange(int pageSize, int pageNumber, double startPrice, double endPrice);

    Optional<Product> findProductDetailsById(Long id);
}

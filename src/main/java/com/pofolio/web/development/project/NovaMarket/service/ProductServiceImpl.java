package com.pofolio.web.development.project.NovaMarket.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pofolio.web.development.project.NovaMarket.entity.Product;
import com.pofolio.web.development.project.NovaMarket.repository.ProductRepository;
import com.pofolio.web.development.project.NovaMarket.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{



    @Autowired
    ProductRepository productRepository;

    @Autowired
    WishlistRepository wishlistRepository;

    @Autowired
    CategoryService categoryService;

    @Transactional
    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Page<Product> getProductList(int pageSize, int pageNumber) {
        Pageable page = PageRequest.of(pageNumber,pageSize);
        return productRepository.findAll(page);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Boolean deleteProductById(Long id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Product> saveProductList(List<Product> productList) {
        return productRepository.saveAll(productList);
    }

    @Override
    public Page<Product> findProductByName(String productName, int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber,pageSize);
        return productRepository.findProductByName(page, productName);
    }


    private final String FOLDER_PATH = "/Users/paingzay/Desktop/NovaImages/";

    @Override
    public Product saveProduct2(String product, MultipartFile file) throws IllegalStateException, IOException {

        Product productData = new Product();
        String filePath=FOLDER_PATH+file.getOriginalFilename();
        ObjectMapper objectMapper = new ObjectMapper();
        productData = objectMapper.readValue(product, Product.class);
        productData.setImageUrl(filePath);
        productData.setProductName(productData.getProductName());
        String productName = productData.getProductName();
        List<Product> existingProducts = new ArrayList<>();
        productRepository.saveAndFlush(productData);
        file.transferTo(new File(filePath));
        return productData;
    }

    @Override
    public Page<Product> searchProduct(String keyword, int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber,pageSize);
        return productRepository.searchProducts(page, keyword);
    }

    @Override
    public Page<Product> filterByPriceRange(int pageSize, int pageNumber, double startPrice, double endPrice) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        if (startPrice < endPrice && startPrice >= 0) {
            return productRepository.filterByPriceRange(page, startPrice, endPrice);
        }
            List<Product> data = new ArrayList<>();
            Page<Product> page1 = new PageImpl<>(data);
            return page1;
    }

    @Override
    public Optional<Product> findProductDetailsById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Page<Product> searchByCategory(int pageSize, int pageNumber, Long categoryId) {
        Pageable page = PageRequest.of(pageNumber,pageSize);
        return productRepository.findProductsByCategoryId(page, categoryId);
    }


}

package com.pofolio.web.development.project.NovaMarket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pofolio.web.development.project.NovaMarket.entity.Product;
import com.pofolio.web.development.project.NovaMarket.repository.ProductRepository;
import com.pofolio.web.development.project.NovaMarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @PostMapping(value = "/products", consumes = { MediaType.APPLICATION_JSON_VALUE,

    MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Product> saveBook(@RequestPart("product") String product, @RequestPart("file") MultipartFile file) {

//        log.info("Creating new product");
        try {
            Product savedProduct = productService.saveProduct2(product, file);
            if(savedProduct == null){
                return new ResponseEntity<Product>(savedProduct, HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        }

        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }














//    private final String FOLDER_PATH = "/Users/oscar/pdfStorage/";
//
//    @Override
//    public Book saveBook(String book, MultipartFile file) throws IllegalStateException, IOException {
//
//        Book bookData = new Book();
//        String filePath=FOLDER_PATH+file.getOriginalFilename();
//        ObjectMapper objectMapper = new ObjectMapper();
//        bookData = objectMapper.readValue(book, Book.class);
//        bookData.setPath(filePath);
//        bookData.setName(file.getOriginalFilename());
//        String bookNameck = bookData.getName();
//        String authorName = bookData.getMyAuthor().getName();
//        List<Book> exitBook = new ArrayList<>();
//        if( !bookNameck.equals(null)){
//
//            exitBook = bookRepository.findByName(bookNameck);
//        }
//        if(exitBook.size() != 0){
//
//            List<Book> authB = exitBook.stream().filter(e->e.getMyAuthor().getName().equals(authorName))
//                    .collect(Collectors.toList());
//            if(authB.size()==0){
//                bookRepository.saveAndFlush(bookData);
//                file.transferTo(new File(filePath));
//                return bookData;
//            }
//            else{
//                return null;
//            }
//        }
//        bookData.setPath(filePath);
//        bookData.setName(file.getOriginalFilename());
//        bookRepository.saveAndFlush(bookData);
//        file.transferTo(new File(filePath));
//        return bookData;
//    }
}

package com.pofolio.web.development.project.NovaMarket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "description")
    private String description;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "price")
    private Double price;

    @Column(name = "discount_price")
    private Double discountPrice;

    @Column(name = "manufacturer")
    private String manufacturer;

//    CLOSED FOR NOW
    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "dimension")
    private String dimension;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,
//            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
//    @JoinTable(
//            name = "order_items",
//            joinColumns = @JoinColumn(name = "product_id"),
//            inverseJoinColumns = @JoinColumn(name = "order_id")
//    )
//    private List<Order> orderList;

    public Product(String productName, String description, Long categoryId, Double price)
    {
        this.productName = productName;
        this.description = description;
        this.categoryId = categoryId;
        this.price = price;
    }
}
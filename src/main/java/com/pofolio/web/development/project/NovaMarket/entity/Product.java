package com.pofolio.web.development.project.NovaMarket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
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

    @Column(name = "price")
    private Double price;

    @Column(name = "sku")
    private int sku;

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

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,
//            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
//    @JoinTable(
//            name = "order_items",
//            joinColumns = @JoinColumn(name = "product_id"),
//            inverseJoinColumns = @JoinColumn(name = "order_id")
//    )
//    private List<Order> orderList;

    @OneToMany(targetEntity = CartItem.class, mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    @JsonIgnore//Need to check error
    private List<CartItem> cartItems;

    @OneToMany(targetEntity = Review.class, mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    @JsonIgnore
    private List<Review> reviewList;

    public Product(String productName, String description, Category category, Double price)
    {
        this.productName = productName;
        this.description = description;
        this.category = category;
        this.price = price;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", sku=" + sku +
                ", discountPrice=" + discountPrice +
                ", manufacturer='" + manufacturer + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", weight=" + weight +
                ", dimension='" + dimension + '\'' +
                ", category=" + category +
                '}';
    }
}

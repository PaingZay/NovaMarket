package com.pofolio.web.development.project.NovaMarket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_id")
    private Long id;

    @JoinColumn(name="customer_id")
    @ManyToOne
    private Customer customer;

    @Column(name="cart_created_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

    @Column(name = "status")
    private String status;

    @OneToMany(targetEntity = CartItem.class, mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    @JsonIgnore//Need to check error
    private List<CartItem> cartItems;

//    public Cart(Long id, Customer customer, LocalDate createdDate, String status) {
//        this.id = id;
//        this.customer = customer;
//        this.createdDate = createdDate;
//        this.status = status;
//    }
}

package com.pofolio.web.development.project.NovaMarket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @Column(name="payment_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "amount")
    private double amount;
}

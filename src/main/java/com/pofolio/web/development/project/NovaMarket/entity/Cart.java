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
@Table(name="cart")
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
}

package com.pofolio.web.development.project.NovaMarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="cart_items")
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

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price_per_unit")
    private double pricePerUnit;

    @Column(name = "total_price")
    private double totalPrice;

    public CartItem(Cart cart, Product product, int quantity, double pricePerUnit, double totalPrice) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
//                ", cart=" + cart +
//                ", product=" + product +
                ", quantity=" + quantity +
                ", pricePerUnit=" + pricePerUnit +
                ", totalPrice=" + totalPrice +
                '}';
    }
}

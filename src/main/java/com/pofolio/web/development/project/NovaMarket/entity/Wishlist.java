package com.pofolio.web.development.project.NovaMarket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="wishlists")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_id")
    private Long id;

    @JoinColumn(name="customer_id")
    @ManyToOne
    private Customer customer;

    @Column(name="wishlist_created_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

    //Sealed @ManyToMany

//    @ManyToMany(targetEntity = Product.class, cascade = {CascadeType.ALL, CascadeType.PERSIST}, fetch=FetchType.EAGER)
//    @JoinTable(name = "wishlist_items", joinColumns = {
//            @JoinColumn(name = "wishlist_id", referencedColumnName = "wishlist_id") }, inverseJoinColumns = {
//            @JoinColumn(name = "product_id", referencedColumnName = "product_id") }
//    )
//    private List<Product> productList;
//    private List<Product> productList;

    @OneToMany(targetEntity = WishlistItem.class, mappedBy = "wishlist", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    @JsonIgnore
    private List<WishlistItem> wishlistItemList;

    public void addWishlist(WishlistItem wishlistItem) {
        if (wishlistItemList==null) {
            List<WishlistItem> toAdd=new ArrayList<>();
            toAdd.add(wishlistItem);
            this.wishlistItemList=toAdd;
        }
        else {
            wishlistItemList.add(wishlistItem);
        }
    }

    @Override
    public String toString() {
        return "Wishlist{" +
                "id=" + id +
                ", customer=" + customer +
                ", createdDate=" + createdDate +
                '}';
    }
}

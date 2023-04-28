package com.pofolio.web.development.project.NovaMarket.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "product_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//    @JsonIdentityReference(alwaysAsId = true)
//    @JsonProperty("product_id")
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "customer_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//    @JsonIdentityReference(alwaysAsId = true)
//    @JsonProperty("customer_id")
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name="review_text")
    private String reviewText;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="review_date")
    private LocalDate reviewDate;

    @Column(name="review_rating")
    private double reviewRating;

    public Review(Product product, Customer customer, String reviewText, LocalDate reviewDate, double reviewRating) {
        this.product = product;
        this.customer = customer;
        this.reviewText = reviewText;
        this.reviewDate = reviewDate;
        this.reviewRating = reviewRating;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", product=" + product +
                ", customer=" + customer +
                ", reviewText='" + reviewText + '\'' +
                ", reviewDate=" + reviewDate +
                ", reviewRating=" + reviewRating +
                '}';
    }
}

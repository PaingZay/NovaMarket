package com.pofolio.web.development.project.NovaMarket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    private Long id;

    @Column(name="category_name")
    private String categoryName;

    @Override
    public String toString(){
        return "Category[Category Id:]";
    }

    @OneToMany(targetEntity = Product.class, mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnore
    private List<Product> products;
}

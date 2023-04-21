package com.pofolio.web.development.project.NovaMarket.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="lastname")
    private String lastName;

    @Email(message = "Email should be valid")
    @Size(max = 200)
    @Pattern(regexp = ".+@.+\\..+", message = "Wrong email!")
    @Column(name = "email")
    private String email;


    @Column(name = "password")
    private String password;

    @Column(name="address")
    private String address;

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    @Column(name="zip_code")
    private String zipCode;

    @Column(name="phoneNumber")
    private String phoneNumber;

    @Column(name="dateOfBirth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @OneToMany(targetEntity = Wishlist.class, mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Wishlist> wishlists;

    @OneToMany(targetEntity = Cart.class, mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Cart> cartList;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}

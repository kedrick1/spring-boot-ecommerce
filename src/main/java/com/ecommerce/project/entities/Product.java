package com.ecommerce.project.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "image")
    private String image;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Double price;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "special_price")
    private double specialPrice;

    @ManyToOne
    @JoinColumn(name = "category_id") //owns relationship
    private Category category;

    public Product(String productName, String image, String productDescription, Integer quantity, Double price, double discount, double specialPrice, Category category) {
        this.productName = productName;
        this.image = image;
        this.productDescription = productDescription;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
        this.specialPrice = specialPrice;
        this.category = category;
    }
}

package com.ecommerce.project.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="category")
@Data
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    private Long categoryId;

    @Column(name="category_name")
    @NotBlank
    @Size(min = 5, message = "Category name must contain at least 5 characters") //message is from Size.class
    private String categoryName;

    public Category(Long categoryId, String categoryName) {
        this.categoryName = categoryName;
    }

}

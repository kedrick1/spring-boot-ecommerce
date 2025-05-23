package com.ecommerce.project.services;


import com.ecommerce.project.dtos.ProductDTO;
import com.ecommerce.project.dtos.ProductResponse;
import com.ecommerce.project.entities.Product;

public interface ProductService {

    ProductDTO addProduct(Product product, Long categoryId);

    ProductResponse getAllProducts();

    ProductResponse searchByCategory(Long categoryId);
}

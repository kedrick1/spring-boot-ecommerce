package com.ecommerce.project.repositories;

import com.ecommerce.project.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT c FROM Cart c WHERE c.appUser.email = ?1")
    Cart findCartByEmail(String email);  //will need to define since accessing param of param, jpa doesnt automatically generate queries for nested objects fields

    @Query("SELECT c FROM Cart c WHERE c.appUser.email = ?1 AND c.cartId = ?2")
    Cart findCartByEmailAndCartId(String emailId, Long cartId);

    @Query("SELECT c FROM Cart c JOIN FETCH c.cartItems ci JOIN FETCH ci.product p WHERE p.productId = ?1")
    List<Cart> findCartsByProductId(Long productId);
}

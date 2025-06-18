package com.ecommerce.project.repositories;

import com.ecommerce.project.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT c FROM Cart c WHERE c.appUser.email = ?1")
    Cart findCartByEmail(String email);  //will need to define since accessing param of param, jpa doesnt automatically generate queries for nested objects fields
}

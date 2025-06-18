package com.ecommerce.project.repositories;

import com.ecommerce.project.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.cartId = ?1 AND ci.product.productId = ?2")
    CartItem findCartItemByProductIdAndCartID(Long cartId, Long productId);
}

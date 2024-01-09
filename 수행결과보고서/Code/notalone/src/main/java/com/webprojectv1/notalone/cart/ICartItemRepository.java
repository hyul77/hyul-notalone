package com.webprojectv1.notalone.cart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCart_CartIdAndProduct_ProductId(long cartId, long productId);
    List<CartItem> findCartItemByProduct_ProductId(long productId);
}

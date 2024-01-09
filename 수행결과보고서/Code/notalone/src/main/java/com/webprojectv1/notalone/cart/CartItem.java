package com.webprojectv1.notalone.cart;
import com.webprojectv1.notalone.product.Product;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartItemId;

    @Column(nullable = false)
    private int cartItemCount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="productId")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cartId")
    private Cart cart;

    public static CartItem createCartItem(Cart cart, Product product, int cartItemCount) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setCartItemCount(cartItemCount);
        return cartItem;
    }

    // 이미 담겨있는 물건 또 담을 경우 수량 증가
    public void addCount(int cartItemCount) {
        this.cartItemCount += cartItemCount;
    }
}

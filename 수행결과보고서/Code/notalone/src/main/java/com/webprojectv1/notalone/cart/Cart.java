package com.webprojectv1.notalone.cart;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

import com.webprojectv1.notalone.user.SiteUser;

@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartId;

    @Column(nullable = false)
    private int cartCount;

    @Column(nullable = false)
    private Date cartDate = new Date();
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id")
    private SiteUser siteUser;
    
    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItemList = new ArrayList<>();

    public static Cart createCart(SiteUser siteUser) {
        Cart cart = new Cart();
        cart.setCartCount(0);
        cart.setSiteUser(siteUser);
        return cart;
    }
}

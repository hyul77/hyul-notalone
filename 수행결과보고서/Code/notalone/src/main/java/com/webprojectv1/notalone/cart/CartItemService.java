package com.webprojectv1.notalone.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@Service
public class CartItemService {
    @Autowired
    private CartItemDao cartItemDao;

    // C(Insert) & U(Update)
    public void insertCartItem(CartItem cartItemDto) {
        log.info("[CartItemService] CartItem Insert And Update");

        cartItemDao.insertCartItem(cartItemDto);
    }

    // R(Select)
    public List<CartItem> selectCartItemAll() {
        log.info("[CartItemService] CartItem Select");
        List<CartItem> cartItemList = cartItemDao.selectCartItemAll();
        return cartItemList;
    }

    // D(Delete)
    public void deleteCartItem(long cartItemId) {
        log.info("[CartItemService] CartItem Delete");
        cartItemDao.deleteCartItem(cartItemId);
    }
}

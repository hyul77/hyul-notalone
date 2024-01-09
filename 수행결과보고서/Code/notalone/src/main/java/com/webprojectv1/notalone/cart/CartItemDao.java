package com.webprojectv1.notalone.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@Service
public class CartItemDao {
    @Autowired
    private ICartItemRepository cartItemRepository;

    // C(Insert) & U(Update)
    // save :  엔티티의 ID가 이미 존재하면 업데이트를 수행하고, 
    //         ID가 없으면 새로운 엔티티를 저장하기 때문에 합침
    public void insertCartItem(CartItem cartItemEntity) {
        log.info("[CartItemDao] CartItem Insert And Update : " + cartItemEntity.toString());
        cartItemRepository.save(cartItemEntity);
    }

    // R(Select)
    public List<CartItem> selectCartItemAll() {
        log.info("[CartItemDao] Cart Select All");
        List<CartItem> cartItemList = cartItemRepository.findAll();
        return cartItemList;
    }

     // D(Delete) : id
    public void deleteCartItem(long cartItemId) {
        CartItem savedCartItem = cartItemRepository.getReferenceById(cartItemId);
        // 삭제하고자 하는 데이터를 DB에서 확인
        if (savedCartItem == null) {
            log.info("[CartItemDao] Failed CartItem Delete : Does not exist. : " + cartItemId);
            return;
        }
        log.info("CartItem Delete");
        cartItemRepository.deleteById(cartItemId);
    }
}

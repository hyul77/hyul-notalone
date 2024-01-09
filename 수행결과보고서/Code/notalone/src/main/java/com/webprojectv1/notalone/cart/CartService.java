package com.webprojectv1.notalone.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webprojectv1.notalone.product.Product;
import com.webprojectv1.notalone.user.SiteUser;

import java.util.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CartService {
    @Autowired
    private CartDao cartDao;

    public void createCart(SiteUser user){
        cartDao.createCart(user);
    }

    // C(Insert) & U(Update)
    public void insertCart(SiteUser siteUserDto, Product productDto, int amount) {
        log.info("[CartService] Cart Insert");
        cartDao.insertCart(siteUserDto, productDto, amount);
    }

    // 카트 상품 리스트 중 해당하는 유저가 담은 상품만 반환
    // 유저의 카트 id와 카트상품의 카트 id가 같아야 함
    public List<CartItem> allUserCartView(Cart userCart) {
        log.info("[CartService] Cart allUserCartView");
        return cartDao.allUserCartView(userCart);
    }

    public Cart findUserCart(long id) {
        return cartDao.findCartBySiteUser_Id(id);
    }

    // R(Select)
    public List<Cart> selectCartAll() {
        log.info("[CartService] Cart Select");
        List<Cart> cartList = cartDao.selectCartAll();
        return cartList;
    }

    // D(Delete)
    public void deleteCart(long cartId) {
        log.info("[CartService] Cart Delete");
        cartDao.deleteCart(cartId);
    }

    public void allCartItemDelete(long id) {
        cartDao.allCartItemDelete(id);
    }
}

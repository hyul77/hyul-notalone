package com.webprojectv1.notalone.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webprojectv1.notalone.cart.CartItem;
import com.webprojectv1.notalone.user.SiteUser;

import java.util.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PurchaseService {
    @Autowired
    private PurchaseDao purchaseDao;

    public void createPurchase(SiteUser siteUser){
        purchaseDao.createPurchase(siteUser);
    }

    public void addPurchase(SiteUser siteUser, List<PurchaseItem> purchaseItemList) {
        purchaseDao.addPurchase(siteUser, purchaseItemList);
    }

    public PurchaseItem addCartPurchase(long productId, long id, CartItem cartItem) {
        return purchaseDao.addCartPurchase(productId, id, cartItem);
    }

    // id에 해당하는 주문 아이템 찾기
    public List<PurchaseItem> findUserPurchaseItemList(long id) {
        return purchaseDao.findUserPurchaseItemList(id);
    }

    // C(Insert) & U(Update)
    public void insertPurchase(Purchase purchaseDto) {
        log.info("[PurchaseService] purchase Insert And Update");
        purchaseDao.insertPurchase(purchaseDto);
    }

    // R(Select)
    public List<Purchase> selectPurchaseAll() {
        log.info("[purchaseService] purchase Select All");
        List<Purchase> purchaseList = purchaseDao.selectPurchaseAll();
        return purchaseList;
    }

    // D(Delete)
    public void deletePurchase(long purchaseId) {
        log.info("[purchaseService] purchase Delete");
        purchaseDao.deletePurchase(purchaseId);
    }
}

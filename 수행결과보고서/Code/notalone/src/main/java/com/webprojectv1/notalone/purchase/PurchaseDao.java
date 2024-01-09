package com.webprojectv1.notalone.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webprojectv1.notalone.cart.CartItem;
import com.webprojectv1.notalone.user.IUserRepository;
import com.webprojectv1.notalone.user.SiteUser;

import java.util.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PurchaseDao {
    @Autowired
    private IPurchaseRepository purchaseRepository;

    @Autowired
    private IPurchaseItemRepository purchaseItemRepository;

    @Autowired
    private IUserRepository userRepository;

    public void createPurchase(SiteUser siteUser){
        Purchase purchase = Purchase.createPurchase(siteUser);
        purchaseRepository.save(purchase);
    }

    public void addPurchase(SiteUser siteUser, List<PurchaseItem> purchaseItemList) {
        Purchase userPurchase = Purchase.createPurchase(siteUser, purchaseItemList);
        purchaseRepository.save(userPurchase);
    }

    public PurchaseItem addCartPurchase(long productId, long id, CartItem cartItem) {
        SiteUser siteUser = userRepository.getReferenceById(id);
        PurchaseItem purchaseItem = PurchaseItem.createPurchaseItem(productId, siteUser, cartItem);
        purchaseItemRepository.save(purchaseItem);
        return purchaseItem;
    }

    public List<PurchaseItem> findUserPurchaseItemList(long id) {
        return purchaseItemRepository.findPurchaseItemListBySiteUser_Id(id);
    }

    // C(Insert) & U(Update)
    public void insertPurchase(Purchase purchaseEntity) {
        log.info("[PurchaseDao] purchase : " + purchaseEntity.toString());
        purchaseRepository.save(purchaseEntity);
    }

    // R(Select)
    public List<Purchase> selectPurchaseAll() {
        log.info("[PurchaseDao] purchase Select All");
        List<Purchase> purchaseList = purchaseRepository.findAll();
        return purchaseList;
    }

     // D(Delete) : id
    public void deletePurchase(long purchaseId) {
        Purchase savedPurchase = purchaseRepository.getReferenceById(purchaseId);
        // 삭제하고자 하는 데이터를 DB에서 확인
        if (savedPurchase == null) {
            log.info("[purchaseDao] Failed purchase Delete : Does not exist. : " + purchaseId);
            return;
        }
        log.info("purchase Delete");
        purchaseRepository.deleteById(purchaseId);
    }
}

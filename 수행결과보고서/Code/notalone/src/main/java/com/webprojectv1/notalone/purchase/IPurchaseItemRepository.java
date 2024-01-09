package com.webprojectv1.notalone.purchase;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface IPurchaseItemRepository extends JpaRepository<PurchaseItem, Long> {
    List<PurchaseItem> findPurchaseItemListBySiteUser_Id(long userId);
    PurchaseItem findPurchaseItemByPurchaseItemId(long purchaseItemId);
}

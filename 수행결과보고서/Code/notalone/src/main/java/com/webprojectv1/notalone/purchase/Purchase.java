package com.webprojectv1.notalone.purchase;

import com.webprojectv1.notalone.user.SiteUser;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long purchaseId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private SiteUser siteUser;

    @OneToMany(mappedBy = "purchase")
    private List<PurchaseItem> purchaseItemList = new ArrayList<>();

    @Column(nullable = false)
    private Date purchaseDate = new Date();

    public void addPurchaseItem(PurchaseItem purchaseItem) {
        purchaseItemList.add(purchaseItem);
        purchaseItem.setPurchase(this);
    }

    public static Purchase createPurchase(SiteUser siteUser, List<PurchaseItem> purchaseItemList) {
        Purchase purchase = new Purchase();
        purchase.setSiteUser(siteUser);
        for (PurchaseItem purchaseItem : purchaseItemList) {
            purchase.addPurchaseItem(purchaseItem);
        }
        purchase.setPurchaseDate(purchase.purchaseDate);
        return purchase;
    }

    public static Purchase createPurchase(SiteUser siteUser) {
        Purchase purchase = new Purchase();
        purchase.setSiteUser(siteUser);
        purchase.setPurchaseDate(purchase.purchaseDate);
        return purchase;
    }

}

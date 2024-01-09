package com.webprojectv1.notalone.purchase;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface IPurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findPurchaseListBySiteUser_Id(long id);
}

package com.webprojectv1.notalone.cart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartRepository extends JpaRepository<Cart, Long>{
    Cart findBySiteUser_Id(long id);
    Cart findCartBySiteUser_Id(long id);
}

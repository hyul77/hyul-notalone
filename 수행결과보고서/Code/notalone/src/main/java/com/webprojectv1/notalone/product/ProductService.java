package com.webprojectv1.notalone.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    // C(Insert)
    public void inserProduct(Product productDto) {
        log.info("[ProductService] Product Insert : " + productDto.toString());
        productDao.insertProduct(productDto);
    }


    // R(Select All)
    public List<Product> selectProductAll() {
        List<Product> productList = productDao.selectProductAll();
        return productList;
    }

    // R (Sselect One)
    public Product selectProductOne(long productId) {
        Product productEntity = productDao.selectProductOne(productId);
        log.info(productEntity.toString());
        return productEntity;
    }

    // U(Update)
    public void updateProduct(Product productDto, Long productId) {
        productDao.updateProduct(productDto, productId);
    }

    // D(Delete)
    public void deleteProduct(long productId) {
        log.info("[ProductService] Product Delete");
        productDao.deleteProduct(productId);
    }
}

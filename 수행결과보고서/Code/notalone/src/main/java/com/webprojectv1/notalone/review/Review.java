package com.webprojectv1.notalone.review;

import com.webprojectv1.notalone.product.Product;
import com.webprojectv1.notalone.user.SiteUser;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Review {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reviewId;
    
    @Column(nullable = false)
    private double reviewRating;
    
    @Column(nullable = false)
    private String reviewContent;
    
    @ManyToOne
    private SiteUser siteUser;

    @ManyToOne
    private Product product;
    
}

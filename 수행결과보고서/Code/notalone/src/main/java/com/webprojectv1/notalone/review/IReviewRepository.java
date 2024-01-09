package com.webprojectv1.notalone.review;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IReviewRepository extends JpaRepository<Review, Long> {
    
}

package com.webprojectv1.notalone.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReviewDao {
    @Autowired
    private IReviewRepository reviewRepository;

    // C(Insert) & U(Update)
    // save :  엔티티의 ID가 이미 존재하면 업데이트를 수행하고, 
    //         ID가 없으면 새로운 엔티티를 저장하기 때문에 합침
    public void insertUpdateReview(Review reviewEntity) {
        log.info("[ReviewDao] Review Insert And Update : " + reviewEntity.toString());
        // User user = userRepository.findById(reviewEntity.);
        // Product product = productRepository.findById(reviewEntity.getProduct());
        reviewRepository.save(reviewEntity);
    }

    // R(Select)
    public List<Review> selectReviewAll() {
        log.info("[ReviewDao] Review Select All");
        
        List<Review> reviewList = reviewRepository.findAll();
        return reviewList;
    }

     // D(Delete) : id
    public void deleteReview(long reviewId) {
        Review savedReview = reviewRepository.getReferenceById(reviewId);
        // 삭제하고자 하는 데이터를 DB에서 확인
        if (savedReview == null) {
            log.info("[ReviewDao] Failed Review Delete : Does not exist. : " + reviewId);
            return;
        }
        log.info("Review Delete");
        reviewRepository.deleteById(reviewId);
    }
}

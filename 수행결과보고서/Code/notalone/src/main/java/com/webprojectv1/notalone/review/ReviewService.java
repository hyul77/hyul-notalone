package com.webprojectv1.notalone.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReviewService {
    @Autowired
    private ReviewDao reviewDao;

    // C(Insert) & U(Update)
    public void insertAndUpdateUser(Review reviewDto) {
        log.info("[ReviewService] Review Insert And Update");
        reviewDao.insertUpdateReview(reviewDto);
    }

    // R(Select)
    public List<Review> selectUserAll() {
        log.info("[ReviewService] Review Select");
        List<Review> reviewList = reviewDao.selectReviewAll();
        return reviewList;
    }

    // D(Delete)
    public void deleteUser(long reviewId) {
        log.info("[ReviewService] Review Delete");
        reviewDao.deleteReview(reviewId);
    }
}

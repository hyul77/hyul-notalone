package com.webprojectv1.notalone.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDao {
    @Autowired
    private IUserRepository userRepository;

    // C(Insert) & U(Update)
    // save :  엔티티의 ID가 이미 존재하면 업데이트를 수행하고, 
    //         ID가 없으면 새로운 엔티티를 저장하기 때문에 합침
    public void insertUpdateUser(SiteUser userEntity) {
        log.info("[UserDao] User Insert And Update : " + userEntity.toString());
        userRepository.save(userEntity);
    }

    // R(Select)
    public List<SiteUser> selectUserAll() {
        log.info("[UserDao] User Select All");
        // userRepository에서 select * from user;
        List<SiteUser> userList = userRepository.findAll();
        return userList;
    }

    public SiteUser selectUserOne(long id) {
        log.info("[ProductDao] Product Select One");
        // productRepository에서 select * from product;
        SiteUser siteUser = userRepository.getReferenceById(id);
        return siteUser;
    }

     // D(Delete) : id
    public void deleteUser(long userId) {
        SiteUser savedUser = userRepository.getReferenceById(userId);
        // 삭제하고자 하는 데이터를 DB에서 확인
        if (savedUser == null) {
            log.info("[UserDao] Failed User Delete : Does not exist. : " + userId);
            return;
        }
        log.info("Product Delete");
        userRepository.deleteById(userId);
    }
}

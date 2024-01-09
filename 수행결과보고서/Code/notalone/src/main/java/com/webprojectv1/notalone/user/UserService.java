package com.webprojectv1.notalone.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.webprojectv1.notalone.DataNotFoundException;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDao userDao;

    // C(Insert) & U(Update)
    public void insertAndUpdateUser(SiteUser userDto) {
        log.info("[UserService] User Insert And Update");
        userDao.insertUpdateUser(userDto);
    }

    // R(Select)
    public List<SiteUser> selectUserAll() {
        log.info("[UserService] User Select");
        List<SiteUser> userList = userDao.selectUserAll();
        return userList;
    }

    public SiteUser selectUserOne(long id) {
        SiteUser siteUser = userDao.selectUserOne(id);
        return siteUser;
    }

    // D(Delete)
    public void deleteUser(long userId) {
        log.info("[UserService] User Delete");
        userDao.deleteUser(userId);
    }

    public SiteUser create(String userId, String password) {
        SiteUser user = new SiteUser();
        user.setUserId(userId);
        user.setUserPw(passwordEncoder.encode(password));
        this.userRepository.save(user);
        return user;
    }

    public SiteUser getUser(String userId){
        Optional<SiteUser> siteUser = this.userRepository.findByUserId(userId);
        if (siteUser.isPresent()) {
            return siteUser.get();
        } else {
            throw new DataNotFoundException("siteuser not found");
        }
    }

}

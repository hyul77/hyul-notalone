package com.webprojectv1.notalone.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<SiteUser, Long> {
    Optional<SiteUser> findByUserId(String userId);

}

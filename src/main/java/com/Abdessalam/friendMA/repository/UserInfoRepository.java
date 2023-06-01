package com.Abdessalam.friendMA.repository;

import com.Abdessalam.friendMA.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
    UserInfo findByUserId(String userId);
}

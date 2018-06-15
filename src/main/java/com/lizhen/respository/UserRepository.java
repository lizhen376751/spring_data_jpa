package com.lizhen.respository;

import com.lizhen.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2018\6\15 0015.
 */

public interface UserRepository extends JpaRepository<UserInfo,Long> {
    UserInfo findByName(String name);
}

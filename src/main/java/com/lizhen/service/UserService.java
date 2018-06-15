package com.lizhen.service;

import com.lizhen.entity.UserInfo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Administrator on 2018\6\15 0015.
 */
public interface UserService {
    List<UserInfo> getUserList();

    UserInfo getUserByName(String name);

    UserInfo addUserInfo(UserInfo userInfo);

    UserInfo updateUserInfoById(UserInfo userInfo);

    void deleteUserInfoById(Long Id);

    List<UserInfo> getCurrentUserList();

    Page<UserInfo> getPageUserList();

}

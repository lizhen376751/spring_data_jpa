package com.lizhen2.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Administrator on 2018\6\15 0015.
 */
@Entity
@Table(name="USER")
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    //账号
    private String account;

    //姓名
    private String name;

    //密码
    private String password;

    // 邮箱
    private String email;
}

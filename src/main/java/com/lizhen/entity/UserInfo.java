package com.lizhen.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018\6\15 0015.
 */
@Entity
@Table(name="UserInfo")
@Getter
@Setter
@Component
public class UserInfo implements Serializable {
    @Id
    @GeneratedValue
    private Long id; //ID
    private String name; //姓名
    private String jobNumber; //工号
    private Date createTime; //创建时间



}

package com.hg.user.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by 浩发 on 2020/7/12 09:55
 */
@Entity
@Data
@Table(name = "t_user")
@EqualsAndHashCode
public class User {

    @Id
    @GenericGenerator(name="uuid",strategy="uuid2")
    @GeneratedValue(generator = "uuid")
    public String userId;
    public String userName;
    public String password;
    public Integer age;
    public String mobile;

}

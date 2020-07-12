package com.hg.user.repository;

import com.hg.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 浩发 on 2020/7/12 10:07
 */
@Repository
public interface UserRepository extends JpaRepository<User,String> {

    User findByUserNameAndPassword(String userName,String password);

}

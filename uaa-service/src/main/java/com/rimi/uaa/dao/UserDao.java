package com.rimi.uaa.dao;

import com.rimi.uaa.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author shangzf
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return
     */
    User findByUsername(String username);
}

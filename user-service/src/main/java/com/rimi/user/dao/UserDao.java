package com.rimi.user.dao;

import com.rimi.user.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户DAO
 *
 * @author szf
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {

    /**
     * 根据用户名查找用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    User findByUsername(String username);
}

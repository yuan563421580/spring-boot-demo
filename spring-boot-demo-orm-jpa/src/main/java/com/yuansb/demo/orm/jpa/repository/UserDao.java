package com.yuansb.demo.orm.jpa.repository;

import com.yuansb.demo.orm.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User Dao
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {
    
}

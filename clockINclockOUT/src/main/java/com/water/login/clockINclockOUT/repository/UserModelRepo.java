package com.water.login.clockINclockOUT.repository;

import com.water.login.clockINclockOUT.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserModelRepo extends JpaRepository<UserEntity,Integer> {
}

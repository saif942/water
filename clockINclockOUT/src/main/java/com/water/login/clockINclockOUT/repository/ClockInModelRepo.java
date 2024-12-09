package com.water.login.clockINclockOUT.repository;

import com.water.login.clockINclockOUT.entity.ClockInOutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClockInModelRepo extends JpaRepository<ClockInOutEntity,Integer> {

    List<ClockInOutEntity> findByUserID(int userID);
}

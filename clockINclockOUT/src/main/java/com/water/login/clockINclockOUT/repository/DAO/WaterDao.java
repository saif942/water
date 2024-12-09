package com.water.login.clockINclockOUT.repository.DAO;

import com.water.login.clockINclockOUT.Exception.WaterCheckINException;
import com.water.login.clockINclockOUT.entity.ClockINOUTLogEntity;
import com.water.login.clockINclockOUT.entity.ClockInOutEntity;
import com.water.login.clockINclockOUT.entity.UserEntity;
import com.water.login.clockINclockOUT.repository.ClockINOUTLogRepo;
import com.water.login.clockINclockOUT.repository.ClockInModelRepo;
import com.water.login.clockINclockOUT.repository.UserModelRepo;
import com.water.login.clockINclockOUT.util.Constant;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class WaterDao {
    private final ClockInModelRepo clockInModelRepo;
    private final UserModelRepo userModelRepo;
    private final ClockINOUTLogRepo clockINOUTLogRepo;

    public WaterDao(ClockInModelRepo clockInModelRepo, UserModelRepo userModelRepo, ClockINOUTLogRepo clockINOUTLogRepo) {
        this.clockInModelRepo = clockInModelRepo;
        this.userModelRepo = userModelRepo;
        this.clockINOUTLogRepo = clockINOUTLogRepo;
    }

    @SneakyThrows
    public UserEntity getUserInfo(int userID)
    {
        Optional<UserEntity> userModelOptional = userModelRepo.findById(userID);
        if(userModelOptional.isPresent()){
            return userModelOptional.get();
        }else{
            throw new WaterCheckINException(Constant.INVALID_USER);
        }
    }

    @SneakyThrows
    public ClockInOutEntity getUserCheckINOUTDetail(int userID)
    {
        System.out.println("Hello there "+userID);
        List<ClockInOutEntity> clockInOutEntity = clockInModelRepo.findByUserID(userID);
        System.out.println("Hello there ff "+ clockInOutEntity);
        if(!clockInOutEntity.isEmpty()){
            return clockInOutEntity.get(0);
        }else{
            throw new WaterCheckINException(Constant.USER_NOT_IN_CHECK_IN_OUT);
        }
    }

    @Transactional
    public void clockOut(UserEntity userEntity, ClockINOUTLogEntity clockINOUTLogEntity)
    {
        userModelRepo.save(userEntity);
        clockInModelRepo.deleteById(clockINOUTLogEntity.getId());
        clockINOUTLogRepo.save(clockINOUTLogEntity);
    }

    @Transactional
    public void clockIn(UserEntity userEntity, ClockInOutEntity clockInOutEntity)
    {
        userModelRepo.save(userEntity);
        clockInModelRepo.save(clockInOutEntity);

    }
}

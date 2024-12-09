package com.water.login.clockINclockOUT.service;

import com.water.login.clockINclockOUT.Model.ClockInModel;
import com.water.login.clockINclockOUT.Model.ClockOutModel;
import com.water.login.clockINclockOUT.dto.UserClockInDTO;
import com.water.login.clockINclockOUT.dto.UserClockOutDTO;
import com.water.login.clockINclockOUT.entity.ClockINOUTLogEntity;
import com.water.login.clockINclockOUT.entity.ClockInOutEntity;
import com.water.login.clockINclockOUT.entity.UserEntity;
import com.water.login.clockINclockOUT.repository.DAO.WaterDao;
import com.water.login.clockINclockOUT.util.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClockInService {
    private final WaterDao waterDao;
    private final ClockInModel clockInModel;
    private final ClockOutModel clockOutModel;

    public ClockInService(WaterDao waterDao, ClockInModel clockInModel, ClockOutModel clockOutModel) {
        this.waterDao = waterDao;
        this.clockInModel = clockInModel;
        this.clockOutModel = clockOutModel;
    }

    public ResponseEntity<String> clockIn(UserClockInDTO userClockInDTO){
        UserEntity userEntity = waterDao.getUserInfo(userClockInDTO.getUserID());
        if(Constant.CHECK_OUT.equals(userEntity.getStatus())) {
            ClockInOutEntity entity = clockInModel.toEntity(userClockInDTO);
            userEntity.setStatus(Constant.CHECK_IN);
            waterDao.clockIn(userEntity,entity);
            return new ResponseEntity<>(Constant.CHECK_IN_MESSAGE, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Constant.USER_ALREADY_IN,HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<String> clockOut(UserClockOutDTO userClockOutDTO){
        UserEntity userEntity = waterDao.getUserInfo(userClockOutDTO.getUserID());
        if(Constant.CHECK_IN.equals(userEntity.getStatus())) {
            ClockINOUTLogEntity userClockOutLogModel = clockOutModel.toEntity(userClockOutDTO);
            userEntity.setStatus(Constant.CHECK_OUT);
            waterDao.clockOut(userEntity,userClockOutLogModel);
            return new ResponseEntity<>(Constant.CHECKOUT_MESSAGE, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Constant.USER_ALREADY_OUT,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> checkStatus(int userID) {
        UserEntity userEntity = waterDao.getUserInfo(userID);
        return new ResponseEntity<>(userEntity.getStatus(),HttpStatus.OK);
    }
}

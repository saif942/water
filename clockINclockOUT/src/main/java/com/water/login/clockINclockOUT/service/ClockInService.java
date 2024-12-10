package com.water.login.clockINclockOUT.service;

import com.water.login.clockINclockOUT.Model.ClockInModel;
import com.water.login.clockINclockOUT.Model.ClockOutModel;
import com.water.login.clockINclockOUT.config.JwtUtils;
import com.water.login.clockINclockOUT.dto.UserClockInDTO;
import com.water.login.clockINclockOUT.dto.UserClockOutDTO;
import com.water.login.clockINclockOUT.dto.UserModelDto;
import com.water.login.clockINclockOUT.entity.ClockINOUTLogEntity;
import com.water.login.clockINclockOUT.entity.ClockInOutEntity;
import com.water.login.clockINclockOUT.entity.UserEntity;
import com.water.login.clockINclockOUT.repository.DAO.WaterDao;
import com.water.login.clockINclockOUT.util.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClockInService {
    private final WaterDao waterDao;
    private final ClockInModel clockInModel;
    private final ClockOutModel clockOutModel;
    private final UserDetailServiceImpl userDetailService;
    private final JwtUtils jwtUtils;

    public ClockInService(WaterDao waterDao, ClockInModel clockInModel, ClockOutModel clockOutModel, UserDetailServiceImpl userDetailService, JwtUtils jwtUtils) {
        this.waterDao = waterDao;
        this.clockInModel = clockInModel;
        this.clockOutModel = clockOutModel;
        this.userDetailService = userDetailService;
        this.jwtUtils = jwtUtils;
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

    public ResponseEntity<String> generateToken(UserModelDto userModelDto) {
        if(userModelDto.getUserID().isEmpty() || userModelDto.getPassword().isEmpty()) {
            return new ResponseEntity<>(Constant.INVALID_USERID_PASSWORD,HttpStatus.BAD_REQUEST);
        }else{
            UserDetails userDetails = userDetailService.loadUserByUsername(userModelDto.getUserID());
            String encodedPassword = new BCryptPasswordEncoder().encode(userModelDto.getPassword());
            if(userDetails.getPassword().equals(encodedPassword)) {
                return new ResponseEntity<>(jwtUtils.generateToken(userDetails), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(Constant.INVALID_USERID_PASSWORD,HttpStatus.BAD_REQUEST);
            }
        }
    }
}

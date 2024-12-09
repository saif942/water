package com.water.login.clockINclockOUT.Model;

import com.water.login.clockINclockOUT.dto.UserClockOutDTO;
import com.water.login.clockINclockOUT.entity.ClockINOUTLogEntity;
import com.water.login.clockINclockOUT.entity.ClockInOutEntity;
import com.water.login.clockINclockOUT.repository.DAO.WaterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClockOutModel {

    @Autowired
    WaterDao waterDao;

    public ClockINOUTLogEntity toEntity(UserClockOutDTO userClockOutDTO) {
        ClockInOutEntity userCheckINOUTDetail = waterDao.getUserCheckINOUTDetail(userClockOutDTO.getUserID());
        ClockINOUTLogEntity clockINOUTLogEntity = new ClockINOUTLogEntity();
        clockINOUTLogEntity.setId(userCheckINOUTDetail.getId());
        clockINOUTLogEntity.setUserID(userCheckINOUTDetail.getUserID());
        clockINOUTLogEntity.setClockInGeoLocation(userCheckINOUTDetail.getClockInGeoLocation());
        clockINOUTLogEntity.setClockIn(userCheckINOUTDetail.getClockIn());
        clockINOUTLogEntity.setClockOutGeoLocation(userClockOutDTO.getCheckOutLocation());
        clockINOUTLogEntity.setClockOut(userClockOutDTO.getCheckOutTime());
        return clockINOUTLogEntity;
    }
}

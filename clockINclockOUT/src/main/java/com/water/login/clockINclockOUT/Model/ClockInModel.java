package com.water.login.clockINclockOUT.Model;

import com.water.login.clockINclockOUT.dto.UserClockInDTO;
import com.water.login.clockINclockOUT.entity.ClockInOutEntity;
import org.springframework.stereotype.Component;

@Component
public class ClockInModel {

    public ClockInOutEntity toEntity(UserClockInDTO userClockInDTO){
        ClockInOutEntity clockInOutEntity = new ClockInOutEntity();
        clockInOutEntity.setUserID(userClockInDTO.getUserID());
        clockInOutEntity.setClockIn(userClockInDTO.getClockInTime());
        clockInOutEntity.setClockOut(null);
        clockInOutEntity.setClockInGeoLocation(userClockInDTO.getClockInLocation());
        clockInOutEntity.setClockOutGeoLocation(null);
        return clockInOutEntity;
    }
}

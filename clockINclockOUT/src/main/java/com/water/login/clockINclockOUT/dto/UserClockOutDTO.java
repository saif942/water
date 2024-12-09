package com.water.login.clockINclockOUT.dto;

import com.water.login.clockINclockOUT.entity.ClockINOUTLogEntity;
import com.water.login.clockINclockOUT.entity.ClockInOutEntity;
import com.water.login.clockINclockOUT.repository.DAO.WaterDao;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Data
public class UserClockOutDTO {

    private int userID;
    private String checkOutLocation;
    private LocalDateTime checkOutTime;
}

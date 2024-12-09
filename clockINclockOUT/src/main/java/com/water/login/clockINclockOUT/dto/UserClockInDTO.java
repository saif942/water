package com.water.login.clockINclockOUT.dto;

import com.water.login.clockINclockOUT.entity.ClockInOutEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserClockInDTO {

    private int userID;
    private String clockInLocation;
    private LocalDateTime clockInTime;
}

package com.water.login.clockINclockOUT.entity;

import com.water.login.clockINclockOUT.util.Constant;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name= Constant.IN_OUT_LOG_TABLE)
@NoArgsConstructor
public class ClockINOUTLogEntity {
    @Id
    private int id;
    private int userID;
    private String clockInGeoLocation;
    private String clockOutGeoLocation;
    private LocalDateTime clockIn;
    private LocalDateTime clockOut;
}

package com.water.login.clockINclockOUT.entity;

import com.water.login.clockINclockOUT.util.Constant;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name= Constant.IN_OUT_TABLE)
@NoArgsConstructor
public class ClockInOutEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userID;
    private String clockInGeoLocation;
    private String clockOutGeoLocation;
    private LocalDateTime clockIn;
    private LocalDateTime clockOut;
}

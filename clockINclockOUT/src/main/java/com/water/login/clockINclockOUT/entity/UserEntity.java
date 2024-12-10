package com.water.login.clockINclockOUT.entity;

import com.water.login.clockINclockOUT.util.Constant;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name= Constant.USER_TABLE)
public class UserEntity {

    @Id
    private int userID;
    private String userName;
    private String status;
    private String password;
}

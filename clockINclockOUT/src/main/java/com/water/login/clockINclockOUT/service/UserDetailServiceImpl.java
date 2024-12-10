package com.water.login.clockINclockOUT.service;

import com.water.login.clockINclockOUT.entity.UserEntity;
import com.water.login.clockINclockOUT.repository.DAO.WaterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    WaterDao waterDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userInfo = waterDao.getUserInfo(Integer.parseInt(username));
        return new User(username,userInfo.getPassword(),new ArrayList<>());
    }
}

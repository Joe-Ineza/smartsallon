package com.SSWebApp.SmartSallonWebApp.service;

import com.SSWebApp.SmartSallonWebApp.dto.CustomerDTO;
import com.SSWebApp.SmartSallonWebApp.dto.UsersDTO;

import java.util.List;

public interface UsersService {
    UsersDTO createUsers(UsersDTO usersDTO);

    List<UsersDTO> getAllUsersByEmail(String email);

}

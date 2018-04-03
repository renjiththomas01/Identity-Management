package com.springboot.identitymanagement.backend;

import java.util.List;

public interface UserService {

    UserDTO create(UserDTO user);

    String delete(String loginUserId, String deleteUserId);

    List<UserDTO> findAll();

    UserDTO read(String id);
    
    boolean login(String userName, String password);

    UserDTO update(UserDTO user);
}

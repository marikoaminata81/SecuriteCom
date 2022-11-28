package com.example.demo.Service;

import com.example.demo.Modeles.Role;
import com.example.demo.Modeles.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username,String roleNom);
    static User getUser(String username);
    List<User>getUsers();
}

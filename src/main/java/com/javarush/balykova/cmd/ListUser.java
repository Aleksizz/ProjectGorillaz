package com.javarush.balykova.cmd;

import com.javarush.balykova.entity.User;
import com.javarush.balykova.service.UserService;
import com.javarush.balykova.util.RequestHelpers;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

import java.util.Collection;

@SuppressWarnings("unused")
@AllArgsConstructor
public class ListUser implements Command {

    private final UserService userService;

    @Override
    public String doGet(HttpServletRequest request) {

        Collection<User> users = userService.getAll();
        request.setAttribute("users", users);
        return getView();
    }


}
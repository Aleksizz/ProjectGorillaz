package com.javarush.balykova.cmd;

import com.javarush.balykova.entity.Role;
import com.javarush.balykova.entity.User;
import com.javarush.balykova.service.UserService;
import com.javarush.balykova.util.Go;
import com.javarush.balykova.util.Key;
import com.javarush.balykova.util.RequestHelpers;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@SuppressWarnings("unused")
@AllArgsConstructor
public class Signup implements Command {

    private final UserService userService;

    @Override
    @SneakyThrows
    public String doPost(HttpServletRequest request) {

        User user = User.builder()
                .login(request.getParameter(Key.LOGIN))
                .password(request.getParameter(Key.PASSWORD))
                .role(Role.USER)
                .build();
        userService.create(user);
        HttpSession session = request.getSession();
        session.setAttribute(Key.USER, user);
        return Go.PROFILE;
    }
}
package com.javarush.balykova.cmd;

import com.javarush.balykova.entity.User;
import com.javarush.balykova.service.UserService;
import com.javarush.balykova.util.Go;
import com.javarush.balykova.util.Key;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

import java.util.Optional;

@SuppressWarnings("unused")
@AllArgsConstructor
public class Login implements Command {

    private final UserService userService;

    @Override
    public String doPost(HttpServletRequest request) {

        String login = request.getParameter(Key.LOGIN);
        String password = request.getParameter(Key.PASSWORD);
        Optional<User> user = userService.get(login, password);
        if (user.isPresent()) {
            HttpSession session = request.getSession();
            session.setAttribute(Key.USER, user.get());
            return Go.PROFILE;
        } else {
            return Go.LOGIN;
        }
    }
}
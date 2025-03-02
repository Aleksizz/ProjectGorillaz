package com.javarush.balykova.cmd;

import com.javarush.balykova.entity.Role;
import com.javarush.balykova.entity.User;
import com.javarush.balykova.service.UserService;
import com.javarush.balykova.util.Key;
import com.javarush.balykova.util.RequestHelpers;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@SuppressWarnings("unused")
@AllArgsConstructor
public class EditUser implements Command {

    private final UserService userService;

    @Override
    public String doGet(HttpServletRequest req) {

        String stringId = req.getParameter(Key.ID);
        if (stringId != null) {
            long id = Long.parseLong(stringId);
            userService.get(id)
                    .ifPresent(user -> req.setAttribute(Key.USER, user));
        }
        return getView();
    }

    @Override
    @SneakyThrows
    public String doPost(HttpServletRequest req) {

        long id = Long.parseLong(req.getParameter(Key.ID));
        User user = User.builder()
                .id(id)
                .login(req.getParameter(Key.LOGIN))
                .password(req.getParameter(Key.PASSWORD))
                .role(Role.valueOf(req.getParameter(Key.ROLE)))
                .build();
        userService.update(user);
        return getView() + "?id=" + user.getId();
    }
}
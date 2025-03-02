package com.javarush.balykova.cmd;

import com.javarush.balykova.entity.User;
import com.javarush.balykova.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;


@SuppressWarnings("unused")
public class EditUser implements Command {

    private final UserService userService;

    public EditUser(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String doGet(HttpServletRequest req) {
        String stringId = req.getParameter("id");
        if (stringId != null) {
            long id = Long.parseLong(stringId);
            Optional<User> optionalUser = userService.get(id);
            optionalUser.ifPresent(user ->  req.setAttribute("user", optionalUser.get()));
        }
        return getView();
    }

    @Override
    public String doPost(HttpServletRequest req) {
        User user = User.builder()
                .login(req.getParameter("login"))
                .password(req.getParameter("password"))
                .build();
        if (req.getParameter("create") != null) {
            userService.create(user);
        } else if (req.getParameter("update") != null) {
            user.setId(Long.parseLong(req.getParameter("id")));
            userService.update(user);
        }
        return getView() + "?id=" + user.getId();
    }


}
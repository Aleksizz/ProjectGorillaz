package com.javarush.balykova.cmd;

import com.javarush.balykova.entity.Role;
import com.javarush.balykova.entity.User;
import com.javarush.balykova.service.UserService;
import com.javarush.balykova.util.Go;
import com.javarush.balykova.util.Key;
import com.javarush.balykova.util.RequestHelpers;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.Optional;

@AllArgsConstructor
public class DeleteUser implements Command {

    public static final String SUCCESS_DELETED = "Пользователь успешно удален.";
    public static final String NO_PERMISSION = "NO PERMISSION";
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

        Optional<User> admin = RequestHelpers.getUser(req.getSession());
        if (admin.isPresent() && admin.get().getRole() == Role.ADMIN) {
            User user = User.builder()
                    .id(admin.get().getId())
                    .login(req.getParameter(Key.LOGIN))
                    .password(req.getParameter(Key.PASSWORD))
                    .role(admin.get().getRole())
                    .build();
            userService.delete(user); // Удаление пользователя
            req.setAttribute("message", SUCCESS_DELETED);
        } else {
            req.setAttribute("message", NO_PERMISSION);
        }

        return Go.LIST_USER;
    }
}

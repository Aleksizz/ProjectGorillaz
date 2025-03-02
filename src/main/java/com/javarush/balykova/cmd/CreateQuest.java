package com.javarush.balykova.cmd;

import com.javarush.balykova.entity.User;
import com.javarush.balykova.service.QuestService;
import com.javarush.balykova.util.Go;
import com.javarush.balykova.util.Key;
import com.javarush.balykova.util.RequestHelpers;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class CreateQuest implements Command {

    private final QuestService questService;

    @Override
    public String doPost(HttpServletRequest request) {
        String name = request.getParameter(Key.NAME);
        String text = request.getParameter(Key.TEXT);
        Optional<User> optionalUser = RequestHelpers.getUser(request.getSession());
        optionalUser.ifPresent(user -> questService.create(name, text, user.getId()));
        return Go.HOME;
    }
}
package com.javarush.balykova.cmd;

import com.javarush.balykova.entity.Question;
import com.javarush.balykova.entity.Role;
import com.javarush.balykova.entity.User;
import com.javarush.balykova.service.QuestService;
import com.javarush.balykova.service.QuestionService;
import com.javarush.balykova.util.Go;
import com.javarush.balykova.util.Key;
import com.javarush.balykova.util.RequestHelpers;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.Optional;

import static com.javarush.balykova.util.Key.QUEST;


@AllArgsConstructor
public class Quest implements Command {

    private final QuestService questService;
    private final QuestionService questionService;



    @Override
    public String doGet(HttpServletRequest req) {
        long id = RequestHelpers.getId(req);
        Optional<com.javarush.balykova.entity.Quest> quest = questService.get(id);
        req.setAttribute(QUEST, quest.orElseThrow());
        return getView();
    }

}
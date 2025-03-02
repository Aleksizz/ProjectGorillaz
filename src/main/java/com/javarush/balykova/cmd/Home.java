package com.javarush.balykova.cmd;

import com.javarush.balykova.service.QuestService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

import static com.javarush.balykova.util.Key.QUESTS;

@SuppressWarnings("unused")
@AllArgsConstructor
public class Home implements Command {

    private final QuestService questService;

    @Override
    public String doGet(HttpServletRequest req) {
        req.setAttribute(QUESTS, questService.getAll());
        return getView();
    }
}

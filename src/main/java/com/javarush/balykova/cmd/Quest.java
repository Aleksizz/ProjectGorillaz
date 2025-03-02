package com.javarush.balykova.cmd;

import com.javarush.balykova.entity.Question;
import com.javarush.balykova.entity.Role;
import com.javarush.balykova.entity.User;
import com.javarush.balykova.service.ImageService;
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
    private final ImageService imageService;


    @Override
    public String doGet(HttpServletRequest req) {
        long id = RequestHelpers.getId(req);
        Optional<com.javarush.balykova.entity.Quest> quest = questService.get(id);
        req.setAttribute(QUEST, quest.orElseThrow());
        return getView();
    }

    @Override
    @SneakyThrows
    public String doPost(HttpServletRequest req) {
        Optional<User> editor = RequestHelpers.getUser(req.getSession());
        if (editor.isPresent() && editor.get().getRole() == Role.ADMIN) {
            Long id = RequestHelpers.getId(req);
            Long questionId = RequestHelpers.getId(req, "questionId");
            String text = req.getParameter(Key.TEXT);
            Optional<Question> question = questionService.update(questionId, text);
            if (question.isPresent()) {
                imageService.uploadImage(req, question.get().getImage());
            }
            return "%s?id=%d#bookmark%d".formatted(Go.QUEST, id, questionId);
        } else {
            return Go.QUEST; //TODO добавить ошибку, что "Недостаточно прав для редактирования";
        }
    }
}

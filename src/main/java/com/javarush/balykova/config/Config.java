package com.javarush.balykova.config;

import com.javarush.balykova.entity.Role;
import com.javarush.balykova.entity.User;
import com.javarush.balykova.service.QuestService;
import com.javarush.balykova.service.UserService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Config {

    private final UserService userService;

    private final QuestService questService;

    public void fillEmptyRepository() {
        if (userService.get(1L).isEmpty()) {
            User admin = biuldUser("Carl", "admin", Role.ADMIN);
            userService.create(admin);
            User alisa = biuldUser("Alisa", "qwerty", Role.USER);
            userService.create(alisa);
            User bob = biuldUser("Bob", "123", Role.GUEST);
            userService.create(bob);

            addDemoQuests(admin);
        }
    }

    private static User biuldUser(String name, String password, Role role) {
        return User.builder()
                .login(name)
                .password(password)
                .role(role)
                .build();
    }

    private void addDemoQuests(User author) {
        Long authorId = author.getId();
        questService.create(
                "Играем в неопознанный летающий объект (обязательный квест)",
                """
                        1: Ты потерял память. Принять вызов НЛО?
                        2<  Принять вызов
                        91< Отклонить вызов
                        
                        2: Ты принял вызов. Подняться на мостик к капитану?
                        92< Отказаться подниматься на мостик
                        3< Подняться на мостик
                        
                        3: Ты поднялся на мостик. Ты кто?
                        93< Солгать о себе
                        99< Рассказать правду
                        
                        91- Ты отклонил вызов. Поражение.
                        92- Ты не пошел на переговоры. Поражение.
                        93- Твою ложь разоблачили. Поражение.
                        
                        99+ Вы выиграли
                        """,
                authorId
        );

    }
}
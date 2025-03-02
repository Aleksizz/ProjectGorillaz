package com.javarush.balykova.cmd;

import com.javarush.balykova.BaseIT;
import com.javarush.balykova.config.Winter;
import com.javarush.balykova.util.Key;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class QuestIT extends BaseIT {

    private final Quest quest = Winter.find(Quest.class);

    @Test
    void whenOpenQuestPageWithCorrectId_thenGetJsp() {
        Mockito.when(request.getParameter(Key.ID)).thenReturn("1");
        String jsp = quest.doGet(request);
        Assertions.assertEquals("quest", jsp);
    }
}
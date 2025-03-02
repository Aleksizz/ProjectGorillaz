package com.javarush.balykova.cmd;

import com.javarush.balykova.BaseIT;
import com.javarush.balykova.config.Winter;
import com.javarush.balykova.entity.Game;
import com.javarush.balykova.entity.Question;
import com.javarush.balykova.util.Key;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PlayGameIT extends BaseIT {

    private final PlayGame playGame = Winter.find(PlayGame.class);

    @Test
    void whenStartGame_thenSetGameAndQuestionInRequest() throws ServletException {
        when(session.getAttribute(Key.USER)).thenReturn(testUser);
        when(request.getParameter(Key.QUEST_ID)).thenReturn("1");
        String jspPage = playGame.doGet(request);

        assertEquals("play-game", jspPage);
        verify(request).setAttribute(eq(Key.GAME), any(Game.class));
        verify(request).setAttribute(eq(Key.QUESTION), any(Question.class));
    }

}
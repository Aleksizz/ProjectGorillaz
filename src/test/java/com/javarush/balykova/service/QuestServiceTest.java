package com.javarush.balykova.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.javarush.balykova.entity.Quest;
import com.javarush.balykova.entity.Question;
import com.javarush.balykova.entity.User;
import com.javarush.balykova.repository.AnswerRepository;
import com.javarush.balykova.repository.QuestRepository;
import com.javarush.balykova.repository.QuestionRepository;
import com.javarush.balykova.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuestServiceTest {
    private QuestService questService;
    private UserRepository userRepository;
    private QuestRepository questRepository;
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        questRepository = mock(QuestRepository.class);
        questionRepository = mock(QuestionRepository.class);
        answerRepository = mock(AnswerRepository.class);
        questService = new QuestService(userRepository, questRepository, questionRepository, answerRepository);
    }

    @Test
    public void testCreateQuest() {
        Long userId = 1L;
        String questName = "Test Quest";
        String questText = "1: Question 1\n2: Question 2";
        User user = new User();

        when(userRepository.get(userId)).thenReturn(user);
        when(questRepository.getAll()).thenReturn(new ArrayList<>());

        Optional<Quest> questOptional = questService.create(questName, questText, userId);

        assertTrue(questOptional.isPresent());
        Quest quest = questOptional.get();
        assertEquals(questName, quest.getName());
        assertEquals(userId, quest.getAuthorId());

        ArgumentCaptor<Question> questionCaptor = ArgumentCaptor.forClass(Question.class);
        verify(questionRepository, times(2)).create(questionCaptor.capture());
        List<Question> createdQuestions = questionCaptor.getAllValues();
        assertEquals(2, createdQuestions.size());
        assertEquals("Question 1", createdQuestions.get(0).getText());
        assertEquals("Question 2", createdQuestions.get(1).getText());
    }
}

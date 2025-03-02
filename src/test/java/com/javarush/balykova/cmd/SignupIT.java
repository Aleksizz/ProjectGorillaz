package com.javarush.balykova.cmd;

import com.javarush.balykova.BaseIT;
import com.javarush.balykova.config.Winter;
import com.javarush.balykova.repository.UserRepository;
import com.javarush.balykova.util.Go;
import com.javarush.balykova.util.Key;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class SignupIT extends BaseIT {

    private final Signup signup = Winter.find(Signup.class);
    private final UserRepository repository = Winter.find(UserRepository.class);

    @Test
    void doPost() {
        Mockito.when(request.getParameter(Key.LOGIN)).thenReturn("newTestLogin");
        Mockito.when(request.getParameter(Key.PASSWORD)).thenReturn("newTestPassword");
        Mockito.when(request.getParameter(Key.ROLE)).thenReturn("GUEST");

        String uri = signup.doPost(request);
        Assertions.assertEquals(Go.PROFILE, uri);
        Assertions.assertTrue(repository.getAll().toString().contains("newTestLogin"));
    }
}
package com.javarush.balykova.cmd;

import com.javarush.balykova.BaseIT;
import com.javarush.balykova.config.Winter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class LogoutIT extends BaseIT {

    private final Logout logout = Winter.find(Logout.class);

    @Test
    void whenOpenPage_thenInvalidateSession() {
        logout.doGet(request);
        Mockito.verify(session).invalidate();
    }

}
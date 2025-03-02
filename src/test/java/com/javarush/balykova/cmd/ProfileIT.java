package com.javarush.balykova.cmd;

import com.javarush.balykova.BaseIT;
import com.javarush.balykova.config.Winter;
import com.javarush.balykova.util.Go;
import com.javarush.balykova.util.Key;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ProfileIT extends BaseIT {

    private final Profile profile = Winter.find(Profile.class);

    @Test
    void whenClickEditInProfile_thenGoToEditUserPage() {
        Mockito.when(session.getAttribute(Key.USER)).thenReturn(testUser);
        String uri = profile.doPost(request);
        Assertions.assertEquals(Go.EDIT_USER + "?id=" + testUser.getId(), uri);
    }

    @Test
    void whenClickLogout_thenGoLogout() {
        Mockito.when(request.getParameter(Key.LOGOUT)).thenReturn("true");
        String uri = profile.doPost(request);
        Assertions.assertEquals(Go.LOGOUT, uri);
    }
}
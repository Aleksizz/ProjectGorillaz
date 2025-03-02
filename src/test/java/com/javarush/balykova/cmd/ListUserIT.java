package com.javarush.balykova.cmd;

import com.javarush.balykova.BaseIT;
import com.javarush.balykova.config.Winter;
import com.javarush.balykova.util.Key;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ListUserIT extends BaseIT {

    ListUser listUser = Winter.find(ListUser.class);

    @Test
    void whenGetListUsers_thenReturnJspPage() {
        String jspPage = listUser.doGet(request);

        assertEquals("list-user", jspPage);
        verify(request).setAttribute(eq(Key.USERS), any(Collection.class));
    }
}
package sample;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {


    @Test
    public void canSetDataintoObjectUser(){
        User user = new User();
        user.setMeno("michal");
        user.setPassword("heslo");
        user.setId(2);

        String name=user.getMeno();
        String password=user.getPassword();
        int id=user.getId();

        assertEquals("michal",name);
        assertEquals("heslo",password);
        assertEquals(2,id);


    }




}
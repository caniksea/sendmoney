package com.caniksea.poll.mamamoney.factory;

import com.caniksea.poll.mamamoney.model.UssdMenu;
import org.junit.Test;

import static org.junit.Assert.*;

public class UssdMenuFactoryTest {

    @Test
    public void build() throws Exception {
        UssdMenu ussdMenu = UssdMenuFactory.build("1", "Help");
        System.out.println(ussdMenu);
        assertNotNull(ussdMenu);
    }
}
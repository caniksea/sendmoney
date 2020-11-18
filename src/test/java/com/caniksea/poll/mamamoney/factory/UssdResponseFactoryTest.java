package com.caniksea.poll.mamamoney.factory;

import com.caniksea.poll.mamamoney.model.UssdResponse;
import org.junit.Test;

import static org.junit.Assert.*;

public class UssdResponseFactoryTest {

    @Test
    public void build() {
        UssdResponse ussdResponse = UssdResponseFactory.build("test", "test message");
        assertNotNull(ussdResponse);
    }
}
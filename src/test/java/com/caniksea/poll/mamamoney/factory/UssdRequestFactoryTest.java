package com.caniksea.poll.mamamoney.factory;

import com.caniksea.poll.mamamoney.model.UssdRequest;
import org.junit.Test;

import static org.junit.Assert.*;

public class UssdRequestFactoryTest {

    @Test
    public void build() {
        UssdRequest ussdRequest = UssdRequestFactory.build("", "", "");
        System.out.println(ussdRequest);
        assertNotNull(ussdRequest);
    }
}
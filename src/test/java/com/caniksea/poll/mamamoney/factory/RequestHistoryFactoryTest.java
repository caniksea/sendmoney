package com.caniksea.poll.mamamoney.factory;

import com.caniksea.poll.mamamoney.model.RequestHistory;
import org.junit.Test;

import static org.junit.Assert.*;

public class RequestHistoryFactoryTest {

    @Test
    public void build() {
        RequestHistory requestHistory = RequestHistoryFactory.build("a", "4", "5", "yes", false, "");
        System.out.println(requestHistory);
        assertNotNull(requestHistory.getDateTime());
    }
}
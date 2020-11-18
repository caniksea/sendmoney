package com.caniksea.poll.mamamoney.factory;

import com.caniksea.poll.mamamoney.model.RequestHistory;

import java.time.LocalDateTime;

public class RequestHistoryFactory {

    public static RequestHistory build(String msisdn, String sessionId, String menuNumber, String choice) {
        return new RequestHistory.Builder()
                .msisdn(msisdn).sessionId(sessionId)
                .menuNumber(menuNumber).choice(choice)
                .dateTime(LocalDateTime.now()).build();
    }
}

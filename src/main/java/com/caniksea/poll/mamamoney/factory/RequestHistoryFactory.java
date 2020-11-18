package com.caniksea.poll.mamamoney.factory;

import com.caniksea.poll.mamamoney.model.RequestHistory;

import java.time.LocalDateTime;

public class RequestHistoryFactory {

    public static RequestHistory build(String msisdn, String sessionId, String menuNumber, String choice, boolean isSuccess, String comment) {
        return new RequestHistory.Builder()
                .msisdn(msisdn).sessionId(sessionId)
                .menuNumber(menuNumber).choice(choice)
                .isSuccess(isSuccess).comment(comment)
                .dateTime(LocalDateTime.now()).build();
    }
}

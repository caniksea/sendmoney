package com.caniksea.poll.mamamoney.factory;

import com.caniksea.poll.mamamoney.model.UssdResponse;

public class UssdResponseFactory {

    public static UssdResponse build(String sessionId, String message) {
        return new UssdResponse.Builder()
                .sessionId(sessionId).message(message).build();
    }
}

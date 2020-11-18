package com.caniksea.poll.mamamoney.factory;

import com.caniksea.poll.mamamoney.model.UssdRequest;

public class UssdRequestFactory {

    public static UssdRequest build(String msisdn, String sessionId, String userEntry) {
        return new UssdRequest.Builder()
                .msisdn(msisdn).sessionId(sessionId)
                .userEntry(userEntry).build();
    }
}

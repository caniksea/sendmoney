package com.caniksea.poll.mamamoney.service;

import com.caniksea.poll.mamamoney.model.RequestHistory;

import java.util.Optional;
import java.util.Set;

public interface RequestHistoryService extends IService<RequestHistory, String> {
    Set<RequestHistory> findByMSISDNnSessionId(String msisdn, String sessionId);
    Optional<RequestHistory> findByKeys(String msisdn, String sessionId, String menuNumber);
    Optional<RequestHistory> getLatest(String msisdn, String sessionId);
}

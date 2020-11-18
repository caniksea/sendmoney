package com.caniksea.poll.mamamoney.service.impl;

import com.caniksea.poll.mamamoney.model.RequestHistory;
import com.caniksea.poll.mamamoney.repository.RequestHistoryRepository;
import com.caniksea.poll.mamamoney.service.RequestHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RequestHistoryServiceImpl implements RequestHistoryService {

    @Autowired
    private RequestHistoryRepository repository;

    @Override
    public Set<RequestHistory> findByMSISDNnSessionId(String msisdn, String sessionId) {
        return repository.findByMSISDNnSessionID(msisdn, sessionId);
    }

    @Override
    public Optional<RequestHistory> findByKeys(String msisdn, String sessionId, String menuNumber) {
        return repository.findByKeys(msisdn, sessionId, menuNumber);
    }

    @Override
    public Optional<RequestHistory> getLatest(String msisdn, String sessionId) {
        Set<RequestHistory> requestHistory = findByMSISDNnSessionId(msisdn, sessionId);
        requestHistory = RequestHistory.sortByDateAsc(requestHistory);
        if (requestHistory.isEmpty())
            return Optional.empty();
        return Optional.of(requestHistory.iterator().next());
    }

    @Override
    public RequestHistory create(RequestHistory d) {
        return repository.save(d);
    }

    @Override
    public Optional<RequestHistory> read(String s) {
        throw new UnsupportedOperationException("Not supported!");
    }

    @Override
    public void deleteById(String s) {
        throw new UnsupportedOperationException("Not supported!");
    }

    @Override
    public void delete(RequestHistory d) {
        repository.delete(d);
    }

    @Override
    public Set<RequestHistory> findAll() {
        return new HashSet<>(repository.findAll());
    }
}

package com.caniksea.poll.mamamoney.service.impl;

import com.caniksea.poll.mamamoney.model.Currency;
import com.caniksea.poll.mamamoney.repository.CurrencyRepository;
import com.caniksea.poll.mamamoney.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyRepository repository;

    @Override
    public Currency create(Currency d) {
        return repository.save(d);
    }

    @Override
    public Optional<Currency> read(String s) {
        return repository.findById(s);
    }

    @Override
    public void deleteById(String s) {
        repository.deleteById(s);
    }

    @Override
    public void delete(Currency d) {
        repository.delete(d);
    }

    @Override
    public Set<Currency> findAll() {
        return new HashSet<>(repository.findAll());
    }
}

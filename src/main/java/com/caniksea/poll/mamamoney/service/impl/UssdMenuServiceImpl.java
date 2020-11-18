package com.caniksea.poll.mamamoney.service.impl;

import com.caniksea.poll.mamamoney.model.UssdMenu;
import com.caniksea.poll.mamamoney.repository.UssdMenuRepository;
import com.caniksea.poll.mamamoney.service.UssdMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UssdMenuServiceImpl implements UssdMenuService {

    @Autowired
    private UssdMenuRepository repository;

    @Override
    public UssdMenu create(UssdMenu d) {
        return repository.save(d);
    }

    @Override
    public Optional<UssdMenu> read(String s) {
        return repository.findById(s);
    }

    @Override
    public void deleteById(String s) {
        repository.deleteById(s);
    }

    @Override
    public void delete(UssdMenu d) {
        repository.delete(d);
    }

    @Override
    public Set<UssdMenu> findAll() {
        return new HashSet<>(repository.findAll());
    }
}

package com.caniksea.poll.mamamoney.service;

import java.util.Optional;
import java.util.Set;

public interface IService<T, ID> {
    T create(T d);
    Optional<T> read(ID id);
    void deleteById(ID id);
    void delete(T d);
    Set<T> findAll();
}

package com.caniksea.poll.mamamoney.repository;

import com.caniksea.poll.mamamoney.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, String> {
}

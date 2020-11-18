package com.caniksea.poll.mamamoney.repository;

import com.caniksea.poll.mamamoney.model.UssdMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UssdMenuRepository extends JpaRepository<UssdMenu, String> {
}

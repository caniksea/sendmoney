package com.caniksea.poll.mamamoney.repository;

import com.caniksea.poll.mamamoney.model.RequestHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RequestHistoryRepository extends JpaRepository<RequestHistory, String> {

    @Query("SELECT a FROM #{#entityName} a WHERE a.msisdn = :msisdn AND a.sessionId = :sessionId")
    Set<RequestHistory> findByMSISDNnSessionID(@Param("msisdn") String msisdn, @Param("sessionId") String sessionId);

    @Query("SELECT a FROM #{#entityName} a WHERE a.msisdn = :msisdn AND a.sessionId = :sessionId AND a.menuNumber = :menuNumber")
    Optional<RequestHistory> findByKeys(@Param("msisdn") String msisdn, @Param("sessionId") String sessionId, @Param("menuNumber") String menuNumber);
}

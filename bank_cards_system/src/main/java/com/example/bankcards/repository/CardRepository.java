package com.example.bankcards.repository;

import com.example.bankcards.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<Card, UUID>, JpaSpecificationExecutor<Card> {

    @Query(value = "SELECT cds.balance FROM cards cds WHERE cds.user_id = :id AND cds.number LIKE :number AND cds.archive_flag = false",
            nativeQuery = true)
    Double getBalanceForCardByUser(@Param("id") UUID id, @Param("number") String number);

    Card findByUserIdAndNumberAndIsArchived(UUID id, String number, Boolean isArchived);

    @Query(value = "UPDATE cards SET status = 'WAITING_FOR_BLOCKING' WHERE user_id = :id AND number LIKE :number AND archive_flag = false",
            nativeQuery = true)
    @Modifying
    void setStatusWaitingForBlocking(@Param("id") UUID id, @Param("number") String number);

    @Query(value = "UPDATE cards SET balance = :balance WHERE user_id = :id AND number LIKE :number AND archive_flag = :isArchived" ,
            nativeQuery = true)
    @Modifying
    void setBalanceOnCard(@Param("id") UUID id, @Param("number") String number, @Param("balance") Double balance, @Param("isArchived") Boolean isArchived);

    @Query(value = "UPDATE cards SET status = 'EXPIRED' WHERE expiration <= now() AND status <> 'EXPIRED' AND archive_flag = false" ,
            nativeQuery = true)
    @Modifying
    void setExpireStatusByScheduler();
}

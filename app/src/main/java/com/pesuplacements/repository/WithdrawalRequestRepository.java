package com.pesuplacements.repository;

import com.pesuplacements.entity.WithdrawalRequest;
import com.pesuplacements.entity.WithdrawalRequestId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawalRequestRepository extends JpaRepository<WithdrawalRequest, WithdrawalRequestId> {
    boolean existsById(WithdrawalRequestId id);
}

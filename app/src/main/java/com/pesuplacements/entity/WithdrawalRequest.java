package com.pesuplacements.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "withdrawal_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawalRequest {

    @EmbeddedId
    private WithdrawalRequestId id;

}

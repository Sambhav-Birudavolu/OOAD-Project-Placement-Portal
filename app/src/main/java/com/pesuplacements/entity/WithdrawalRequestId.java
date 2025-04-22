// WithdrawalRequestId.java
package com.pesuplacements.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawalRequestId implements Serializable {

    @Column(name = "srn", length = 13)
    private String srn;

    @Column(name = "job_id")
    private Long jobId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WithdrawalRequestId that)) return false;
        return Objects.equals(srn, that.srn) && Objects.equals(jobId, that.jobId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(srn, jobId);
    }
}

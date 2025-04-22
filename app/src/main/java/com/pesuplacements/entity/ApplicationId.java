package com.pesuplacements.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;
public class ApplicationId implements Serializable {
    private String srn;
    private Long jobId;

    public ApplicationId() {}

    public ApplicationId(String srn, Long jobId) {
        this.srn = srn;
        this.jobId = jobId;
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApplicationId)) return false;
        ApplicationId that = (ApplicationId) o;
        return Objects.equals(srn, that.srn) &&
               Objects.equals(jobId, that.jobId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(srn, jobId);
    }
}

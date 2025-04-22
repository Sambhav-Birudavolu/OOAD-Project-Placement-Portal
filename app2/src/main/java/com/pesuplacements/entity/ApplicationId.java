package com.pesuplacements.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ApplicationId implements Serializable {

    private String srn;
    private Long jobId;

    public ApplicationId() {}

    public ApplicationId(String srn, Long jobId) {
        this.srn = srn;
        this.jobId = jobId;
    }

    
    public String getSrn() {
        return srn;
    }

    public void setSrn(String srn) {
        this.srn = srn;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
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

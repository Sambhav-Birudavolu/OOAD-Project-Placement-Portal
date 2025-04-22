package com.pesuplacements.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "student_job_openings")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@IdClass(StudentJobOpeningsId.class)
public class StudentJobOpenings {

    @Id
    @Column(name = "srn", length = 13, nullable = false)
    private String srn;

    @Id
    @Column(name = "jobid", nullable = false)
    private Long jobId;

    @Column(name = "job_title", length = 100, nullable = false)
    private String jobTitle;

    @Column(name = "company_name", length = 100, nullable = false)
    private String companyName;

    @Column(name = "job_description", columnDefinition = "TEXT", nullable = false)
    private String jobDescription;

    
}

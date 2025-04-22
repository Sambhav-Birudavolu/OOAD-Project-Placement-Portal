package com.pesuplacements.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "job_openings")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JobOpenings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jobid")
    private Long jobID;

    @Column(name = "job_title", length = 100, nullable = false)
    @NotEmpty(message = "Job title cannot be empty")
    private String jobTitle;

    @Column(name = "company_name", length = 100, nullable = false)
    @NotEmpty(message = "Company name cannot be empty")
    private String companyName;

    @Column(name = "job_description", nullable = false)
    @NotEmpty(message = "Job description cannot be empty")
    private String jobDescription;

    @Column(name = "preferred_branch", nullable = false)
    private String preferredBranch;

    @Column(name = "min_gpa", nullable = false)
    @DecimalMin(value = "0.0", inclusive = true, message = "Minimum GPA must be at least 0.0")
    @DecimalMax(value = "10.0", inclusive = true, message = "Minimum GPA cannot exceed 10.0")
    private double minGpa;

    @Column(name = "number_of_rounds_left", nullable = false)
    @Min(value = 0, message = "Number of rounds left must be 0 or more")
    private int numberOfRoundsLeft;

    @Column(name = "is_open", nullable = false)
    private boolean isOpen = true;

    public boolean getIsOpen() {
        return isOpen;
    }
}

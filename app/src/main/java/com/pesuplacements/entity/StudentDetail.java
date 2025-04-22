package com.pesuplacements.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "studentdetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentDetail {

    @Id
    @Column(name = "srn", length = 13)
    @Size(min = 13, max = 13, message = "SRN must be exactly 13 characters")
    private String srn;

    @Column(name = "gpa", nullable = false)
    @DecimalMin(value = "0.0", message = "GPA cannot be less than 0.0")
    @DecimalMax(value = "10.0", message = "GPA cannot be greater than 10.0")
    private double gpa;

    @Column(name = "branch", nullable = false)
    private String branch;

    @Column(name = "penalty", nullable = false)
    private int penalty = 0; 

}

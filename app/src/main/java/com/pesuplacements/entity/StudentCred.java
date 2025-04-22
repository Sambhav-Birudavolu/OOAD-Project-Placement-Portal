package com.pesuplacements.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "studentcred")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentCred {

    @Id
    @Column(name = "SRN", length = 13)
    private String srn; 

    @NotEmpty
    @Column(name = "password", nullable = false)
    private String password;
}

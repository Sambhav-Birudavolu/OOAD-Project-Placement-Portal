package com.pesuplacements.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "admincred")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminCred {

    @Id
    @Column(name = "AID", length = 5)
    private String aid; 

    @NotEmpty
    @Column(name = "password", nullable = false)
    private String password;
}

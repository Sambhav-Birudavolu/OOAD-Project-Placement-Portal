package com.pesuplacements.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "companycred")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompanyCred {

    @Id
    @Column(name = "company_name", length = 100)  
    private String companyName;  

    @NotEmpty
    @Column(name = "password", nullable = false)
    private String password; 
}

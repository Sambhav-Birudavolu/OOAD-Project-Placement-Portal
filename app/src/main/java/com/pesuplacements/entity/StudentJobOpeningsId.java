package com.pesuplacements.entity;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentJobOpeningsId implements Serializable {
    private String srn;
    private Long jobId;
}

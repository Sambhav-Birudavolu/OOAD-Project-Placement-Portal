package com.pesuplacements.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AssignedJobDTO {
    private Long jobId;
    private String jobTitle;
    private String companyName;
    private String jobDescription;
    private boolean isOpen;
}

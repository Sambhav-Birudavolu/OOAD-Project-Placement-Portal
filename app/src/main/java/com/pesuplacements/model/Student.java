package com.pesuplacements.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Student {

    private static final Student instance = new Student(); 

    private String srn;


    private Student() {
    }

    
    public static Student getInstance() {
        return instance;
    }

    
    public String getSrn() {
        return srn;
    }

    public void setSrn(String srn) {
        this.srn = srn;
    }
}


package com.pesuplacements.factory;

import com.pesuplacements.entity.StudentCred;

public class StudentCredFactory {

 
    public static StudentCred create(String srn, String rawPassword) {
    
        return new StudentCred(srn, rawPassword);
    }
}

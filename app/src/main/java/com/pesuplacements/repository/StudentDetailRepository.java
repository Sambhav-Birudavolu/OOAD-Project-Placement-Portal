package com.pesuplacements.repository;

import com.pesuplacements.entity.StudentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentDetailRepository extends JpaRepository<StudentDetail, String> {
    List<StudentDetail> findByBranchAndGpaGreaterThanEqual(String branch, double gpa);
}

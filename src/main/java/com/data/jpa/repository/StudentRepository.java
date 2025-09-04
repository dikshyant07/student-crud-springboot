package com.data.jpa.repository;

import com.data.jpa.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
//    List<String> getStudentNames();

    boolean existsByEmail(String email);

}

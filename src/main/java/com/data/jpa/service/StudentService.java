package com.data.jpa.service;

import com.data.jpa.exceptions.*;
import com.data.jpa.model.Student;
import com.data.jpa.model.StudentDto;
import com.data.jpa.repository.StudentRepository;
import com.data.jpa.utils.MapperFunction;
import com.data.jpa.utils.Regx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    @Autowired
    private MapperFunction mapperFunction;
    @Autowired
    private Regx regx;


    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentDto createStudent(Student student) {
        if (!student.getEmail().matches(regx.getEmailRegx())) {
            throw new InvalidEmailException("Please enter the valid email!");
        }
        if (!student.getPassword().matches(regx.getPasswordRegx())) {
            throw new InvalidPasswordException("Password should be secure enough");
        }
        if (!studentRepository.existsByEmail(student.getEmail())) {
            throw new StudentAlreadyExistsException("The user with this email already exists ,so failed to signup");
        }
        Student createdStudent = studentRepository.save(student);
        return new StudentDto(createdStudent.getName(), createdStudent.getAge(), createdStudent.getEmail());

    }

    public StudentDto getOneStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentDoesNotExistsException("The student with the following gmail does not exists"));
        return new StudentDto(student.getName(), student.getAge(), student.getEmail());

    }

    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(mapperFunction).toList();
    }

    public StudentDto changePassword(Long id, String currentPassword, String newPassword) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentDoesNotExistsException("There is no any student with this email,so failed to change the password"));
        if (!currentPassword.equals(student.getPassword())) {
            throw new PasswordDoesNotMatchException("your password didnt match with database");
        }
        if (newPassword.equals(student.getPassword())) {
            throw new SamePasswordException("Please enter the password not same as previous one.");
        }
        if (!newPassword.matches(regx.getPasswordRegx())) {
            throw new InvalidPasswordException("Please enter the valid new password");
        }
        student.setPassword(newPassword);
        Student savedStudent = studentRepository.save(student);
        return new StudentDto(savedStudent.getName(), savedStudent.getAge(), savedStudent.getEmail());

    }

    public void deleteStudent(Long id) {
        studentRepository.findById(id).orElseThrow(() -> new StudentDoesNotExistsException("There is no any student with this id,so failed to delete"));
        studentRepository.deleteById(id);

    }

//    public List<String> getNames() {
//        List<Student> students = studentRepository.findAll();
//        return students.stream().map(Student::getName).toList();
//    }


}

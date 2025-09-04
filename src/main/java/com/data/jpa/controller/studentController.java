package com.data.jpa.controller;

import com.data.jpa.model.ApiResponse;
import com.data.jpa.model.RequestModel;
import com.data.jpa.model.Student;
import com.data.jpa.model.StudentDto;
import com.data.jpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/student")
public class studentController {
    private final StudentService studentService;

    @Autowired
    public studentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/signup")
    public ResponseEntity<StudentDto> saveStudent(@RequestBody Student student) {
        StudentDto dto = studentService.createStudent(student);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }

    @GetMapping("/one/{id}")
    public ResponseEntity<StudentDto> getOneStudent(@PathVariable Long id) {
        StudentDto dto = studentService.getOneStudent(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentDto>> getStudents() {
        List<StudentDto> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PutMapping("/change/{id}")
    public ResponseEntity<StudentDto> changePassword(@PathVariable Long id, @RequestBody RequestModel requestModel) {
        StudentDto changedDto = studentService.changePassword(id, requestModel.getCurrentPassword(), requestModel.getNewPassword());
        return new ResponseEntity<>(changedDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        ApiResponse response = new ApiResponse(HttpStatus.OK, "Successful", "Deleted the student fronm Database", new Date().toString());
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}

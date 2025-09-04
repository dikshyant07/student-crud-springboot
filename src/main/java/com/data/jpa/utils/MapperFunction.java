package com.data.jpa.utils;

import com.data.jpa.model.Student;
import com.data.jpa.model.StudentDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperFunction implements Function<Student, StudentDto> {
    @Override
    public StudentDto apply(Student student) {
        return new StudentDto(student.getName(), student.getAge(), student.getEmail());
    }
}

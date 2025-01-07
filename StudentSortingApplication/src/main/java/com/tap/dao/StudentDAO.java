package com.tap.dao;



import java.util.List;

import com.tap.model.Student;

public interface StudentDAO {
    List<Student> getAllStudents();
    List<Student> getFilteredStudents(String subject, int value1, int value2);
    List<Student>getFilteredStudentsAbove(String subject,int value1);
    List<Student>getFilteredStudentsBelow(String subject,int value1);
    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(int id);
}

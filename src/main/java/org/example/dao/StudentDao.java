package org.example.dao;

import org.example.enums.Gender;
import org.example.models.Student;

import java.util.List;
import java.util.Map;

public interface StudentDao {
    void createTable();
    void saveStudents(Student student);
    Student findByStudentId(long studentId);
    void updateStudent(long studentId,Student newStudent);
    void deleteByStudentId(long studentId);
    List<Student> findAll();
    List<Student>universalSort(String ascOrDesc);
    boolean checkByAge();
    void addColumnGender(String title);
    Map<Gender,List<Student>> groupByGender();
    void deletedAllStudents();

}

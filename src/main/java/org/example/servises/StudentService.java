package org.example.servises;

import org.example.enums.Gender;
import org.example.models.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    String creatTable();

    // save students
    String saveStudents(Student student);

    // find by studentsId
    String findByStudentId(Long studentId);

    //find all
    List<Student> findAllStudents();

    // update students
    String updateStudent(Long studentId, Student newStudent);

    //delete
    String deleteByStudentId(Long studentID);
    List<Student>universalSort(String ascOrDesc);

    boolean checkByAge();
    void addColumnGender(String title);
    Map<Gender,List<Student>> groupByGender();
    void deletedAllStudents();







}

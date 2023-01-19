package org.example.servises;

import org.example.dao.StudentDao;
import org.example.dao.StudentDaoImpl;
import org.example.enums.Gender;
import org.example.models.Student;

import java.util.List;
import java.util.Map;

public class StudentServiceImpl implements StudentService{
    StudentDao studentDao=new StudentDaoImpl();
    public String creatTable() {
        studentDao.createTable();
        return "Successful created";
    }

    public String saveStudents(Student student) {
        studentDao.saveStudents(student);
        return "Successful added";
    }

    public String findByStudentId(Long studentId) {
        studentDao.findByStudentId(studentId);
        return "Successful finded";
    }

    public List<Student> findAllStudents() {
        return null;
    }

    public String updateStudent(Long studentId, Student newStudent) {
        studentDao.updateStudent(studentId,newStudent);
        return "Success";
    }

    public String deleteByStudentId(Long studentID) {
        return null;
    }

    @Override
    public List<Student> universalSort(String ascOrDesc) {
        return studentDao.universalSort(ascOrDesc);
    }

    @Override
    public boolean checkByAge() {
       return studentDao.checkByAge();

    }

    @Override
    public void addColumnGender(String title) {
        studentDao.addColumnGender(title);
    }

    @Override
    public Map<Gender, List<Student>> groupByGender() {
        return studentDao.groupByGender();
    }

    @Override
    public void deletedAllStudents() {
     studentDao.deletedAllStudents();
    }

}

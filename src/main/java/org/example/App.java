package org.example;

import org.example.config.DataBaseConnection;
import org.example.models.Student;
import org.example.servises.StudentService;
import org.example.servises.StudentServiceImpl;

import java.sql.Connection;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args )
    {
//        DataBaseConnection.getConnection();
        StudentService studentService=new StudentServiceImpl();
//        System.out.println(studentService.creatTable());
//        System.out.println(studentService.saveStudents(new Student("lira", (byte) 38)));
//        studentService.updateStudent()

//        studentService.findAllStudents().forEach(System.out::println);
      //  String word = new Scanner(System.in).nextLine();
     //   studentService.universalSort(word).forEach(System.out::println);
//        System.out.println(studentService.checkByAge());
//        studentService.addColumnGender("gender");
      //  System.out.println(studentService.groupByGender());
        studentService.deletedAllStudents();
    }
}

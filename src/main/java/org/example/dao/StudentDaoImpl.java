package org.example.dao;

import org.example.config.DataBaseConnection;
import org.example.enums.Gender;
import org.example.models.Student;

import java.sql.*;
import java.util.*;

public class StudentDaoImpl implements StudentDao {
    private Connection connection;

    public StudentDaoImpl() {
        this.connection = DataBaseConnection.getConnection();
    }

    public void createTable() {
        String query = "create table students(" +
                "id serial primary key," +
                "name varchar (50) not null," +
                "age smallint not null" +
                ");";
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
            statement.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void saveStudents(Student student) {
        String query = """
                insert into (name,age)
                values(?,?)
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setByte(2, student.getAge());
            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Student findByStudentId(long studentId) {
        String sql = """
                select * from where id=?;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, studentId);
            ;
            ResultSet resultSet = preparedStatement.executeQuery();
            Student student = new Student();
            while (resultSet.next()) {
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString(2));
                student.setAge(resultSet.getByte(3));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void updateStudent(long studentId, Student newStudent) {
        String sql = """
                              update students 
                              set name=?,
                age=? where id=?      
                              """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newStudent.getName());
            preparedStatement.setByte(2, newStudent.getAge());
            preparedStatement.setLong(3, studentId);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                System.out.println("Successfull updated");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteByStudentId(long studentId) {
        String sql = "Delete from users where id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, studentId);
            preparedStatement.executeUpdate();
            System.out.println(studentId + "successfully deleted");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public List<Student> findAll() {
        Student student = new Student();

        List<Student> allStudent = new ArrayList<>();
        String query = """
                select * from students;
                """;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString(2));
                student.setAge(resultSet.getByte(3));
                allStudent.add(student);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public List<Student> universalSort(String ascOrDesc) {
        List<Student> allStudent = new ArrayList<>();
        String word = new Scanner(System.in).nextLine();
        String sql = null;
        switch (word) {
            case "name":
                if (Objects.equals(ascOrDesc, "asc")) {
                    sql = """
                            select * from students 
                            order by name 
                            """;
                } else if (Objects.equals(ascOrDesc, "desc")) {
                    sql = """
                            select * from students 
                            order by name desc
                            """;
                }
                break;
            case "age":
                if (Objects.equals(ascOrDesc, "asc")) {
                    sql = """
                            select * from students 
                            order by age 
                            """;
                } else if (Objects.equals(ascOrDesc, "desc")) {
                    sql = """
                            select * from students 
                            order by age  desc
                            """;
                }
        }

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString(2));
                student.setAge(resultSet.getByte(3));
                allStudent.add(student);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allStudent;
    }

    @Override
    public boolean checkByAge() {
        String s = """
                SELECT * FROM students WHERE age > 18;
                """;
        try (Statement statement =connection.createStatement()) {
            ResultSet resultSet=statement.executeQuery(s);
            boolean isTrue = false;
            isTrue = resultSet.next();
            resultSet.close();
            return isTrue;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void addColumnGender(String title) {
        String sql= """
                alter table students add column gender gender;
                """;
        try(Statement statement=connection.createStatement()){
            statement.executeQuery(sql);

            System.out.println("Successfully added column");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }






    @Override
    public Map<Gender, List<Student>> groupByGender() {
        Map<Gender,List<Student>> result=new HashMap<>();
        List<Student>girls=new ArrayList<>();
        List<Student>boys=new ArrayList<>();


        String sql= """
                select * from students where gender='FEMALE'; 
                """;
        try(Statement statement=connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString(2));
                student.setAge(resultSet.getByte(3));
                    girls.add(student);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        String sql1= """
                select * from students where gender='MALE'; 
                """;
        try(Statement statement1=connection.createStatement()){
            ResultSet resultSet1 = statement1.executeQuery(sql1);
            while (resultSet1.next()) {
                Student student = new Student();
                student.setId(resultSet1.getLong("id"));
                student.setName(resultSet1.getString(2));
                student.setAge(resultSet1.getByte(3));
                boys.add(student);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        result.put(Gender.FEMALE, girls);
        result.put(Gender.MALE, boys);

        return result;
        }

        @Override
    public void deletedAllStudents() {
        String sql = """
                truncate table students;
                """;
        try(Statement statement=connection.createStatement()){
            statement.executeQuery(sql);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}

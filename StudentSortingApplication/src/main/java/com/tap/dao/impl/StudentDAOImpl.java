package com.tap.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.StudentDAO;
import com.tap.model.Student;

public class StudentDAOImpl implements StudentDAO {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/studentSorting";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Dharam@123";

    private Connection getConnection() throws SQLException {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                students.add(mapResultSetToStudent(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    @Override
    public List<Student> getFilteredStudents(String subject, int value1, int value2) {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students WHERE " + subject + " BETWEEN ? AND ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, value1);
                preparedStatement.setInt(2, value2);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setEnglishMarks(resultSet.getInt("english"));
                student.setMathsMarks(resultSet.getInt("maths"));
                student.setScienceMarks(resultSet.getInt("science"));
                student.setSocialMarks(resultSet.getInt("social"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }
    
    @Override
    public List<Student> getFilteredStudentsAbove(String subject,int value1) {
        List<Student> students = new ArrayList<>();
        String column = "select * from students where "+subject+" > ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(column)) {

        	preparedStatement.setInt(1, value1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setEnglishMarks(resultSet.getInt("english"));
                student.setMathsMarks(resultSet.getInt("maths"));
                student.setScienceMarks(resultSet.getInt("science"));
                student.setSocialMarks(resultSet.getInt("social"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }
    
    @Override
    public List<Student> getFilteredStudentsBelow(String subject, int value1) {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students WHERE " + subject + " < ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, value1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setEnglishMarks(resultSet.getInt("english"));
                student.setMathsMarks(resultSet.getInt("maths"));
                student.setScienceMarks(resultSet.getInt("science"));
                student.setSocialMarks(resultSet.getInt("social"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }



    @Override
    public void addStudent(Student student) {
        String query = "INSERT INTO students (name, english, maths, science, social) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getEnglishMarks());
            preparedStatement.setInt(3, student.getMathsMarks());
            preparedStatement.setInt(4, student.getScienceMarks());
            preparedStatement.setInt(5, student.getSocialMarks());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStudent(Student student) {
        String query = "UPDATE students SET name = ?, english_marks = ?, maths_marks = ?, science_marks = ?, social_marks = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getEnglishMarks());
            preparedStatement.setInt(3, student.getMathsMarks());
            preparedStatement.setInt(4, student.getScienceMarks());
            preparedStatement.setInt(5, student.getSocialMarks());
            preparedStatement.setInt(6, student.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(int id) {
        String query = "DELETE FROM students WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Student mapResultSetToStudent(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getInt("id"));
        student.setName(resultSet.getString("name"));
        student.setEnglishMarks(resultSet.getInt("english"));
        student.setMathsMarks(resultSet.getInt("maths"));
        student.setScienceMarks(resultSet.getInt("science"));
        student.setSocialMarks(resultSet.getInt("social"));
        return student;
    }
}

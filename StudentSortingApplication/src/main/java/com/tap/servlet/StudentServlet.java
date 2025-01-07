package com.tap.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Comparator;

import com.tap.dao.StudentDAO;
import com.tap.dao.impl.StudentDAOImpl;
import com.tap.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/FilterStudentServlet")
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String subject = request.getParameter("subject");
        String filterType = request.getParameter("filterType");
        String value1 = request.getParameter("value1");
        
        StudentDAO sdao = new StudentDAOImpl();

        List<Student> slist = null;
	        if (filterType != null && !filterType.isEmpty()) {
	            if (filterType.equals("BETWEEN")) {
	                String value2 = request.getParameter("value2");
	                slist = sdao.getFilteredStudents(subject, Integer.parseInt(value1), Integer.parseInt(value2));
	            } else if (filterType.equals("Above")) {
	                slist = sdao.getFilteredStudentsAbove(subject, Integer.parseInt(value1));
	            } else {
	                slist = sdao.getFilteredStudentsBelow(subject, Integer.parseInt(value1));
	            }
	        } else {
	            slist = sdao.getAllStudents();
	        }
	     String sortCriteria = request.getParameter("sortCriteria");
        if (sortCriteria != null) {
            if (sortCriteria.equals("id")) {
                slist.sort(Comparator.comparingInt(Student::getId));
            } else if (sortCriteria.equals("name")) {
                slist.sort(Comparator.comparing(Student::getName));
            }
        }

        request.setAttribute("StudentList", slist);
        request.getRequestDispatcher("filterStudent.jsp").forward(request, response);
    }
}

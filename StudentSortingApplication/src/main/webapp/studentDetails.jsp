<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.tap.model.Student,com.tap.dao.StudentDAO,com.tap.dao.impl.StudentDAOImpl" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .filter-form {
            background-color: #f5f5f5;
            padding: 20px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        .btn {
            background-color: #4CAF50;
            color: white;
            text-decoration:none;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-right: 10px;
        }
        .btn:hover {
            background-color: #45a049;
        }
        select, input {
            padding: 8px;
            margin-right: 10px;
        }
        .value2-container {
            display: none;
        }
    </style>
</head>
<body>
    <h1>Student Details</h1>

    <!-- Filter Form -->
    <div class="filter-form">
        <form action="FilterStudentServlet" method="post">
        	<input type="hidden" name="sortCriteria" id="sortCriteria" value="">
            <label for="subject">Subject:</label>
            <select name="subject" id="subject" required>
                <option value="">Select Subject</option>
                <option value="english">English</option>
                <option value="maths">Maths</option>
                <option value="science">Science</option>
                <option value="social">Social</option>
            </select>

            <label for="filterType">Filter Type:</label>
            <select name="filterType" id="filterType" required>
                <option value="">Select Filter</option>
                <option value="Above">Greater Than</option>
                <option value="Below">Less Than</option>
                <option value="BETWEEN">BETWEEN</option>
            </select>

            <label for="value1">Value:</label>
            <input type="number" name="value1" id="value1" min="0" max="100" required>

            <div id="value2Container" class="value2-container">
                <label for="value2">Value 2:</label>
                <input type="number" name="value2" id="value2" min="0" max="100">
            </div>

            <input type="submit" value="Apply Filter" class="btn">
            <a href="studentDetails.jsp" class="btn">Show All</a>
            
            <button type="button" name="sortCriteria" class="btn" onclick="sort('id')">Sort By ID</button>
            <button type="button" name="sortCriteria" class="btn" onclick="sort('name')">Sort By Name</button>
        </form>
    </div>

    <!-- Students Table -->
    <%
    // Retrieve the list of students added to the request
    StudentDAOImpl studentDAO = new StudentDAOImpl();
    List<Student> list = studentDAO.getAllStudents();
    %>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>English</th>
            <th>Maths</th>
            <th>Science</th>
            <th>Social</th>
            <th>Total</th>
            <th>Average</th>
        </tr>
    </thead>
    <tbody>
        <%
            if (list != null && !list.isEmpty()) {
                for (Student student : list) {
        %>
        <tr>
            <td><%= student.getId() %></td>
            <td><%= student.getName() %></td>
            <td><%= student.getEnglishMarks() %></td>
            <td><%= student.getMathsMarks() %></td>
            <td><%= student.getScienceMarks() %></td>
            <td><%= student.getSocialMarks() %></td>
            <td><%= student.getEnglishMarks() + student.getMathsMarks() + student.getScienceMarks() + student.getSocialMarks() %></td>
            <td><%= String.format("%.2f", 
                   (student.getEnglishMarks() + student.getMathsMarks() + student.getScienceMarks() + student.getSocialMarks()) / 4.0) %>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="8" style="text-align:center;">No students found matching the criteria.</td>
        </tr>
        <%
            }
        %>
    </tbody>
</table>

    <script>
        // Show/hide value2 based on filterType
        document.getElementById('filterType').addEventListener('change', function() {
            const value2Container = document.getElementById('value2Container');
            const value2Input = document.getElementById('value2');
            
            if (this.value === 'BETWEEN') {
                value2Container.style.display = 'inline-block';
                value2Input.required = true;
            } else {
                value2Container.style.display = 'none';
                value2Input.required = false;
            }
        });

        // Set initial state of value2 field
        document.getElementById('filterType').dispatchEvent(new Event('change'));

        // Maintain filter values after form submission
        window.onload = function() {
            const urlParams = new URLSearchParams(window.location.search);
            document.getElementById('subject').value = urlParams.get('subject') || '';
            document.getElementById('filterType').value = urlParams.get('filterType') || '';
            document.getElementById('value1').value = urlParams.get('value1') || '';
            document.getElementById('value2').value = urlParams.get('value2') || '';
            
            // Trigger change event to properly show/hide value2
            document.getElementById('filterType').dispatchEvent(new Event('change'));
        };
    </script>
    <script>
        function sort(criteria) {
            document.getElementById('sortCriteria').value = criteria;
            document.forms[0].submit();
        }
    </script>
</body>
</html>
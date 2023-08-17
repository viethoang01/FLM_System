<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : curriculumsubject
    Created on : Apr 19, 2023, 12:40:45 PM
    Author     : DUCHIEUPC.COM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.3/js/bootstrap.min.js" integrity="sha512-1/RvZTcCDEUjY/CypiMz+iqqtaoQfAITmNSJY17Myp4Ms5mdxPS5UV7iOfdZoxcGhzFbOm6sntTKJppjvuhg4g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script><!-- comment -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.3/css/bootstrap.min.css" integrity="sha512-SbiR/eusphKoMVVXysTKG/7VseWii+Y3FdHrt0EpKgpToZeemhqHeZeLWLhJutz/2ut2Vw1uQEj2MbRF+TVBUA==" crossorigin="anonymous" referrerpolicy="no-referrer" />

        <title>JSP Page</title>
        <%@include file="../common/head.jspf" %>
    </head>
    <body>
        <%@include file="../header.jsp" %>
        <div style="margin: 20px">
            <h1>Curriculum!</h1>
            <table class="table table-bordered" style="width: 35%">
                <tbody>
                    <tr>
                        <td>Id</td>
                        <td>${c.curriculum_id}</td>
                    </tr>
                    <tr>
                        <td>Code</td>
                        <td>${c.code}</td>

                    </tr>
                    <tr>
                        <td>Name</td>
                        <td>${c.name}</td>
                    </tr>
                </tbody>
            </table>
            <h1>Add more subject</h1>
            <form method="post" action="AddCurriculumSubject">
                <input type="hidden" name="cid" value="${c.curriculum_id}">
                <select class="form-select" name="sid" style="display: inline-block; margin-right: 15px; width: 30%">
                    <c:forEach var="s"  items="${notMappedList}">
                        <option value="${s.subject_id}">${s.code}</option>
                    </c:forEach>  
                </select> 
                <button type="submit" class="btn btn-primary">Add</button>
            </form>
            <h1>Subject List of this curriculum</h1>
            <table class="table table-bordered" style="width: 80%;">
                <thead>
                    <tr>
                        <th>SubjectCode</th>
                        <th>Subject Name</th>
                        <th>Semester</th>
                        <th>NoCredit</th>
                        <th>Remove</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="s" items="${mappedList}">
                        <tr>
                            <td>${s.code}</td>
                            <td>${s.name}</td>
                            <td>${s.semester}</td>
                            <td>${s.no_credit}</td>
                            <td><a href="RemoveSubject?cid=${c.curriculum_id}&sid=${s.subject_id}" style="background-color: red;" class="btn btn-danger">Remove</a></td>
                        </tr>
                    </c:forEach>  

                </tbody>
            </table>
        </div>
    </body>
</html>

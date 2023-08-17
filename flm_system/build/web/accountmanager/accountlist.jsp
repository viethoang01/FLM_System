<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : accountlist
    Created on : Apr 18, 2023, 2:58:39 PM
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
        <div  style="margin: 15px;">

            <a href="AccountAdd" class="btn btn-primary">Add Account</a>
            <table class="table table-hover" id="tablepro">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Full Name</th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Mobile</th>
                        <th> Status</th>
                        <th>Change Status</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="u" items="${ulist}">
                        <tr>
                            <td>${u.user_id}</td>
                            <td>${u.full_name}</td>
                            <td>${u.user_name}</td>
                            <td>${u.email}</td>
                            <td>${u.mobile}</td>
                            <c:if test="${u.status==0}">
                                <td>Inactive</td>
                            </c:if>
                            <c:if test="${u.status==1}">
                                <td>Active</td>
                            </c:if>
                            <c:if test="${u.status==1}">
                                <td><a href="ChangeAccountStatus?uid=${u.user_id}&status=0" style="background-color: red" class="btn btn-danger">De-active</a></td>
                            </c:if>
                            <c:if test="${u.status==0}">
                                <td><a href="ChangeAccountStatus?uid=${u.user_id}&status=1" class="btn btn-success">Active</a></td>
                            </c:if>
                            <td><a href="AccountDetail?uid=${u.user_id}" class="btn btn-warning">Detail</a></td>
                            <td><a href="" onclick="cfDelete(${u.user_id})" class="btn btn-danger" style="background-color: red">Del</a></td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js"></script>
        <script>
                                $(document).ready(function () {
                                    $("#tablepro").DataTable({
                                        "bLengthChange": false,
                                        "bInfo": false
                                       });
                                });
        </script>
        <script>
            function cfDelete(uid) {
                let text = "Are your sure to delete user have id=" + uid;
                if (confirm(text) == true) {
                    window.location = "DeleteAccount?uid=" + uid;
                } else {
                }
            }

        </script>
    </body>
</html>

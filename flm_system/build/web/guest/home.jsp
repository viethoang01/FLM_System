<%-- 
    Document   : home
    Created on : Apr 11, 2023, 7:33:09 PM
    Author     : SHD
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <%@include file="../common/head.jspf" %>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="icon" href="images/favicon.ico" type="image/x-icon" />
        <title>Home Page</title>
        <link href='https://fonts.googleapis.com/css?family=Raleway:800,500%7CLato:400,300,400italic,700,700italic,300italic,900italic,900,100,100italic%7CRoboto:400,500,600' rel='stylesheet' type='text/css' />
        <style>
            .btn{
                width: 80%;
            }
            a{
                color: white;
            }

        </style>
    </head>
    <body>
        <%@include file="../header.jsp" %>
        <div class="container-fluid mt-5">
            <div class="row">
                <div class="col-4 text-center">
                    <div style="font-size: 25px;font-weight: 600">Guest Features</div>
                    <div class="mt-3"><button class="btn btn-warning"><a href="curriculum">View Curriculum</a></button></div>
                    <div class="mt-3"><button class="btn btn-warning">Subject Predeccessors</button></div>
                    <div class="mt-3"><button class="btn btn-warning">Subject Successors</button></div>
                </div>

                <c:if test="${sessionScope.user.getRole() eq 'Teacher' || sessionScope.user.getRole() eq 'Student' || sessionScope.user.getRole() eq 'CRDD Staff'}">
                    <div class="col-4 text-center">
                        <div style="font-size: 25px;font-weight: 600">Student Features</div>
                        <div class="mt-3"><button class="btn btn-warning"><a href="curriculum">View Curriculum</a></button></div>
                        <div class="mt-3"><button class="btn btn-warning">View Training Syllabus</button></div>
                        <div class="mt-3"><button class="btn btn-warning">Subject Predeccessors</button></div>
                        <div class="mt-3"><button class="btn btn-warning">Subject Successors</button></div>
                    </div>
                    <c:if test="${sessionScope.user.getRole() eq 'Teacher' || sessionScope.user.getRole() eq 'CRDD Staff'}">
                        <div class="col-4 text-center">
                            <div style="font-size: 25px;font-weight: 600">Teacher Features</div>
                            <div class="mt-3"><button class="btn btn-warning">View Curriculum</button></div>
                            <div class="mt-3"><button class="btn btn-warning">View Training Syllabus</button></div>
                            <div class="mt-3"><button class="btn btn-warning">Subject Predeccessors</button></div>
                            <div class="mt-3"><button class="btn btn-warning">Subject Successors</button></div>
                        </div>
                        <c:if test="${sessionScope.user.getRole() eq 'CRDD Staff'}">
                            <div class="col-4 text-center mt-5">
                                <div style="font-size: 25px;font-weight: 600">Reviewer Features</div>
                                <div class="mt-3"><button class="btn btn-warning">Review Syllabus</button></div>
                            </div>
                            <div class="col-4 text-center mt-5">
                                <div style="font-size: 25px;font-weight: 600">Designer Features</div>
                                <div class="mt-3"><button class="btn btn-warning">Design Syllabus</button></div>
                            </div>
                            <div class="col-4 text-center mt-5">
                                <div style="font-size: 25px;font-weight: 600">CRDD Staff Features</div>
                                <div class="mt-3"><button class="btn btn-warning">Manage Curriculum</button></div>
                                <div class="mt-3"><button class="btn btn-warning">Manage Subject</button></div>
                                <div class="mt-3"><button class="btn btn-warning">Manage Syllabus</button></div>
                                <div class="mt-3"><button class="btn btn-warning">Material Decisions</button></div>
                                <div class="mt-3"><button class="btn btn-warning"><a href="decision-list">Decision List</a></button></div>
                            </div>
                        </c:if>

                    </c:if>

                </c:if>
                <c:if test="${sessionScope.user.getRole() eq 'CRDD Head'}">
                    <div class="col-4 text-center">
                        <div style="font-size: 25px;font-weight: 600">CRDD Head Features</div>
                        <div class="mt-3"><button class="btn btn-warning">Assign,Approve,Disapprove Curriculum</button></div>
                        <div class="mt-3"><button class="btn btn-warning">Approve,Disapprove Syllabus</button></div>
                    </div>
                </c:if>
                <c:if test="${sessionScope.user.getRole() eq 'System Admin'}">
                    <div class="col-4 text-center">
                        <div style="font-size: 25px;font-weight: 600">System Admin Features</div>
                        <div class="mt-4"><a href="/flm_system/setting"><button class="btn btn-warning">System Settings</button></a></div>
                        <div class="mt-3"><button class="btn btn-warning"><a href="permission-setting">Role Permissions</a></button></div>
                        <div class="mt-3"><a href="AccountList" class="btn btn-warning">Account Management</button></a>
                        <div class="mt-3"><button class="btn btn-warning">Subject Successor</button></div>
                        <div class="mt-3"><button class="btn btn-warning"><a href="decision-list">Decision List</a></button></div>
                    </div>
                </c:if>
                <c:if test="${sessionScope.user.getRole() eq 'Syllabus Reviewer'}">
                    <div class="col-4 text-center">
                        <div style="font-size: 25px;font-weight: 600">Syllabus Reviewer Features</div>
                        <div class="mt-3"><button class="btn btn-warning">Review Syllabus</button></div>
                    </div>
                </c:if>
                <c:if test="${sessionScope.user.getRole() eq 'Syllabus Designer'}">
                    <div class="col-4 text-center">
                        <div style="font-size: 25px;font-weight: 600">Syllabus Designer Features</div>
                        <div class="mt-3"><button class="btn btn-warning">Design Syllabus</button></div>
                    </div>
                </c:if>

            </div>



        </div>
        <footer class="text-center text-white" style="position: fixed;right: 0;bottom: 0;left: 0;padding: 1rem;background: #003152">
            <!-- Grid container -->

            @FLM System

            <!-- Copyright -->
        </footer>
    </body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</html>

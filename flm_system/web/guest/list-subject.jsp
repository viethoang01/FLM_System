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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="icon" href="images/favicon.ico" type="image/x-icon" />
        <title>Home Page</title>

        <link rel="stylesheet" type="text/css" href="css/animate.css" />
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="css/bootstrap-select.css" />
        <link rel="stylesheet" type="text/css" href="css/font-awesome.css" />
        <link rel="stylesheet" type="text/css" href="css/fontello.css" />
        <link rel="stylesheet" type="text/css" href="css/jquery.fancybox.css" />
        <link rel="stylesheet" type="text/css" href="css/mob_menu.css" />
        <link rel="stylesheet" type="text/css" href="css/rev/pe-icon-7-stroke.css" />
        <link rel="stylesheet" type="text/css" href="css/rev/font-awesome.min.css" />
        <link rel="stylesheet" type="text/css" href="css/rev/settings.css" />
        <link rel="stylesheet" type="text/css" href="css/rev/layers.css" />
        <link rel="stylesheet" type="text/css" href="css/rev/navigation.css" />
        <link rel="stylesheet" type="text/css" href="css/rev/rev_responsive.css" />
        <link rel="stylesheet" type="text/css" href="css/reset.css" />
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <link rel="stylesheet" type="text/css" href="css/circularCountdown.css" />

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
        <header>
            <nav>
                <div class="container-fluid">
                    <div id="navbar" class="navbar navbar-default">
                        <div class="navbar-header col-md-1 col-sm-1 col-xs-1">
                            <a class="navbar-brand trigger" href="index2.jsp" title="LearnMate"><img alt="Logo" src="images/logo_bk.png"></a>
                        </div>
                        <div class="col-md-10 col-sm-10 col-xs-10 pull-xs-right">                           

                            <div class="main_menu_wrap">
                                <ul class="main_menu">
                                    <li><a class="trigger" href="teachers.jsp">Curriculum</a></li>
                                    <li><a class="trigger" href="contact.jsp">Subject Predecessors</a></li>
                                    <li><a class="trigger" href="contact.jsp">Subject Successors</a></li>
                                        <c:if test="${sessionScope.user.getRole() eq 'Student' || sessionScope.user.getRole() eq 'System Admin' 
                                                      || sessionScope.user.getRole() eq 'Teacher' || sessionScope.user.getRole() eq 'CRDD Head' ||
                                                      sessionScope.user.getRole() eq 'CRDD Staff'}"><li><a class="trigger" href="contact.jsp">Syllabus</a></li>
                                        </c:if>
                                        <c:if test="${sessionScope.user.getRole() eq 'System Admin'}">
                                        <li><a class="trigger" href="#">Dashboard <i class="fa fa-angle-down"></i></a>
                                            <ul class="submenu">
                                                <li><a href="blog_right_sidebar.jsp">System Settings</a></li>
                                                <li><a href="permission-setting">Role Permission</a></li>
                                                <li><a href="blog_dtl.jsp">User Management</a></li>
                                            </ul>
                                        </li>
                                    </c:if>
                                    <c:if test="${sessionScope.user.getRole() eq 'Syllabus Designer' || sessionScope.user.getRole() eq 'Syllabus Reviewer'}">
                                        <li><a class="trigger" href="#">Dashboard <i class="fa fa-angle-down"></i></a>
                                            <ul class="submenu">
                                                <li><a href="blog_right_sidebar.jsp">Syllabus Designing</a></li>
                                            </ul>
                                        </li>
                                    </c:if>
                                    <c:if test="${sessionScope.user.getRole() eq 'CRDD Head' || sessionScope.user.getRole() eq 'CRDD Staff'}">
                                        <li><a class="trigger" href="#">Dashboard <i class="fa fa-angle-down"></i></a>
                                            <ul class="submenu">
                                                <li><a href="blog_right_sidebar.jsp">Training Curriculums</a></li>
                                                <li><a href="blog_left_sidebar.jsp">Subject Management</a></li>
                                                <li><a href="blog_dtl.jsp">Material Decisions</a></li>
                                            </ul>
                                        </li>
                                    </c:if>
                                    <c:if test="${sessionScope.user == null}">
                                        <li><a  class="trigger" href="login">Login</a></li>
                                        <li><a class="trigger" href="register">Register</a></li>
                                        </c:if>
                                        <c:if test="${sessionScope.user != null}">
                                        <li><a class="trigger" href="#">${sessionScope.user.getUser_name()}<i class="fa fa-angle-down"></i></a>
                                            <ul class="submenu" >
                                                <li><a href="userprofile">User Profile</a></li>
                                                <li><a href="changepassword">Change Password</a></li>
                                                <li><a href="logout">Log out</a></li>
                                            </ul>
                                        </li>
                                    </c:if>



                                </ul>
                            </div>
                        </div>
                        <div class="navbar-header col-md-1 col-sm-1 col-xs-1">

                        </div>


                    </div>
                </div>
            </nav>
        </header>

        <div class="container-fluid mt-5">
            



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

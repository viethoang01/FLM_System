<%-- 
    Document   : header
    Created on : Apr 20, 2023, 12:15:10 AM
    Author     : SHD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                            <li><a href="curriculum" class="trigger" >Curriculum</a></li>
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

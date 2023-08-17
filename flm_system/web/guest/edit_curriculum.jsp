<%-- 
    Document   : edit_curriculum
    Created on : Apr 19, 2023, 6:23:27 AM
    Author     : SHD
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Curriculum</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <%@include file="../common/head.jspf" %>
        <style>
            body {
                margin: 0;
                color: #2e323c;
                background: #f5f6fa;
                position: relative;
                height: 100%;
            }
            .account-settings .user-profile {
                margin: 0 0 1rem 0;
                padding-bottom: 1rem;
                text-align: center;
            }
            .account-settings .user-profile .user-avatar {
                margin: 0 0 1rem 0;
            }
            .account-settings .user-profile .user-avatar img {
                width: 90px;
                height: 90px;
                -webkit-border-radius: 100px;
                -moz-border-radius: 100px;
                border-radius: 100px;
            }
            .account-settings .user-profile h5.user-name {
                margin: 0 0 0.5rem 0;
            }
            .account-settings .user-profile h6.user-email {
                margin: 0;
                font-size: 0.8rem;
                font-weight: 400;
                color: #9fa8b9;
            }
            .account-settings .about {
                margin: 2rem 0 0 0;
                text-align: center;
            }
            .account-settings .about h5 {
                margin: 0 0 15px 0;
                color: #007ae1;
            }
            .account-settings .about p {
                font-size: 0.825rem;
            }
            .form-control {
                border: 1px solid #cfd1d8;
                -webkit-border-radius: 2px;
                -moz-border-radius: 2px;
                border-radius: 2px;
                font-size: .825rem;
                background: #ffffff;
                color: #2e323c;
            }

            .card {
                background: #ffffff;
                -webkit-border-radius: 5px;
                -moz-border-radius: 5px;
                border-radius: 5px;
                border: 0;
                margin-bottom: 1rem;
            }
        </style>
    </head>
    <body>
        <%@include file="../header.jsp" %>
        <header>
            <nav>
                <div class="container-fluid">
                    <div id="navbar" class="navbar navbar-default">
                        <div class="navbar-header col-md-1 col-sm-1 col-xs-1">
                            <a class="trigger" href="">Curriculum Details</a><br>${curri.getCode()}
                        </div>
                        <div class="col-md-10 col-sm-10 col-xs-10 pull-xs-right">                           

                            <div class="main_menu_wrap">
                                <ul class="main_menu">
                                    <li><a class="trigger" href="#" style="font-weight: bolder">Overview <i class="fa fa-angle-down"></i></a>
                                        <ul class="submenu">
                                            <li><a href="edit-curriculum?id=${curri.getCurriculum_id()}">Edit</a></li>
                                        </ul>
                                    </li>
                                    <li><a class="trigger" href="contact.jsp">POs</a></li>
                                    <li><a class="trigger" href="plo-list?curriculum_id=${curri.getCurriculum_id()}">PLOs</a></li>
                                    <li><a class="trigger" href="po-plo?id=${curri.getCurriculum_id()}">PLOs-POs</a></li>
                                    <li><a class="trigger" href="contact.jsp">Subjects</a></li>
                                    <li><a class="trigger" href="subject-plo?id=${curri.getCurriculum_id()}">Subjects-PLOs</a></li>
                                    <li><a class="trigger" href="contact.jsp">Combos</a></li>
                                    <li><a class="trigger" href="contact.jsp">Electives</a></li>







                                </ul>
                            </div>
                        </div>
                        <div class="navbar-header col-md-1 col-sm-1 col-xs-1">

                        </div>


                    </div>
                </div>
            </nav>
        </header>
        <div class="pt-3" style="margin-left:  50px;">
            <a href="home" style="color: blue">Home</a>&ensp;->&ensp;
            <a href="curriculum" style="color: blue">View Curriculum</a>&ensp;->&ensp;
            <a href="curriculum-overview?id=${curri.getCurriculum_id()}" style="color: blue">Overview</a>&ensp;->&ensp;
            <a href="" style="color: blue">Edit Curriculum</a>
        </div>
        <div class="container mt-5">
            <div class="row gutters">

                <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
                    <div class="card h-100">
                        <div class="card-body">
                            <form action="edit-curriculum" method="post">
                                <input type="hidden" value="${curri.getCurriculum_id()}" class="form-control" name="id" required>
                                <div class="row gutters">
                                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                        <h6 class="mb-2 text-success">Curriculum Edit</h6>
                                    </div>
                                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12 mt-2">
                                        <div class="form-group mt-2">
                                            <label for="fullName">Curriculum Code</label>
                                            <input type="text" value="${curri.getCode()}" class="form-control" name="code" placeholder="Enter curriculum code" required>
                                        </div>
                                        <div class="form-group mt-2">
                                            <label for="phone">Curriculum Name</label>
                                            <input type="text" value="${curri.getName()}"  class="form-control" name="name" placeholder="Enter curriculum name" required>
                                        </div>
                                        <div class="form-group mt-2">
                                            <label for="phone">Total Credit</label>
                                            <input type="number" value="${curri.getTotal_credit()}" name="total"  class="form-control" id="phone" placeholder="Enter total credit" required min="1">
                                        </div>
                                        <div class="form-group mt-2">
                                            <label for="phone">Status</label>
                                            <select class="form-control" name="status">
                                                <option ${curri.isIs_active() == true ? 'selected':''} value="true">Active</option>
                                                <option ${curri.isIs_active() == false ? 'selected':''} value="false">Inactive</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12 mt-2">
                                        <div class="form-group mt-2">
                                            <label for="eMail">Description</label>
                                            <textarea type="email" class="form-control" name="description" placeholder="Enter description" style="height: 230px;" required>${curri.getDescription()}</textarea>
                                        </div>
                                    </div>


                                </div>

                                <div class="row gutters mt-5 text-center">
                                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                        <div>
                                            <button type="submit" id="submit"  class="btn btn-success">Update</button>
                                        </div>
                                    </div>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</html>

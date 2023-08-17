<%-- 
    Document   : plo_detail
    Created on : Apr 22, 2023, 4:56:22 AM
    Author     : SHD
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../common/head.jspf" %>
        <title>Po Detail</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <style>
            td{
                padding: 20px 20px 20px 20px !important;
            }
        </style>
    </head>
    <body>
        <%@include file="../header.jsp" %>
        <header class="mt-2">
            <nav>
                <div class="container-fluid">
                    <div id="navbar" class="navbar navbar-default">
                        <div class="navbar-header col-md-1 col-sm-1 col-xs-1">
                            <a class="trigger" href="">Po Detail</a><br>
                        </div>
                        <div class="col-md-10 col-sm-10 col-xs-10 pull-xs-right">                           

                            <div class="main_menu_wrap">
                                <ul class="main_menu">
                                    <li><a class="trigger" href="#" style="font-weight: bolder">Overview <i class="fa fa-angle-down"></i></a>
                                        <ul class="submenu">
                                            <li><a href="edit-curriculum?id=${curriculum_id}">Edit</a></li>
                                        </ul>
                                    </li>
                                    <li><a class="trigger" href="po-list?curriculum_id=${curriculum_id}">POs</a></li>
                                    <li><a class="trigger" href="plo-list?curriculum_id=${curriculum_id}">PLOs</a></li>
                                    <li><a class="trigger" href="po-plo?id=${curriculum_id}">PLOs-POs</a></li>
                                    <li><a class="trigger" href="CuriSubjectList?cid=${curriculum_id}">Subjects</a></li>
                                    <li><a class="trigger" href="subject_plo?id=${curriculum_id}">Subjects-PLOs</a></li>
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
            <a href="curriculum" style="color: blue">View Curriculum</a>
            &ensp;->&ensp;
            <a href="curriculum-overview?id=${curriculum_id}" style="color: blue">Curriculum Overview</a>
            &ensp;->&ensp;
            <a href="plo-list?curriculum_id=${curriculum_id}" style="color: blue">List PO</a>
            &ensp;->&ensp;
            <a href="" style="color: blue">PO Detail</a>
        </div>
     
        <div class="text-center">
            <h3>Po Detail</h3>
        </div>
        <table class="table table-hover mt-3 table-bordered" style="width: 95%;margin: 0 auto;">
            <tbody>

                <tr>
                    <td>Po ID</td>
                    <td>${po.getPo_id()}</td>    
                </tr>
                <tr>
                    <td>Name</td>
                    <td>${po.getName()}</td>    
                </tr>
                <tr>
                    <td>Description</td>
                    <td>${po.getDescription()}</td>    
                </tr>
            </tbody>
        </table>
        
    </body>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</html>
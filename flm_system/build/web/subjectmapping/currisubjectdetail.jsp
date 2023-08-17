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
        <style>
            body {
                font-family: Arial, Helvetica, sans-serif;
            }

            /* The Modal (background) */
            .modal {
                display: none; /* Hidden by default */
                position: fixed; /* Stay in place */
                z-index: 1; /* Sit on top */
                padding-top: 100px; /* Location of the box */
                left: 0;
                top: 0;
                width: 100%; /* Full width */
                height: 100%; /* Full height */
                overflow: auto; /* Enable scroll if needed */
                background-color: rgb(0,0,0); /* Fallback color */
                background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
            }

            /* Modal Content */
            .modal-content {
                position: relative;
                background-color: #fefefe;
                margin: auto;
                padding: 0;
                border: 1px solid #888;
                width: 40%;
                box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0,0,0,0.19);
                -webkit-animation-name: animatetop;
                -webkit-animation-duration: 0.4s;
                animation-name: animatetop;
                animation-duration: 0.4s
            }

            /* Add Animation */
            @-webkit-keyframes animatetop {
                from {
                    top:-300px;
                    opacity:0
                }
                to {
                    top:0;
                    opacity:1
                }
            }

            @keyframes animatetop {
                from {
                    top:-300px;
                    opacity:0
                }
                to {
                    top:0;
                    opacity:1
                }
            }

            /* The Close Button */
            .close {
                color: white;
                float: right;
                font-size: 28px;
                font-weight: bold;
            }

            .close:hover,
            .close:focus {
                color: #000;
                text-decoration: none;
                cursor: pointer;
            }

            .modal-header {
                padding: 2px 16px;
                background-color: lightgray;
                color: white;
            }

            .modal-body {
                padding: 2px 16px;
            }

            .modal-footer {
                padding: 2px 16px;
                background-color: lightgray;
                color: white;
            }
        </style>
        <%@include file="../common/head.jspf" %>
    </head>
    <body>
        <%@include file="../header.jsp" %>
        <div style="margin: 20px">
            <div class="row">
                <div class="col-md-12">
                    <h1>Curriculum</h1>
                    <table class="table table-bordered" style="width: 40%">
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
                </div>
                <div class="col-md-12">
                    <h1>Subject</h1>
                    <table class="table table-bordered" style="width: 40%">
                        <tbody>
                            <tr>
                                <td>Id</td>
                                <td>${s.subject_id}</td>
                            </tr>
                            <tr>
                                <td>Code</td>
                                <td>${s.code}</td>

                            </tr>
                            <tr>
                                <td>Name</td>
                                <td>${s.name}</td>
                            </tr>
                            <tr>
                                <td>Semester</td>
                                <td>${s.semester}</td>
                            </tr>
                            <tr>
                                <td>No_credit ${s.is_active}</td>
                                <td>${s.no_credit}</td>
                            </tr>
                            <tr>
                                <td>Status</td>
                                <c:if test="${s.is_active==true}">
                                    <td><button class="btn btn-success">Active</button></td>
                                </c:if> 
                                <c:if test="${s.is_active==false}">
                                    <td><button class="btn btn-danger">Inactive</button></td>
                                </c:if>  
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <c:if test="${sessionScope.user.getRole() eq 'System Admin'}">
                <a href="SubjectMapPlo?cid=${c.curriculum_id}&sid=${s.subject_id}" class="btn btn-success">Map Plo Subject</a>
            </c:if>

            <h1>Plo List of this Subject</h1>
            <table class="table table-bordered" style="width: 40%;">
                <thead>
                    <tr>
                        <th> Id</th>
                        <th> Name</th>
                        <th>Description</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="ss" items="${mappedPlo}">
                        <tr>
                            <td>${ss.plo_id}</td>
                            <td>${ss.name}</td>
                            <td>${ss.description}</td>
                        </tr>
                    </c:forEach>  
                </tbody>
            </table>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js" integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ" crossorigin="anonymous"></script>
    </body>
</html>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../common/head.jspf" %>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>Mapping PLOs to Subject</title>
        <style>
            .highlight-row {
                background-color: #f2f2f2;
            }

            .auto-style2 {
                margin-right: 0px;
            }

            .auto-style3 {
                width: 100%;
            }

            .auto-style4 {
                width: 156px;
            }

            .margin-right-10 {
                margin-right: 12px;
            }

            .margin-bot-10 {
                margin-bottom: 10px;
            }

            /*Table*/
            #gvPLO {
                border: 1px solid rgba(0, 0, 0, .05);
            }

            #gvPLO th {
                font-weight: bold;
                background-color: white;
                color: white;
            }

            .table-top {
                border-collapse: separate;
                border-spacing: 0 0.5em;
            }

            .plo,
            table {
                font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
                border-collapse: collapse;
                width: 100%;
            }

            .plo td,
            #customers th {
                border: 1px solid rgba(0, 0, 0, .05);
                padding: 8px;
            }

            .plo tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            .plo tr:hover {
                background-color: #ddd;
            }

            .plo th {
                border: 1px solid rgba(0, 0, 0, .05);
                padding-top: 12px;
                padding-bottom: 12px;
                padding-left: 6px;
                padding-right: 6px;
                text-align: center;
                background-color: white;
                color: black;
                font-weight: bold;
            }

            .plo td {
                text-align: center;
            }

            .textRedBold {
                color: red;
                font-weight: bold;
            }

            #btnAddPLO {
                height: 36px;
                font-weight: bold;
            }

            #btnDeletePLO {
                height: 36px;
                font-weight: bold;
            }

            table tr th {
                background-color: #F2994A;
                color: white;
                font-weight: bold;
            }
            .form-check-input:checked {
                background-color: #02B126;
                border-color: yellowgreen;
            }
            .form-check{
                display: flex;
                align-items: center;
                justify-content: center;
            }
            td{
                background: white !important;
            }
            .btn-edit{
                background:  #2ecc71 !important;
                margin-left: 94px !important;
            }
        </style>
    </head>
    <body>
        <%@include file="../header.jsp" %>
        <header>
            <nav>
                <div class="container-fluid">
                    <div id="navbar" class="navbar navbar-default">
                        
                        <div class="col-md-10 col-sm-10 col-xs-10 pull-xs-right">                           

                            <div class="main_menu_wrap">
                                <ul class="main_menu">
                                    <li><a class="trigger" href="#" style="font-weight: bolder">Overview <i class="fa fa-angle-down"></i></a>
                                        <ul class="submenu">
                                            <li><a href="edit-curriculum?id=${curriculum_id}">Edit</a></li>
                                        </ul>
                                    </li>
                                    <li><a class="trigger" href="contact.jsp">POs</a></li>
                                    <li><a class="trigger" href="plo-list?curriculum_id=${curriculum_id}">PLOs</a></li>
                                    <li><a class="trigger" href="plo-list?id=${curriculum_id}">PLOs-POs</a></li>
                                    <li><a class="trigger" href="contact.jsp">Subjects</a></li>
                                    <li><a class="trigger" href="subject_plo?id=${curriculum_id}">Subjects-PLOss</a></li>
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
            <a href="curriculum" style="color: blue">Subject-Plos</a>
        </div>                            
        <div class="mt-5 text-center">
            <h4 style="font-size: 25px;">Mapping PLOs to Subject</h4>
        </div>
       
        <form action="subject-plo" method="post" id="formPost">
            <input type="hidden" name="curri_id" value="${curriculum_id}" />
            <c:if test="${isAllow == true}"><button type="submit" class="btn-edit ms-3 mt-2">Update</button>
            </c:if>
            <table class="plo table-bordered mt-3">
                <thead>
                    <tr>
                        <th>Subjects</th>
                            <c:forEach items="${listPlo}" var="plo">
                            <th>${plo.name}</th>
                            </c:forEach>
                    </tr>
                </thead>
                <tbody>
                    <c:set var = "count" value = "0"/>
                     <c:set var = "string" value = "${listSubject.get(0).subject_group_name}"/>
                    <c:forEach items="${listSubject}" var="subject">
                        <c:if test="${string != subject.subject_group_name}">
                            <tr><td style="height: 60px;vertical-align: middle"  colspan="${listPlo.size()+1}">${subject.subject_group_name}</td></tr>
                            <c:set var = "string" value = "${subject.subject_group_name}"/>
                        </c:if>
                        <c:if test="${count == 0}">
                            <tr><td style="height: 60px;vertical-align: middle" colspan="${listPlo.size()+1}">${subject.subject_group_name}</td></tr>
                        </c:if>
                        
                        <tr>
                            <c:set var = "count" value = "1"/>
                            <td style="width:10%">${subject.name}</td>
                            <c:forEach items="${listPlo}" var="plo">
                                <c:set var = "isTrue" value = "${false}"/>
                                <c:forEach items="${listPloSubject}" var="ploSubject">
                                    <c:if test="${ploSubject.subject_id == subject.subject_id && ploSubject.plo_id == plo.plo_id}">
                                        <c:set var = "isTrue" value = "${true}"/>                              
                                    </c:if>
                                </c:forEach>
                                <c:if test="${isAllow  == true}">
                                    <td>
                                        <div class="form-check form-switch">
                                            <input type="checkbox" class="form-check-input" name="param ${subject.subject_id}_${plo.plo_id}" ${isTrue ? 'checked="true"':''}" value="true">
                                        </div>
                                    </td>
                                </c:if>
                                <c:if test="${isAllow == null}">
                                    <c:if test="${isTrue}">
                                        <td style="font-weight: bolder;color: green;font-size: 20px;">✓</td>
                                    </c:if>
                                    <c:if test="${!isTrue}">
                                        <td></td>
                                    </c:if> 
                                </c:if>
                            </c:forEach>
                        </tr>
                    </c:forEach>
            </table>
        </form>
        <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <script>
            $(document).ready(function () {
                $("#formPost").submit(function (event) {
                    event.preventDefault(); // ngăn tự động submit form
                    var form = $(this);
                    var url = form.attr('action');
                    var data = form.serialize(); // lấy toàn bộ dữ liệu trong form
                    $.ajax({
                        url: url,
                        type: "POST",
                        data: data,
                        success: function (response) {
                            if (response == 'success') {
                                swal("Update successfully!", {
                                    icon: "success",
                                    timer: 1000
                                });
                            } else {
                                swal("Update failed!", {
                                    icon: "success",
                                    timer: 1000
                                });
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            swal("Update failed!", {
                                icon: "success",
                                timer: 1000
                            });
                        }
                    });
                });
            });
        </script>
    </body>
</html>

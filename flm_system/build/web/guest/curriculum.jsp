<%-- 
    Document   : permission_setting
    Created on : Apr 15, 2023, 8:15:05 AM
    Author     : SHD
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <%@include file="../common/head.jspf" %>
        <title>View Curriculum</title>
        <style>
            .break {
                flex-basis: 100%;
                height: 0;
            }
            a{
                text-decoration: none;
                font-size: 14px;
            }
            .form-check-input:checked {
                background-color: #02B126;
                border-color: yellowgreen;
            }
            .pagination{
                padding: 30px 0;
            }

            .pagination ul{
                margin: 0;
                padding: 0;
                list-style-type: none;
            }

            .pagination a{
                display: inline-block;
                padding: 10px 18px;
                color: #222;
            }

            .p1 a{
                width: 40px;
                height: 40px;
                line-height: 40px;
                padding: 0;
                text-align: center;
            }

            .p1 a.is-active{
                background-color: #2ecc71;
                border-radius: 100%;
                color: #fff;
            }
            .container{
                width: 100%;
                max-width: 940px;
                margin: 0 auto;
                position: relative;
                text-align: center;
            }
            .dropdown-menu{
                min-width: 0px;
            }
            .btn-group{
                width: 100px;
            }
            .custom-select{
                width:150px;
                height: 100%;
                border: green 2px solid;
                margin-bottom: 0px !important;
            }
            .btn{
                background-color: #2ecc71 !important;
                color: white !important;
            }
            .tb-sort{
                float: right;
            }
        </style>
    </head>
    <body>
        <%@include file="../header.jsp" %>
        <div class="pt-3" style="margin-left:  50px;">
            <a href="home" style="color: blue">Home</a>&ensp;->&ensp;
            <a href="" style="color: blue">View Curriculum</a>
        </div>
        <div class="text-center">
            <h3>View Curriculum</h3>
        </div>
        <form action="curriculum" method="get" style="margin-left: 50px;">
            Enter Curriculum:&ensp;
            <div class="curriculum-search d-flex align-item-center">
                <select class="form-select custom-select" name="filter">
                    <option ${filter eq 'code' ? 'selected':''} value="code">Code</option>

                    <option ${filter eq 'name' ? 'selected':''} value="name">Name</option>
                </select>&ensp;
                <input type="text"  name="txt" value="${txt.trim()}" style="width: 20%;margin-bottom: 0" placeholder="Enter to search..." >
                <button type="submit" class="btn ms-3">Search</button>
                <c:if test="${isAllow == true}">
                     <button type="button"  class="btn ms-3"><a href="add-curriculum" style="color: white;">Add</a></button>
                </c:if>
               
            </div>

        </form>

        <c:if test="${list.size() > 0}">
            <table class="table table-hover mt-3" style="width: 95%;margin: 0 auto;">
                <thead>
                    <tr>
                        <th>ID<a href="curriculum?filter=${filter}&txt=${txt.trim()}&index=${i}&sort=curriculum_id" class="tb-sort">Sort</a></th>
                        <th>Curriculum Code<a href="curriculum?filter=${filter}&txt=${txt.trim()}&index=${i}&sort=code" class="tb-sort">Sort</a></th>
                        <th>Name<a href="curriculum?filter=${filter}&txt=${txt.trim()}&index=${i}&sort=name" class="tb-sort">Sort</a></th>
                        <th>Description<a href="curriculum?filter=${filter}&txt=${txt.trim()}&index=${i}&sort=description" class="tb-sort">Sort</a></th>
                        <th>Decision No MM/DD/YYYY<a href="curriculum?filter=${filter}&txt=${txt.trim()}&index=${i}&sort=decision_id" class="tb-sort">Sort</a></th>
                        <th>Total Credit<a href="curriculum?filter=${filter}&txt=${txt.trim()}&index=${i}&sort=total_credit" class="tb-sort">Sort</a></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="p" items="${list}">
                        <tr>
                            <td>${p.getCurriculum_id()}</td>
                            <td>${p.getCode()}</td>
                            <td><a href="curriculum-overview?id=${p.getCurriculum_id()}">${p.getName()}</a></td>
                            <td>${p.getDescription()}</td>

                            <td>${p.getDecision_no()}&ensp;  ${p.getDecision_dated()}</td>
                            <td>${p.getTotal_credit()}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="container">

                <div class="pagination p1 ">
                    <ul>

                        <c:forEach begin="1" end="${totalItem % 3 == 0 ? totalItem/3 : (totalItem/3)+1}" var="i">
                            <a class="${index == i ? 'is-active':''}" href="curriculum?filter=${filter}&txt=${txt.trim()}&index=${i}&sort=${sort}"><li>${i}</li></a>
                                </c:forEach>

                    </ul>
                </div>
            </div>
        </c:if>






    </body>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</html>


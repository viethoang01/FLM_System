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
        <%@include file="../common/head.jspf" %>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>Permission Page</title>
        <style>
            .break {
                flex-basis: 100%;
                height: 0;
            }
            a{
                color: black;
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
                max-width:150px;
                border: green 2px solid;
                margin-left: 50px;
            }
            .btn{
                background-color: #2ecc71 !important;
                color: white !important;
            }
        </style>
    </head>
    <body>
        <%@include file="../header.jsp" %>
        <div class="pt-3" style="margin-left:  50px;">
            <a href="home" style="color: blue">Home</a>&ensp;->&ensp;
            <a href="" style="color: blue">Role Permission</a>
        </div>
        <div class="text-center">
            <h3>Role Permission</h3>
        </div>
        <form action="permission-setting" method="get">
            <div class="d-flex">
                <select class="custom-select" name="filter">
                    <option value="all">All</option>
                    <c:forEach var="r" items="${roles}">
                        <option ${filter eq r ? 'selected':''} value="${r}">${r}</option>
                    </c:forEach>
                </select>
                <button type="submit" class="btn ms-3">Filter</button>
            </div>
        </form>
        <button class="btn" style="float: right;text-decoration: none;margin-right: 50px"> <a style="color: white" href="permission-setting?index=${index}&filter=${filter}">Reset</a></button>
        <form method="post" action="permission-setting">   
            <input type="hidden" name="index" value="${index}">
            <input type="hidden" name="filter" value="${filter}">
            <button  type="submit" class="btn" style="float:right;margin-right: 10px;">Update</button>

            <table class="table table-hover mt-3" style="width: 95%;margin: 0 auto;">
                <thead>
                    <tr>
                        <th>Role</th>
                        <th>Page</th>
                        <th>Access All</th>
                        <th>Can Read</th>
                        <th>Can Add</th>
                        <th>Can Edit</th>
                        <th>Can Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="p" items="${list}">
                        <tr>
                            <td>${p.getRoleName()}</td>
                            <td>${p.getPageName()}</td>
                            <td><div class="form-check form-switch"><input type="checkbox" ${p.getRole_id() == 5 && p.getPage_id() == 14 ? 'disabled':''} class="form-check-input"  name="${p.getRole_id()}_${p.getPage_id()}_isAccessAll" ${p.isAccess_all() == true ? 'checked':''} value="true" /></div></td>
                            <td><div class="form-check form-switch"><input type="checkbox" ${p.getRole_id() == 5 && p.getPage_id() == 14 ? 'disabled':''} class="form-check-input" name="${p.getRole_id()}_${p.getPage_id()}_isCanRead" ${p.isCan_read() == true ? 'checked':''} value="true" /></div></td>
                            <td><div class="form-check form-switch"><input type="checkbox" ${p.getRole_id() == 5 && p.getPage_id() == 14 ? 'disabled':''} class="form-check-input" name="${p.getRole_id()}_${p.getPage_id()}_isCanAdd" ${p.isCan_add() == true ? 'checked':''} value="true" /></div></td>
                            <td><div class="form-check form-switch"><input type="checkbox" ${p.getRole_id() == 5 && p.getPage_id() == 14 ? 'disabled':''} class="form-check-input" name="${p.getRole_id()}_${p.getPage_id()}_isCanEdit" ${p.isCan_edit() == true ? 'checked':''} value="true" /></div></td>
                            <td><div class="form-check form-switch"><input type="checkbox" ${p.getRole_id() == 5 && p.getPage_id() == 14 ? 'disabled':''} class="form-check-input" name="${p.getRole_id()}_${p.getPage_id()}_isCanDelete" ${p.isCan_delete() == true ? 'checked':''} value="true" /></div></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </form>
        <div class="container">

            <div class="pagination p1 ">
                <ul>
                    <c:forEach begin="1" end="${total % 10 == 0 ? total/10 : (total/10)+1}" var="i">
                        <a class="${index == i ? 'is-active':''}" href="permission-setting?index=${i}&filter=${filter}"><li>${i}</li></a>
                            </c:forEach>
                </ul>
            </div>
        </div>





    </body>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</html>

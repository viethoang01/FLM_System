<%-- 
    Document   : setting.jsp
    Created on : Apr 13, 2023, 10:48:24 AM
    Author     : nguye
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body> 

        <div class="title"> Setting List</div>
        <div class="search">
            <select class="custom-select">
                <option selected>User Role</option>
                <c:forEach items="${listP}" var="o">
                    <option value="1">${o.type}</option>
                </c:forEach>
                <!--    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>-->
            </select>
            <select style="margin-left: 10px" class="custom-select">
                <option selected>Active</option>
                <option value="2">Disactive</option>
            </select>
            <form style="display: flex;" action="setting" method="get">
                <input type="hidden" name="do" value="search">
                <div class="search-container">
                    <input  class="search-text" id="search" type="text" name="search" placeholder="Enter string name to search...">
                </div>
                <button type="text"  class="custom-button">Search/Filter</button>
            </form>
        </div>

        <div style="display: flex;">   
            <c:if test="${a==3}">
                <div> Found ${count} items </div>
            </c:if>
            <a style="margin-left: 32%" href="/flm_system/addsetting">New setting </a>
        </div>   
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Type</th>
                    <th>
                        <c:if test="${a==1}">
                            <a id="order" class="order" style="text-decoration: none; color: black" href="setting?do=sortsetting">Display Order</a>
                            <i class="fa-solid fa-chevron-down"></i>
                        </c:if>
                        <c:if test="${a==2}">
                            <a id="order" class="order" style="text-decoration: none; color: black" href="setting">Display Order</a>
                            <i class="fa-solid fa-chevron-up"></i>
                        </c:if>

                    </th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${listP}" var="o">
                    <tr>
                        <td><a href="/flm_system/settingdetail?cid=${o.settingid}"><c:out value="${o.settingid}" /> </a> </td>
                        <td><c:out value="${o.name}" /></td>
                        <td><c:out value="${o.type}" /></td>
                        <td><c:out value="${o.displayorder}" /></td>

                        <c:if test = "${o.isactive == 1}">
                            <td>Active</td>

                        </c:if> 
                        <c:if test = "${o.isactive == 0}">
                            <td>Inactive</td>
                        </c:if> 
                        <td>
                            <a href="/flm_system/addsetting?sid=${o.settingid}">Edit</a>        

                            <c:if test = "${o.isactive == 1}">
                                <a href="setting?do=disactive&&id=${o.settingid}&&index=${tag}">  Deactive</a>
                            </c:if> 
                            <c:if test = "${o.isactive == 0}">
                                <a href="setting?do=active&&id=${o.settingid}&&index=${tag}"> Active</a>
                            </c:if> 
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="pagination">
                <c:if test="${a==1}">
                <c:forEach begin="1" end="${endP}" var="i">
                    <a class="${tag == i?"active":""}" href="setting?index=${i}">${i}</a>
                </c:forEach>
            </c:if>
            <c:if test="${a==2}">
                    <c:forEach begin="1" end="${endP}" var="i">
                    <a class="${tag == i?"active":""}" href="setting?do=sortsetting&&index=${i}">${i}</a>
                </c:forEach>
                    </c:if>
            <c:if test="${a==3}">
                    <c:forEach begin="1" end="${endP}" var="i">
                    <a class="${tag == i?"active":""}" href="setting?do=search&&search=${author}&&index=${i}">${i}</a>
                </c:forEach>
                    </c:if>
              </div>
    </body>
</html>
<style>
    table, th, td {
        border: 1px solid black;
    }
    th{
        background-color: #C1B8D2;
        width: 140px;
    }
    .search {
        display: flex;
    }
    .custom-select {
        display: block;
        font-size: 16px;
        color: #333;
        border-radius: 8px 8px 8px 8px ;
        padding: 5px 5px;
        width: 100%;
        max-width: 110px;
        margin: 20px 0px;
    }

    .custom-button {
        margin-top: 20px;
        margin-left: 20px;
        height:  30px;
        background-color: #C1B8D2;
        padding: 5px 5px;
        border-radius: 5px;
        text-decoration: none;
        font-size: 14px;
        border: none;
        cursor: pointer;
    }
    .search-container{
        margin-top: 20px;
        margin-left: 20px;

    }
    .search-text{
        height: 30px;
        padding:  0px 25px;
    }
    .pagination {
        margin-top: 20px;
        width: 500px;
        margin-left: auto;
        margin-right: auto;
    }
    .pagination a {
        display: block;
        color: black;
        float: left;
        padding: 8px 16px;
        text-decoration: none;
    }
    .pagination a.active {
        background-color: #4CAF50;
        color: white;
    }

    .pagination a:hover:not(.active) {
        background-color: #ddd;
    }
    .order active{
        background-color: black;
    }
</style>
<script src="https://kit.fontawesome.com/3bfd3ce9e5.js" crossorigin="anonymous"></script>

<%-- 
    Document   : addsetting.jsp
    Created on : Apr 13, 2023, 1:34:21 PM
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
        <form method="post" action="addsetting?do=add">
            <div style="font-size :50px; font-weight: 800; margin-bottom: 20px"> New Setting </div>
        <div class="top">
            <div class="p name"> 
                <div class="type-name">Name*</div>
                <input class="name-value" type="text" id="fname" name="name" value="">
            </div>
            <div class="p title"> 
                <div class="title-name"> Type</div>
                <select name="type" class="custom-select">
                    <c:forEach items="${ast}" var="ast">
                         <option value="${ast.type}">${ast.type}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="bot">
            <div class="p value"> 
                <div class="value-name"> Display Order</div>
                <input type="text" id="fname" name="order" value="" >
            </div>
            <div class="p status "> 
                <div style="margin-bottom: 20px;" class="detail-name">Status</div>         
                <label><input type="radio" name="status" value="1">Active</label>
                <label style="margin-left: 40px;"><input type="radio" checked=""  name="status" value="0">Disactive</label>
            </div>
        </div>
        <div class="p detail "> 
            <div class="detail-name"> Description</div>
            <input style="height: 150px;" type="text" id="fname" name="desc" value="">
        </div>
        <button class="custom-button">Submit</button>
        <button class="custom-button">Reset</button>
        </form>
    </body>
</html>
<style>
    input[type=text], select {
        width: 100%;
        padding: 12px 20px;
        margin: 8px 0;
        display: inline-block;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }
    .top{
        display: flex;
    }
    .bot{
        display: flex;
    }
    .name{
        width: 40%;
    }
    .title{
        width: 20%;
        margin-left: 40px;
    }
    .value{
        width: 40%;
    }
    .status{
        width: 40%;
        margin-left: 40px;
    }

</style>
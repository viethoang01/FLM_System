<%-- 
    Document   : test
    Created on : Apr 22, 2023, 10:47:02 AM
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
        <title>JSP Page</title>
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
        </style>
    </head>
    <body>
        <table class="plo table-bordered">
            <thead>
                <tr>
                    <th colspan="11" style="font-size: large">Mapping POs to PLOs</th>
                </tr>
            </thead>
            <thead>
                <tr>
                    <th>PLO(s)</th>
                        <c:forEach items="${listPo}" var="po">
                        <th>${po.name}</th>
                        </c:forEach>
                </tr>
            </thead>
            <tbody>
                <tr></tr>
                <c:forEach items="${listPlo}" var="plo">
                    <tr>
                        <td style="width:5%">${plo.name}</td>
                        <c:forEach items="${listPo}" var="po">
                            <c:set var = "isTrue" value = "${false}"/>
                            <c:forEach items="${listPoPlo}" var="poplo">
                                <c:if test="${poplo.plo_id == plo.plo_id && poplo.po_id == po.po_id}">
                                    <c:set var = "isTrue" value = "${true}"/>                              
                                </c:if>
                            </c:forEach>
                            <c:if test="${sessionScope.user != null}">
                                <td>
                                    <div class="form-check form-switch">
                                        <input type="checkbox" class="form-check-input" name="8_9_isAccessAll" ${isTrue ? 'checked="true"':''}" value="${isTrue}">
                                    </div>
                                </td>
                            </c:if>
                            <c:if test="${sessionScope.user == null}">
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
    </body>
</html>

<%-- 
    Document   : ploSubject
    Created on : Apr 24, 2023, 5:04:56 AM
    Author     : SHD
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
        private int plo_id;
    private int curriculum_id;
    private int subject_id;
    
    private int plo_id;
    private String name;
    private String description;
    private int curriculum_id;
    
    request.setAttribute("listPlo", plos);
        request.setAttribute("listSubject", subjects);
        request.setAttribute("listPloSubject", ploSubjects);
        <form action="plo-subject" method="post" id="formPost">
            <input type="hidden" name="curri_id" value="${curriculum_id}" />
            <c:if test="${isAllow == true}">
                <button type="submit" class="btn-edit ms-3 mt-2">Update</button>
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
                <c:forEach items="${listSubject}" var="subject">
                    <tr>
                        <td style="width:5%">${plo.name}</td>
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
    </body>
</html>

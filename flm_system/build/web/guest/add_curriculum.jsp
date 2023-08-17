<%-- 
    Document   : add_curriculum
    Created on : Apr 19, 2023, 7:43:02 AM
    Author     : SHD
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Curriculum</title>
        <%@include file="../common/head.jspf" %>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <style>
            a{
                text-decoration: none;
            }
        </style>
    </head>
    <body>
        <%@include file="../header.jsp" %>
        <div class="pt-3" style="margin-left:  50px;">
            <a href="home" style="color: blue">Home</a>&ensp;->&ensp;
            <a href="curriculum" style="color: blue">View Curriculum</a>&ensp;->&ensp;
            <a href="" style="color: blue">Add Curriculum</a>
        </div>
        <div class="container mt-5">
            <div class="row gutters">

                <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
                    <div class="card h-100">
                        <div class="card-body">
                            <form action="add-curriculum" method="post">
                                <input type="hidden"  class="form-control" name="id" required>
                                <div class="row gutters">
                                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                        <h6 class="mb-2 text-success">New curriculum</h6>
                                    </div>
                                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12 mt-2">
                                        <div class="form-group mt-2 has-validation">
                                            <label for="fullName">Curriculum Code</label>
                                            <input type="text" value="${code}" id="valid-code" class="form-control ${msgCode != null ? 'is-invalid':''}" name="code" placeholder="Enter curriculum code" required>
                                            <c:if test="${msgCode != null}">
                                                <div class="invalid-feedback"  id="valid-codeFeedback" style="color:red;">
                                                    ${msgCode}
                                                </div>
                                            </c:if>
                                        </div>
                                        <div class="form-group mt-2">
                                            <label for="phone">Curriculum Name</label>
                                            <input type="text" id="valid-name" value="${name}"  class="form-control ${msgName != null ? 'is-invalid':''}" name="name" placeholder="Enter curriculum name" required>
                                            <c:if test="${msgName != null}">
                                                <div class="invalid-feedback" id="valid-nameFeedback" style="color:red;">
                                                    ${msgName}
                                                </div>
                                            </c:if>
                                        </div>
                                        <div class="form-group mt-2">
                                            <label for="phone">Total Credit</label>
                                            <input type="number" value="${total}"  name="total"  class="form-control" id="phone" placeholder="Enter total credit" required min="1">
                                        </div>
                                        <div class="form-group mt-2">
                                            <label for="phone">Status</label>
                                            <select class="form-control" name="status">
                                                <option ${status eq 'true' ? 'selected':''}  value="true">Active</option>
                                                <option ${status eq 'false' ? 'selected':''} value="false">Inactive</option>
                                            </select>
                                        </div>
                                        <div class="form-group mt-2">
                                            <label for="phone">Decision</label>
                                            <select class="form-control" name="decision">
                                                <c:forEach var="d" items="${list}">
                                                    <option ${d.getDecision_id() == decision ? 'selected':''} value="${d.getDecision_id()}">${d.getDecision_no()}</option>
                                                </c:forEach>

                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12 mt-2">
                                        <div class="form-group mt-2">
                                            <label for="eMail">Description</label>
                                            <textarea type="text" class="form-control" name="description" placeholder="Enter description" style="height: 320px;" required>${description}</textarea>
                                        </div>
                                    </div>


                                </div>

                                <div class="row gutters mt-5 text-center">
                                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                        <div>
                                            <button type="submit" id="submit"  class="btn btn-success">Add</button>
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

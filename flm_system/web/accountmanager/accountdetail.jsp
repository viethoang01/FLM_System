<%-- 
    Document   : accountdetail
    Created on : Apr 18, 2023, 2:58:47 PM
    Author     : DUCHIEUPC.COM
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="css/bootstrap-select.css" />
        <link rel="stylesheet" type="text/css" href="css/font-awesome.css" />
        <link rel="stylesheet" type="text/css" href="css/fontello.css" />
        <link rel="stylesheet" type="text/css" href="css/jquery.fancybox.css" />
        <link rel="stylesheet" type="text/css" href="css/mob_menu.css" />
        <link rel="stylesheet" type="text/css" href="css/rev/pe-icon-7-stroke.css" />
        <link rel="stylesheet" type="text/css" href="css/rev/font-awesome.min.css" />
        <link rel="stylesheet" type="text/css" href="css/rev/settings.css" />
        <link rel="stylesheet" type="text/css" href="css/rev/layers.css" />
        <link rel="stylesheet" type="text/css" href="css/rev/navigation.css" />
        <link rel="stylesheet" type="text/css" href="css/rev/rev_responsive.css" />
        <link rel="stylesheet" type="text/css" href="css/reset.css" />
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <link rel="stylesheet" type="text/css" href="css/circularCountdown.css" />
 <%@include file="../common/head.jspf" %>
        <link href='https://fonts.googleapis.com/css?family=Raleway:800,500%7CLato:400,300,400italic,700,700italic,300italic,900italic,900,100,100italic%7CRoboto:400,500,600' rel='stylesheet' type='text/css' />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <title>JSP Page</title>
        <style>
            .container{

                display: flex;
                justify-content: center;
                align-items: center;

            }
            .avatar {
                vertical-align: middle;
                width: 200px;
                height: 200px;
                border-radius: 50%;
            }
        </style>
    </head>
    <body>
        <%@include file="../header.jsp" %>
        <div style="width: 100%; display: flex; justify-content: center; align-items: center;">
            <div style="border: 1px solid gray; margin: 15px;padding: 18px;" class="row container container-content" >

                <div class=" col-md-9">

                    <h3 style="text-align: center;">Account detail</h3>
                    <!<!-- end message  -->
                    <div class="mb-3 mt-3">
                        <label for="fullname" class="form-label">Full name *</label>
                        <input type="text" class="form-control" id="fullname" placeholder="Enter fullname" name="fullname" value="${user.full_name}" readonly>
                    </div>
                    <div class="mb-3 mt-3" style="position: relative;margin-top: 30px">
                        <span class="row"> <label for="username" class="form-label col-md-5">User name</label></span>
                        <input type="text" class="form-control" id="username" placeholder="Enter username" name="username" value="${user.user_name}" readonly>
                    </div>
                    <div class="mb-3 mt-3">  
                        <label for="email" class="form-label">Email *</label>
                        <input type="email" class="form-control" id="email" placeholder="Enter email" name="email" value="${user.email}"  readonly>
                    </div>

                    <div class="mb-3 mt-3">
                        <label for="mobile" class="form-label">Mobile</label>
                        <input type="text" onchange="changeMobile()" class="form-control" id="mobile" placeholder="Enter mobile" name="mobile" value="${user.mobile}"  readonly>
                    </div>

                    <div class="row mb-3 mt-3" id="divVerifyCode" hidden="true">
                        <div class="col-md-6">
                            <div class="mb-3 mt-3">
                                <label for="verification" class="form-label">XX Verification Code</label>
                                <input type="number" onchange="codeverify()" class="form-control" id="verificationCode" placeholder="Enter verification" name="verification"  >
                            </div>
                        </div>

                        <div id="verifier">
                            <div id="recaptcha-container"></div>
                        </div>

                        <div class="col-md-6" style="padding-top: 30px">
                            <div class="mb-3 mt-3" >
                                <a onclick="phoneAuth()"  id="sendverification" class="btn btn-primary">Send Verification Code</a>
                            </div>
                        </div>
                    </div>

                    <div class="mb-3 mt-3 row" style="padding-left: 10px">
                        <label for="tile" class="form-label">Title</label>
                        <c:if test="${user.title eq 'Mr'}">
                            <div class="form-check col-md-4">
                                <input type="radio"  id="title1" name="title" value="Mr" checked readonly>Mr
                                <label class="form-check-label" for="radio1"></label>
                            </div>
                        </c:if>
                        <c:if test="${user.title ne 'Mr'}">
                            <div class="form-check col-md-4">
                                <input type="radio"  id="title1" name="title" value="Mr" readonly>Mr
                                <label class="form-check-label" for="radio1"></label>
                            </div>
                        </c:if>
                        <c:if test="${user.title eq 'Mrs'}">
                            <div class="form-check col-md-4">
                                <input type="radio"  id="title1" name="title" value="Mrs" checked readonly>Mrs
                                <label class="form-check-label" for="radio1"></label>
                            </div>
                        </c:if>
                        <c:if test="${user.title ne 'Mrs'}">
                            <div class="form-check col-md-4">
                                <input type="radio"  id="title1" name="title" value="Mrs" readonly>Mrs
                                <label class="form-check-label" for="radio1"></label>
                            </div>
                        </c:if>
                        <c:if test="${user.title eq 'Ms'}">
                            <div class="form-check col-md-4">
                                <input type="radio"  id="title1" name="title" value="Ms" checked readonly>Ms
                                <label class="form-check-label" for="radio1"></label>
                            </div>
                        </c:if>
                        <c:if test="${user.title ne 'Ms'}">
                            <div class="form-check col-md-4">
                                <input type="radio"  id="title1" name="title" value="Ms" readonly>Ms
                                <label class="form-check-label" for="radio1"></label>
                            </div>
                        </c:if>

                    </div>
                    <div class="mb-3 mt-3">
                        <label for="job" class="form-label">Job Title</label>
                        <input type="text" class="form-control" id="job" placeholder="Enter job" name="job" value="${user.job}" readonly>
                    </div>
                    <div class="mb-3 mt-3">
                        <label for="company" class="form-label">Company/Organzation</label>
                        <input type="text" class="form-control" id="company" placeholder="Enter company" name="company" value="${user.company}" readonly>
                    </div>
                    <a href="AccountEdit?uid=${user.user_id}" class="btn btn-primary">Update</a>

                </div>
            </div>
        </div>

    </body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <%@include file="../common/head.jspf" %>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    </head>
    <body>
        <%@include file="../header.jsp" %>
        <div class="container-fluid" style="max-width: 800px; margin-top: 50px; border: solid 1px">
            <div style="margin-top: 5px">
                <a href="home">
                    <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-house-door-fill" viewBox="0 0 16 16">
                    <path d="M6.5 14.5v-3.505c0-.245.25-.495.5-.495h2c.25 0 .5.25.5.5v3.5a.5.5 0 0 0 .5.5h4a.5.5 0 0 0 .5-.5v-7a.5.5 0 0 0-.146-.354L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.354 1.146a.5.5 0 0 0-.708 0l-6 6A.5.5 0 0 0 1.5 7.5v7a.5.5 0 0 0 .5.5h4a.5.5 0 0 0 .5-.5Z"/>
                    </svg>
                </a>
            </div>
            <h1 style="font-weight: bold">User Login</h1>
            <h3 style="color: red">${error}</h3>
            <form action="login" method="post">
                <!-- Email input -->
                <div class="form-outline mb-4" style="margin-top: 20px">
                    <label class="form-label" for="form2Example1">Email, Mobile or, User name*</label>
                    <input type="text" name="username" class="form-control" />
                </div>

                <!-- Password input -->
                <div class="form-outline mb-4">
                    <label class="form-label" for="form2Example2">Password*</label>
                    <input type="password" name="password" class="form-control" />
                </div>
                <label></label>
                <!-- Submit button -->
                <div class="row" style="display: flex">

                    <div class="col" style="margin-left: 10px">
                        <!-- Simple link -->
                        <button type="submit">Sign in</button>
                    </div>
                    <div class="col" style="margin-left: 10px">
                        <!-- Simple link -->
                        <button type="submit">Google login</button>
                    </div>
                </div>
                <label></label>
                <div class="form-outline mb-4" style="text-align: center">

                    <div class="col">
                        <!-- Simple link -->
                        <label>Not account yet?</label> &nbsp;
                        <a href="register">Register User</a>
                    </div>
                </div>
                <label></label>
                <!-- 2 column grid layout for inline styling -->
                <div class="form-outline mb-4" style="text-align: center; margin-bottom: 10px">

                    <div class="col">
                        <!-- Simple link -->
                        <a href="send">Forgot password?</a>
                    </div>
                </div>
            </form>
        </div>
    </body>

</html>


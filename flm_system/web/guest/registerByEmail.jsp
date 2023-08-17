
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Register</title>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    </head>
    <body>
        <div class="container-fluid" style="max-width: 800px; margin-top: 50px; border: solid 1px">
            <div>
                <a href="login">
                    <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-arrow-left" viewBox="0 0 16 16">
                    <path fill-rule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>
                    </svg>
                </a>
            </div>

            <h1 style="font-weight: bold">User Register</h1>
            <h3 style="color: red">${error}</h3>
            <form action="register" method="post">
                
                <div class="form-outline mb-4" style="margin-top: 20px">
                    <label class="form-label" for="form2Example1">Full Name*</label>
                    <input type="text" name="fullname" class="form-control" value="${requestScope.fullname}"/>
                </div>

                <!-- Email input -->
                <div class="form-outline mb-4" style="margin-top: 20px">
                    <label class="form-label" for="form2Example1">Email*</label>
                    <a href="#" style="margin-left: 75%">Register with mobile</a>
                    <input type="text" name="email" class="form-control" value="${requestScope.e}"/>
                </div>

                <!-- Code OTP input -->
                <div class="form-outline mb-4" style="margin-top: 10px">
                    <label class="form-label" for="form2Example2">Verification Code*</label>

                    <div class="row">
                        <div class="col-sm-6">
                            <input type="text" name="otp" class="form-control" />
                        </div>
                        <div class="col-sm-6">
                            <button type="submit">Send verification code</button>
                        </div>
                    </div>
                </div>
            </form>
            <!-- New password input -->
            <div class="form-outline mb-4" style="margin-top: 10px">

                <div class="row">
                    <div class="col-sm-6">
                        <label class="form-label" for="form2Example2">Password*</label>
                        <input type="password" name="password" class="form-control" />
                    </div>
                    <div class="col-sm-6">
                        <label class="form-label" for="form2Example2">Confirm Password*</label>
                        <input type="password" name="repassword" class="form-control" />
                    </div>
                </div>
            </div>
            <label></label>
            <!-- Submit button -->
            <div class="row" style="display: flex; margin-bottom: 10px">

                <div class="col" style="margin-left: 10px">
                    <!-- Simple link -->
                    <button type="submit">Register</button>
                </div>
            </div>


        </div>

    </body>
</html>

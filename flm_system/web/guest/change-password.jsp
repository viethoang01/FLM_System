<%-- 
    Document   : user-profile
    Created on : Apr 13, 2023, 7:02:25 PM
    Author     : dell
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User profile</title>
    </head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        .container{
            display: flex;
            justify-content: center;
            align-items: center;

        }
        .form-changepassword{
            width: 500px;
            height: auto;
            border: 1px solid gray;
            padding: 20px 40px;
            border-radius: 5px;
            background-color: ghostwhite;
        }
        a{
            text-decoration: none;
            color: white;
            margin-left: 30px
        }
    </style>

    <body>

        <div class="row container-content" >
            <div class="container mt-3">



                <form action="changepassword" method="post" onsubmit="return changePassword()" class="form-changepassword" id="idformchangepassword">
                    <!<!-- message  -->
                    <c:choose>
                        <c:when test="${status eq 'update_fail'}">
                            <div class="alert alert-warning" >
                                <strong>Fail!</strong> password is incorrect 
                            </div>
                        </c:when>
                        <c:when test="${status eq null}">

                        </c:when>
                        <c:otherwise >
                            <div class="alert alert-success" >
                                <strong>Success!</strong> Successful change
                            </div>
                        </c:otherwise>
                    </c:choose>

                    <!<!-- end message  -->
                    <h3>Change password</h3>
                    <p>Try to submit the form.</p>
                    <div class="mb-3 mt-3">
                        <input name="user_id" value="${user.user_id}" type="text" hidden="true"/>
                        <label for="oldpassword" class="form-label">Current password *</label>
                        <input type="password" class="form-control" id="oldpassword"  name="oldpassword" required>
                        <div class="valid-feedback">Valid.</div>
                        <div class="invalid-feedback">Please fill out this field.</div>
                    </div>

                    <div class="mb-3 mt-3">
                        <label for="newpassword" class="form-label">New password *</label>
                        <input type="password" class="form-control" id="newpassword"  name="newpassword" required>
                        <div class="valid-feedback">Valid.</div>
                        <div class="invalid-feedback">Please fill out this field.</div>
                    </div>

                    <div class="mb-3 mt-3">
                        <label for="repassword" class="form-label">Comfirm password *</label>
                        <input type="password" onchange="comfirmPassword()" class="form-control" id="repassword"  name="repassword" required>
                        <div class="alert alert-warning" id="comfirm" hidden="true">
                            <strong>Fail!</strong> comfirm password again.
                        </div>
                        <div class="valid-feedback">Comfirm fail</div>
                        <div class="valid-feedback">Valid.</div>
                        <div class="invalid-feedback">Please fill out this field.</div>
                    </div>


                    <button type="submit"  class="btn btn-primary">Update</button>
                    <a class="btn btn-warning" href="userprofile">Back to profile</a>

                </form>
            </div>


        </div>
    </div>
    <script>
        function comfirmPassword() {
            var newPass = document.getElementById("newpassword").value;
            var comfirmPass = document.getElementById("repassword").value;
            if (newPass !== comfirmPass) {
                document.getElementById("comfirm").hidden = false;
            } else {
                document.getElementById("comfirm").hidden = true;

            }

        }
        function changePassword() {
            document.getElementById("idformchangepassword").classList.add("was-validated");
            var newPass = document.getElementById("newpassword").value;
            var comfirmPass = document.getElementById("repassword").value;
            if (newPass !== comfirmPass) {
                document.getElementById("repassword").style.borderColor = "red";
                document.getElementById("comfirm").hidden = false;
                return false;
            } else {
                document.getElementById("comfirm").hidden = true;
                                return true;

            }
        }
    </script>
</body>
</html>

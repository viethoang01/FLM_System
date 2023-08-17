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
    <!-- Firebase App (the core Firebase SDK) is always required and must be listed first -->

    <link rel="stylesheet" type="text/css" href="css/animate.css" />
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

    <link href='https://fonts.googleapis.com/css?family=Raleway:800,500%7CLato:400,300,400italic,700,700italic,300italic,900italic,900,100,100italic%7CRoboto:400,500,600' rel='stylesheet' type='text/css' />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Firebase App (the core Firebase SDK) is always required and must be listed first -->


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
        .form-div{
            width: 100%;
            position: fixed;
            top: 50%;
            left: 60%;
            transform: translate(-50%, -50%);
            height: auto;
        }

    </style>
    <body>
        <%@include file="header.jsp" %>
        <!--action="userprofile" method="post"-->
        <div class="form-div">
            <form id="formupdate"  enctype="multipart/form-data">

                <div class="row container container-content" >
                    <div class="col-md-1"></div>
                    <div class=" col-md-6">



                        <h3>User profile</h3>

                        <div class="mb-3 mt-3">
                            <label for="fullname" class="form-label">Full name *</label>
                            <input  type="text" class="form-control" id="fullname" placeholder="Enter fullname" name="fullname" value="${user.full_name}" required>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill out this field.</div>
                        </div>

                        <div class="mb-3 mt-3">  
                            <label for="email" class="form-label">Email *</label>
                            <input type="email" class="form-control" id="email" placeholder="Enter email" name="email" value="${user.email}"  readonly>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill out this field.</div>
                        </div>

                        <div class="mb-3 mt-3">
                            <label for="mobile" class="form-label">Mobile</label>
                            <input type="text" onchange="changeMobile()" class="form-control" id="mobile" placeholder="Enter mobile" name="mobile" value="${user.mobile}"  required>
                            <div class="valid-feedback" id="verifystatus" hidden="true">Verification successful</div>

                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill out this field.</div>
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
                            <c:if test="${user.title eq null}">
                                <div class="form-check col-md-4">
                                    <input type="radio" class="form-check-input" id="title1" name="title" value="Mr" checked required>Mr
                                    <label class="form-check-label" for="radio1"></label>
                                </div>
                                <div class="form-check col-md-4">
                                    <input type="radio" class="form-check-input" id="title1" name="title" value="Mrs"  required>Mrs
                                    <label class="form-check-label" for="radio1"></label>
                                </div>
                                <div class="form-check col-md-4">
                                    <input type="radio" class="form-check-input" id="title1" name="title" value="Ms"  required>Ms
                                    <label class="form-check-label" for="radio1"></label>
                                </div>
                            </c:if>
                            <c:if test="${user.title ne null}">
                                <c:if test="${user.title eq 'Mr'}">
                                    <div class="form-check col-md-4">
                                        <input type="radio" class="form-check-input" id="title1" name="title" value="Mr" checked required>Mr
                                        <label class="form-check-label" for="radio1"></label>
                                    </div>
                                </c:if>
                                <c:if test="${user.title ne 'Mr'}">
                                    <div class="form-check col-md-4">
                                        <input type="radio" class="form-check-input" id="title1" name="title" value="Mr" required>Mr
                                        <label class="form-check-label" for="radio1"></label>
                                    </div>
                                </c:if>
                                <c:if test="${user.title eq 'Mrs'}">
                                    <div class="form-check col-md-4">
                                        <input type="radio" class="form-check-input" id="title1" name="title" value="Mrs" checked required>Mrs
                                        <label class="form-check-label" for="radio1"></label>
                                    </div>
                                </c:if>
                                <c:if test="${user.title ne 'Mrs'}">
                                    <div class="form-check col-md-4">
                                        <input type="radio" class="form-check-input" id="title1" name="title" value="Mrs" required>Mrs
                                        <label class="form-check-label" for="radio1"></label>
                                    </div>
                                </c:if>
                                <c:if test="${user.title eq 'Ms'}">
                                    <div class="form-check col-md-4">
                                        <input type="radio" class="form-check-input" id="title1" name="title" value="Ms" checked required>Ms
                                        <label class="form-check-label" for="radio1"></label>
                                    </div>
                                </c:if>
                                <c:if test="${user.title ne 'Ms'}">
                                    <div class="form-check col-md-4">
                                        <input type="radio" class="form-check-input" id="title1" name="title" value="Ms" required>Ms
                                        <label class="form-check-label" for="radio1"></label>
                                    </div>
                                </c:if>
                            </c:if>


                        </div>

                        <div class="mb-3 mt-3">
                            <label for="company" class="form-label">Company/Organzation</label>
                            <input type="text" class="form-control" id="company" placeholder="Enter company" name="company" value="${user.company}" required>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill out this field.</div>
                        </div>
                        <button type="submit"  id="btnsubmit" disabled="true" class="btn btn-primary">Update</button>

                    </div>
                    <div class="col-md-3">

                        <div style="text-align: center" id="showavatar">
                            <img src="" alt="avatar"  width="100%" class="avatar" id="preavatar" hidden/>

                            <c:choose>
                                <c:when test="${user.avatar ne null}">
                                    <img src="${user.avatar}" alt="avatar" id="avatarphoto" width="100%" class="avatar"/>
                                </c:when>
                                <c:otherwise>
                                    <img src="images/avatar_default.png" alt="avatar default" id="avatarphoto" width="100%" class="avatar"/>

                                </c:otherwise>
                            </c:choose>

                        </div>
                        <div class="mb-3 mt-3" style="position: relative" >
                            <input name="checkchangeavatar" type="text"  id="checkchangeavatar"  hidden/>

                            <!--<button style="width: 100%;" class="btn btn-info" onclick="document.getElementById('avatar').click()">Change avatar image</button>-->
                            <input onchange="changeAvatar()" type="file" name="avatar" id="avatar"  style=""/>
                        </div>
                        <div class="mb-3 mt-3" style="position: relative;margin-top: 30px">
                            <span class="row"> <label for="username" class="form-label col-md-5">User name</label><span class="col-md-5" style="display: inline;right: 0;position: absolute ;"><a href="" data-bs-toggle="modal" data-bs-target="#myModal">Change ps</a></span></span>
                            <input type="text" class="form-control" id="username" placeholder="Enter username" name="username" value="${user.user_name}" required>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill out this field.</div>
                        </div>

                        <div class="mb-3 mt-3">
                            <label for="job" class="form-label">Job Title</label>
                            <input type="text" class="form-control" id="job" placeholder="Enter job" name="job" value="${user.job}" required>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill out this field.</div>
                        </div>

                        <input name="user_id" value="${user.user_id}" hidden/>

                    </div>
                </div>
            </form>
        </div>


        <!-- modal -->
        <div class="modal " id="myModal" >
            <div class="modal-dialog modal-dialog-centered modal-lg">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title"></h4>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        <div class="container mt-3">
                            <form  onsubmit="return changePassword()" class="form-changepassword" id="idformchangepassword">
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
                                <div class="mb-3 mt-3">
                                    <input name="userid" value="${user.user_id}" type="text" hidden />
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


                            </form>


                            <div id="showImport"></div>

                        </div>
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="button" style="width: 100px" btn-danger" data-bs-dismiss="modal">Close</button>
                    </div>

                </div>
            </div>
        </div>

        <!-- end modal -->


        <footer class="text-center text-white" style="position: fixed;right: 0;bottom: 0;left: 0;padding: 1rem;background: #003152">
            <!-- Grid container -->

            @FLM System

            <!-- Copyright -->
        </footer>


        <script src="https://www.gstatic.com/firebasejs/8.3.3/firebase-app.js"></script>

        <script src="https://www.gstatic.com/firebasejs/8.3.3/firebase.js"></script>
        <script src="https://www.gstatic.com/firebasejs/9.12.1/firebase-app-compat.js"></script>
        <script src="https://www.gstatic.com/firebasejs/9.12.1/firebase-auth-compat.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
                                        const input = document.getElementById('avatar');
                                        const preview = document.getElementById('showavatar');
                                        input.addEventListener('change', () => {
                                            const file = input.files[0];
                                            const imageUrl = URL.createObjectURL(file);
                                            const previewView = document.getElementById('preimgview');
                                            if (previewView !== null) {
                                                previewView.remove();
                                            }
                                            document.getElementById('avatarphoto').hidden = true;
                                            const img = document.createElement('img');
                                            img.src = imageUrl;
                                            img.setAttribute('id', 'preimgview');
                                            img.classList.add("avatar");
                                            preview.appendChild(img);
                                        });
        </script>
        <script>

            // opt
            // 
            // For Firebase JS SDK v7.20.0 and later, measurementId is optional 
            const firebaseConfig = {
                apiKey: "AIzaSyAjrs0DN7qQJI63LqaBWBgYGJTInezNBp0",
                authDomain: "swp391bl5.firebaseapp.com",
                projectId: "swp391bl5",
                storageBucket: "swp391bl5.appspot.com",
                messagingSenderId: "750074195914",
                appId: "1:750074195914:web:6125dae4445916ce9e6933",
                measurementId: "G-Z84CVVBCJS"
            };
            firebase.initializeApp(firebaseConfig);
            render();
            function render() {
                window.recaptchaVerifier = new firebase.auth.RecaptchaVerifier('recaptcha-container');
                recaptchaVerifier.render();
            }
            function phoneAuth() {
                var number = document.getElementById("mobile").value;
                number = convertPhone(number);
                firebase.auth().signInWithPhoneNumber(number, window.recaptchaVerifier).then(function (confirmationResult) {
                    window.confirmationResult = confirmationResult;
                    coderesult = confirmationResult;
                    swal("OTP Sent!", {
                        icon: "info",
                        timer: 2000
                    });
                    console.log('OTP Sent');
                    console.log(coderesult);
                }).catch(function (error) {
                    // error in sending OTP
                    swal(error.message, {
                        icon: "warning",
                        timer: 2000
                    });
                });
            }


            function convertPhone(phone) {
                if (!phone.startsWith("+84")) {
                    phone = phone.replace('0', "+84");
                    console.log(phone + "new ");
                }
                return phone;
            }
            // function for OTP verify
            function codeverify() {
                console.log(coderesult);
                var code = document.getElementById('verificationCode').value;
                console.log(code);
                coderesult.confirm(code).then(function () {
                    console.log('OTP Verified');
                    swal('OTP Verified', {
                        icon: "success",
                        timer: 2000
                    });
                    document.getElementById('divVerifyCode').hidden = true;
                    document.getElementById('verifystatus').hidden = false;
                }).catch(function () {
                    console.log('OTP Not correct');

                    document.getElementById('verifystatus').hidden = true;
                    swal('OTP Not correct', {
                        icon: "warning",
                        timer: 2000
                    });
                });
            }

            function changeMobile() {
                var divVerifyCode = document.getElementById('divVerifyCode');
                divVerifyCode.hidden = false;
            }

            //end opt

            function changeAvatar() {
                document.getElementById("checkchangeavatar").value = "true";
                console.log("avatar change? :");
                var fileInput = document.getElementById("avatar");
                var fileName = fileInput.files[0].name;
                var relativePath = fileInput.files[0].webkitRelativePath; // Đường dẫn tương đối của tệp được chọn

                console.log("Tên tệp: " + fileName);
                console.log("Đường dẫn tương đối: " + relativePath);
            }
            function checkValid() {
                var regex = /^[a-zA-Z]+$/;
                var fullname = document.getElementById('fullname');
                var tilte = document.getElementById('tilte');
                var job = document.getElementById('job');
                var company = document.getElementById('company');
                var username = document.getElementById('username');
                var checkedTitle = false;
//                for (var i = 0; i < tilte.length; i++) {
//                    if (tilte[i].checked) {
//                        checkedTitle = true;
//                        break;
//                    }
//                }
//                if (!checkedTitle) {
//                    tilte.style.border = "2px solid red";
//                } else {
//                    // xử lý dữ liệu
//                }
                console.log(fullname.value === '');
                if (fullname.value === '') {
                    fullname.style.border = "2px solid red";
                } else {
                    if (regex.test(fullname.value)) {
                        fullname.style.border = "2px solid red";
                    } else {
                        fullname.style.border = "2px solid green";
                    }
                }
                if (job.value === "") {
                    job.style.border = "2px solid red";
                } else {
                    job.style.border = "2px solid green";
                }
                if (company.value === "") {
                    company.style.border = "2px solid red";
                } else {
                    company.style.border = "2px solid green";
                }
                if (username.value === "") {
                    username.style.border = "2px solid red";
                } else {
                    username.style.border = "2px solid green";
                }

                if (username.value === "" || company.value === "" || job.value === "" || fullname.value === "") {
                }

            }
        </script>

        <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <script>
            // CHANGE PROFILE


            var myForm = document.getElementById("formupdate");
            $(document).ready(function () {
                myForm.onchange = function () {
                    document.getElementById('btnsubmit').disabled = false;
                };
            });

            myForm.onsubmit = function (event) {
                event.preventDefault(); // ngăn chặn form được submit mặc định

                // Kiểm tra validate các input


                console.log("dd");
                event.preventDefault();
                // Tạo đối tượng FormData và thêm dữ liệu vào đối tượng này
                var formData = new FormData(this);
                $.ajax({

                    url: 'userprofile',
                    type: 'POST',
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function (data) {
                        if (data == 'dup_username') {
                            swal("Username duplicate!", {
                                icon: "error",
                                timer: 2000
                            });
                        }
                        if (data == 'dup_phone') {
                            swal("Phone duplicate!", {
                                icon: "error",
                                timer: 2000
                            });
                        }

                        if (data == 'success') {
                            swal("successfully!", {
                                icon: "success",
                                timer: 2000
                            });
                        } else {
                            swal(data, {
                                icon: "error",
                                timer: 2000
                            });
                        }
                    }
                });
            };
            //END CHANGE PROFILE


            // CHANGE PASSWORD
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


            var myForm2 = document.getElementById('idformchangepassword');
            myForm2.onsubmit = function (event) {
                event.preventDefault(); // ngăn chặn form được submit mặc định

                // Kiểm tra validate các input


                event.preventDefault();
                // Tạo đối tượng FormData và thêm dữ liệu vào đối tượng này
                var formData2 = new FormData(this);
                var repassword = document.getElementById('repassword').value;
                var newpassword = document.getElementById('newpassword').value;
                var oldpassword = document.getElementById('oldpassword').value;

                $.ajax({

                    url: 'changepassword?' + "repassword=" + repassword + "&newpassword=" + newpassword + "&oldpassword=" + oldpassword,
                    type: 'POST',
                    data: formData2,
                    processData: false,
                    contentType: false,
                    success: function (data) {

                        if (data === 'success') {
                            swal("success change", {
                                icon: "success",
                                timer: 2000
                            }).then((result) => {
                                if (result) {
                                    const modal = document.querySelector('#myModal');
                                    modal.style.display = 'none';
                                }
                            });



                        } else if (data === 'dup') {
                            swal("password not change ", {
                                icon: "warning",
                                timer: 3000
                            });
                        } else if (data === 'fail-pass') {
                            swal("password incorrect ", {
                                icon: "warning",
                                timer: 3000
                            });
                        } else {
                            swal('fail change', {
                                icon: "error",
                                timer: 3000
                            });
                        }
                    }
                });
            };
            // END CHANGE PASSWORD

        </script>
    </body>
</html>

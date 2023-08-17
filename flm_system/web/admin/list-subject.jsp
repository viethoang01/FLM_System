<%-- 
    Document   : home
    Created on : Apr 11, 2023, 7:33:09 PM
    Author     : SHD
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="icon" href="images/favicon.ico" type="image/x-icon" />
        <title>Home Page</title>

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
        <style>

            .btn{
                width: 80%;
            }
            a{
                color: white;
            }
            table{
                border-spacing: 15px;
            }
            input[type=file]::file-selector-button {
                margin-right: 20px;
                width: 150px;
                border: none;
                background: #084cdf;
                padding: 10px 20px;
                border-radius: 5px;
                color: #fff;
                cursor: pointer;
                transition: background .2s ease-in-out;
            }

            input[type=file]::file-selector-button:hover {
                background: #0d45a5;
            }

            .pagination-wrapper {
            }
            
            #showImport{
                /*position: relative;*/
                height: 300px;
                overflow-y: scroll;

            }
        </style>
    </head>
    <body>
        <%@include file="../guest/header.jsp" %>

        <div class="container-fluid mt-5">
            <div class="container mt-3">
                <h2>Subject List</h2>
                <!<!-- message  -->
                <c:choose>
                    <c:when test="${status eq 'false'}">
                        <div class="alert alert-warning" >
                            <strong>Fail!</strong> Fail update 
                        </div>
                    </c:when>
                    <c:when test="${status eq null}">

                    </c:when>
                    <c:otherwise >
                        <div class="alert alert-success" >
                            <strong>Success!</strong> Successful update 
                        </div>
                    </c:otherwise>
                </c:choose>

                <!<!-- end message  -->
                <div id="datanew">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Code</th>
                                <th>Name</th>
                                <th>Semester</th>
                                <th>No credit</th>
                                <th>Active ${subjectList.size()}</th>


                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${subjectList}" var="sl">
                                <tr>
                                    <td>${sl.code}</td>
                                    <td>${sl.name}</td>
                                    <td>${sl.semester}</td>
                                    <td>${sl.no_credit}</td>
                                    <td>${sl.is_active}</td>

                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                </div>
                <div class="pagination-wrapper">
                    <ul class="pagination" id="myList">
                        <li class="page-item ${pre}"><a class="page-link" href="SubjectImport?index=pre" >Previous</a></li>
                        <li class="page-item ${index1}"><a class="page-link" href="SubjectImport?index=1" id="index1" onclick="">1</a></li>
                        <li class="page-item ${index2}"><a class="page-link" href="SubjectImport?index=2" id="index2">2</a></li>
                        <li class="page-item ${index3}"><a class="page-link" href="SubjectImport?index=3" id="index3">3</a></li>
                        <li class="page-item ${index4}"><a class="page-link" href="SubjectImport?index=4" id="index4">4</a></li>
                        <li class="page-item ${index5}"><a class="page-link" href="SubjectImport?index=5" id="index5">5</a></li>
                        <li class="page-item ${index6}"><a class="page-link" href="SubjectImport?index=6" id="index6">6</a></li>

                        <li class="page-item"><a class="page-link" href="SubjectImport?index=next">Next</a></li>
                    </ul>
                </div>


                <button data-bs-toggle="modal" data-bs-target="#myModal" style="margin-top: 30px">Import Subject</button>
                <button data-bs-toggle="modal" data-bs-target="#myModal2" style="margin-top: 30px">New</button>

            </div>

            <script>
                const ul = document.querySelector('#myList');
                ul.addEventListener('click', (event) => {
                    const li = event.target;
                    li.classList.add("active");
                });
            </script>

        </div>

        <div class="modal " id="myModal" >
            <div class="modal-dialog modal-dialog-centered modal-lg">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">List Subject Import</h4>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        <div class="container mt-3">
                            <form action=""  method="" id="siForm" enctype="multipart/form-data">
                                <table >

                                    <tbody>
                                        <tr >
                                            <td><p style=" margin-bottom: 25px">Select Excel file: </p></td>
                                            <td>
                                                <input type="file" name="file_subject" id="file_subject" style="margin-bottom: 20px"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td><a onclick="uploadFile()" style="width: 150px;background-color: orange; margin-bottom: 20px" class="btn btn-warning">Import</a></td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td><a href="template-file/template-import-subjects.xlsx" download style="text-decoration: none;color: #00a8ff">Download template</a></td>
                                        </tr>
                                    </tbody>
                                </table>
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

        <div class="modal " data-bs-spy="scroll" id="myModal2">
            <div class="modal-dialog modal-dialog-centered modal-lg">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">Add new Subject</h4>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        <div class="container mt-3">
                            <form action="AddSubject"   method="post" class="was-validated" >
                                <input type="number" value="1" name="type" hidden/>

                                <table >

                                    <tbody>
                                        <tr >
                                            <td>Code</td>
                                            <td>
                                                <input type="text" name="code" required/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Name</td>
                                            <td>       
                                                <input type="text" name="name" required/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Semester</td>
                                            <td>
                                                <input type="number" name="semester" required/>

                                            </td>
                                        </tr>
                                        <tr>
                                            <td>No credit</td>
                                            <td>
                                                <input type="number" name="nocredit" required/>

                                            </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td>
                                                <button type="submit">Add New</button>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
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
        <footer class="text-center text-white" style="position: fixed;right: 0;bottom: 0;left: 0;padding: 1rem;background: #003152">
            <!-- Grid container -->

            @FLM System

            <!-- Copyright -->
        </footer>
    </body>

    <script
    src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript">
                                                function uploadFile() {
                                                    var fileInput = document.getElementById('file_subject');
                                                    var file = fileInput.files[0];
                                                    var formData = new FormData();
                                                    formData.append('file', file);
                                                    var xhr = new XMLHttpRequest();
                                                    xhr.open('POST', 'SubjectImport', true);
                                                    xhr.onload = function () {
                                                        if (xhr.status === 200) {
                                                            document.getElementById('showImport').innerHTML = xhr.responseText;
                                                        }
                                                    };
                                                    xhr.send(formData);
                                                }

    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</html>

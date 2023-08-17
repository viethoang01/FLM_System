<%-- 
    Document   : permission_setting
    Created on : Apr 15, 2023, 8:15:05 AM
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

        <title>Decision List</title>
        <style>
            .break {
                flex-basis: 100%;
                height: 0;
            }
            a{
                text-decoration: none;
                font-size: 14px;
            }
            .form-check-input:checked {
                background-color: #02B126;
                border-color: yellowgreen;
            }
            .pagination{
                padding: 30px 0px 10px 40%;

            }

            .pagination ul{
                margin: 0;
                padding: 0;
                list-style-type: none;
            }

            .pagination a{
                display: inline-block;
                padding: 10px 18px;
                color: #222;
            }

            .p1 a{
                width: 40px;
                height: 40px;
                line-height: 40px;
                padding: 0;
                text-align: center;
            }

            .p1 a.is-active{
                background-color: #2ecc71;
                border-radius: 100%;
                color: #fff;
            }
            .container{
                width: 100%;
                max-width: 940px;
                margin: 0 auto;
                position: relative;
                text-align: center;
            }
            .dropdown-menu{
                min-width: 0px;
            }
            .btn-group{
                width: 100px;
            }
            .custom-select{
                width:150px;
                height: 100%;
                border: green 2px solid;
                margin-bottom: 0px !important;
            }
            .btn{
                background-color: #2ecc71 !important;
                color: white !important;
            }
            .tb-sort{
                float: right;
            }
            #showImport{
                max-height: 200px;
                overflow-y: scroll;
            }
            #formdetails{
                margin-left: 25%;
            }
            .details{
                display: inline;
            }
        </style>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> 
    </head>
    <body>
        <%@include file="../header.jsp" %>
        <header class="mt-2">
            <nav>
                <div class="container-fluid">
                    <div id="navbar" class="navbar navbar-default">
                        <div class="navbar-header col-md-1 col-sm-1 col-xs-1">
                            <a class="trigger" href="">Po List </a><br>
                        </div>
                        <div class="col-md-10 col-sm-10 col-xs-10 pull-xs-right">                           

                            <div class="main_menu_wrap">
                                <ul class="main_menu">
                                    <li><a class="trigger" href="#" style="font-weight: bolder">Overview <i class="fa fa-angle-down"></i></a>
                                        <ul class="submenu">
                                            <li><a href="edit-curriculum?id=${curriculum_id}">Edit</a></li>
                                        </ul>
                                    </li>
                                    <li><a class="trigger" href="po-list?curriculum_id=1"">POs</a></li>
                                    <li><a class="trigger" href="plo-list?curriculum_id=1">PLOs</a></li>
                                    <li><a class="trigger" href="po-plo?id=1">PLOs-POs</a></li>
                                    <li><a class="trigger" href="CuriSubjectList?cid=1">Subjects</a></li>
                                    <li><a class="trigger" href="subject_plo?id=1">Subjects-PLOs</a></li>
                                    <li><a class="trigger" href="contact.jsp">Combos</a></li>
                                    <li><a class="trigger" href="contact.jsp">Electives</a></li>

                                </ul>
                            </div>
                        </div>
                        <div class="navbar-header col-md-1 col-sm-1 col-xs-1">

                        </div>


                    </div>
                </div>
            </nav>
        </header>
        <div class="pt-3" style="margin-left:  50px;">
            <a href="home" style="color: blue">Home</a>&ensp;->&ensp;
            <a href="decision-list" style="color: blue">View Decision</a>

        </div>
        <div class="text-center">
            <h3>Po&ensp; List</h3>
        </div>
        <form action="decision-list" method="get" style="margin-left: 50px;">
            <div class="curriculum-search d-flex align-item-center">
                <input type="text"  name="txt" value="${txt}" style="width: 20%;margin-bottom: 0" placeholder="Enter to po name..." >
                <button type="submit" class="btn ms-3">Search</button>


            </div>

        </form>


    </body>
    <script type="text/javascript">
        function uploadFile() {
            $('#showImport').empty();
            var fileInput = document.getElementById('file_po');
            var file = fileInput.files[0];
            if (!file)
                return;
            var formData = new FormData();
            formData.append('file', file);
            var xhr = new XMLHttpRequest();
            xhr.open('POST', 'po-list?curriculum_id=${curriculum_id}', true);
            xhr.onload = function () {
                if (xhr.responseText) {
                    document.getElementById('showImport').innerHTML = xhr.responseText;
                }
                fileInput = null;
                file = null;
            };
            xhr.send(formData);
        }

        // details
        function detailPo(po_id) {

            var xhr = new XMLHttpRequest();
            xhr.open('GET', 'DetailsPo?po_id=' + po_id, true);
            xhr.onload = function () {
                if (xhr.status === 200) {

                    document.getElementById('formdetails').innerHTML = xhr.responseText;
                }
            };
            xhr.send();
        }

        var myForm = document.getElementById('formdetails');
        myForm.onsubmit = function (event) {
            event.preventDefault(); // ngăn chặn form được submit mặc định


            var id = document.getElementById('id').value;
            var name = document.getElementById('name').value;
            var des = document.getElementById('des').value;

            var xhr = new XMLHttpRequest();
            xhr.open('POST', 'DetailsPo?id=' + id + '&name=' + name + '&des=' + des, true);
            xhr.onload = function () {
                if (xhr.status === 200) {

                    swal("successfully!", {
                        icon: "success",
                        timer: 2000
                    });


                } else {
                    swal('fail update', {
                        icon: "error",
                        timer: 3000
                    });
                }
            };
            xhr.send();
        };
        //
        function openSubmit() {
            document.getElementById('btnupdate').classList.remove("disabled");
            document.getElementById("formdetails").action = "DetailsPo";
            document.getElementById("formdetails").classList.add("was-validated");
        }
    </script>

    <script src="dist/sweetalert.min.js"></script>
    <link rel="stylesheet" type="text/css" href="dist/sweetalert.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</html>


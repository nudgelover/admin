<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--jquery--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>

    let marker_add = {
        init: function () {
            $('#register_btn').click(function () {
                marker_add.send();
            })
        },
        send: function () {
            $('#register_form').attr({
                method : 'post',
                action : '/marker/addimpl',
                enctype: 'multipart/form-data'
            });
            $('#register_form').submit();
        }

    };
    $(function () {
        marker_add.init();
        }
    )
</script>
<script>
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                document.getElementById('preview').src = e.target.result;
            };
            reader.readAsDataURL(input.files[0]);
        } else {
            document.getElementById('preview').src = "";
        }
    }
</script>
<style>
    #register_btn {
        max-width: 30%;
        margin-left: 35%;
        margin-right: 35%;
    }

    #preview_box {
        margin: 5% 35%;
    }

    #preview {
        width: 100%;
        height: 100%;
    }
</style>
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Marker ADD</h1>
    <p class="mb-4"> 지도 좌표 등록 페이지 </p>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Marker ADD</h6>
        </div>
        <div class="card-body">
            <figure class="highcharts-figure">
                <div id="container">
                    <form id="register_form" class="user">
                        <%-- id title target lat lng img loc --%>
                        <div class="form-group">
                            <input type="title" class="form-control form-control-user" id="title" name="title"
                                   placeholder="Enter title...">
                        </div>
                        <div class="form-group">
                            <input type="target" class="form-control form-control-user" id="target" name="target"
                                   placeholder="Enter target...">
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-4">
                                <input type="number" class="form-control form-control-user"
                                       id="lat" name="lat" placeholder="Enter lat">
                            </div>
                            <div class="col-sm-4">
                                <input type="number" class="form-control form-control-user"
                                       name="lng" id="lng" placeholder="Enter lng">
                            </div>
                            <div class="col-sm-4">
                                <input type="text" class="form-control form-control-user"
                                       name="loc" id="loc" placeholder="Enter loc">
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="file" class="form-control form-control-user"
                                   id="file" name="file" onchange="readURL(this)" placeholder="Input image">
                            <div id="preview_box"><img id="preview"/></div>
                        </div>

                        <button type="button" id="register_btn" class="btn btn-primary btn-user btn-block">Register
                            Marker
                        </button>
                        <hr>

                    </form>
                </div>
            </figure>
        </div>
        <!-- /.container-fluid -->
    </div>
</div>
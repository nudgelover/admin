<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--jquery--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<style>
    #update_btn, #delete_btn {
        margin-left: 30%;
        max-width: 40%;


    }

    #itemimg {
        width: 100%;
        height: 100%;
        max-width: 300px;
        max-height: 300px;
    }

    #preview_box {
        margin: 5% 35%;
    }

    #preview {
        width: 100%;
        height: 100%;
    }
</style>
<script>
    let item_detail = {
        init: function () {
            $('#update_btn').click(function () {
                item_detail.send();
            });
            $('#delete_btn').click(function () {
                var c = confirm("삭제하시겠습니까?");
                if (c == true) {
                    location.href = "/item/deleteimpl?id=${iteminfo.id}";
                }
            });
        },
        send: function () {
            $('#item_detail_form').attr({
                method : 'post',
                action : '/item/updateimpl',
                enctype: 'multipart/form-data'
            });
            $('#item_detail_form').submit();
        },
    }
    $(function () {
        item_detail.init();
    });
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
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Item Detail</h1>
    <p class="mb-4">Item 상세페이지입니다.</p>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">ITEM : ${iteminfo.name}</h6>
        </div>
        <div class="card-body">
            <figure class="highcharts-figure text-center">

                <p><img id="itemimg" data-toggle="modal" data-target="#img${iteminfo.id}"
                        src="/uimg/${iteminfo.imgname}"></p>


                <h2>${iteminfo.name}</h2>
                <p>가격 :<fmt:formatNumber type="number" pattern="###,###" value="${iteminfo.price}"/>원</p>
                <p>등록일자 :<fmt:formatDate pattern="yyyy/MM/dd hh:mm:ss" value="${iteminfo.rdate}"/></p>

                <form id="item_detail_form" class="user">
                    <input type="hidden" id="id" name="id" value="${iteminfo.id}">
                    <input type="hidden" id="imgname" name="imgname" value="${iteminfo.imgname}">
                    <div class="form-group">
                        <input type="name" class="form-control form-control-user" id="name" name="name"
                               value="${iteminfo.name}">
                    </div>
                    <div class="form-group">
                        <input type="number" class="form-control form-control-user" id="price" name="price"
                               value="${iteminfo.price}">
                    </div>
                    <div class="form-group">
                        <input type="file" class="form-control form-control-user"
                               id="img" name="img" onchange="readURL(this)" placeholder="Input image">
                        <div id="preview_box"><img id="preview"/></div>
                    </div>

                    <hr>

                    <button type="button" id="update_btn" class="btn btn-primary btn-user btn-block">Update Info
                    </button>
                    <button type="button" id="delete_btn" class="btn btn-primary btn-user btn-block">Delete Info
                    </button>

                </form>

            </figure>
        </div>
        <!-- /.container-fluid -->
    </div>
</div>


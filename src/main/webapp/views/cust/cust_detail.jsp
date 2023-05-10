<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<style>
    #detail_form span {
        display: block;
        height: 15px;
        padding: 3px;
        color: red;
        font-weight: 200;
    }
</style>
<script>
    let cust_detail = {
        init: function () {
            $('#update_btn').click(function () {
                cust_detail.send();
            });
            $('#delete_btn').click(function () {
                var c = confirm("삭제하시겠습니까?");
                if (c == true) {
                    location.href = "/cust/deleteimpl?id=${custinfo.id}";
                }
            });
        },
        send: function () {
            $('#detail_form').attr({
                method: 'post',
                action: '/cust/updateimpl'
            });
            $('#detail_form').submit();
        },
    }
    $(function () {
        cust_detail.init();
    });
</script>


<div class="container-fluid">
    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
                <div class="col-lg-3"></div>
                <div class="col-lg-6">
                    <div class="p-5">
                        <div class="text-center">
                            <h1 class="h4 text-gray-900 mb-4">Change your information!</h1>
                        </div>
                        <form id="detail_form" class="user">

                            <div class="form-group">
                                <input type="id" class="form-control form-control-user" id="id" name="id"
                                       value="${custinfo.id}" readonly>
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control form-control-user" id="name" name="name"
                                       value="${custinfo.name}">
                            </div>
                            <div class="form-group row">

                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <input type="password" class="form-control form-control-user"
                                           id="pwd" name="pwd" placeholder="Password">
                                    <span id="check_pwd"> </span>
                                </div>
                                <div class="col-sm-6">
                                    <input type="password" class="form-control form-control-user"
                                           name="pwd2" id="pwd2" placeholder="Repeat Password">
                                    <span id="check_pwd2"> </span>
                                </div>
                            </div>

                            <button type="button" id="update_btn" class="btn btn-primary btn-user btn-block">Change
                                Cust Info
                            </button>
                            <button type="button" id="delete_btn" class="btn btn-primary btn-user btn-block">Delete
                                Cust Info
                            </button>

                        </form>
                        <hr>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

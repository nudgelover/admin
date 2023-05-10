<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--jquery--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>
    let cust_add = {
        init: function () {
            $('#register_btn').click(function () {
                cust_add.send();
            })
        },
        send: function () {
            $('#register_form').attr({
                method:'post',
                action : '/cust/addimpl'
            });
            $('#register_form').submit();
        }

    };
    $(function () {
            cust_add.init();
        }
    )
</script>
<style>
    #register_btn {
        max-width: 30%;
        margin-left: 35%;
        margin-right: 35%;

    }
</style>
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Cust ADD</h1>
    <p class="mb-4"> 테스트 계정 가입 (CUST)</p>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Cust ADD</h6>
        </div>
        <div class="card-body">
            <figure class="highcharts-figure">
                <div id="container">
                    <form id="register_form" class="user">

                        <div class="form-group">
                            <input type="id" class="form-control form-control-user" id="id" name="id"
                                   placeholder="ID">
                            <span id="check_id"> </span>
                        </div>
                        <div class="form-group">
                            <input type="name" class="form-control form-control-user" id="name" name="name"
                                   placeholder="name">
                            <span id="check_name"> </span>
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

                        <button type="button" id="register_btn" class="btn btn-primary btn-user btn-block">Register
                            Account
                        </button>
                        <hr>

                    </form>
                </div>
            </figure>
        </div>
        <!-- /.container-fluid -->
    </div>
</div>
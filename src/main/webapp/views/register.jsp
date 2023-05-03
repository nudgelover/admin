<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<style>
    #register_form span {
        display: block;
        height: 15px;
        padding: 3px;
        color: red;
        font-weight: 200;
    }
</style>
<script>
    let register_form = {
        init: function () {
            $('#register_btn').click(function () {
                register_form.send();
            }),
                $('#id').keyup(function () {
                        var txt_id = $('#id').val();
                        if (txt_id.length <= 3) {
                            $('#check_id').text('id는 4자이상 입력해주셔야합니다.');
                            return;
                        }
                        $.ajax({
                            url    : '/checkid',
                            data   : {id: txt_id},
                            success: function (result) {
                                if (result == 0) {
                                    $('#check_id').text('사용가능한 아이디입니다!');
                                    // ------------------------------------------------------------------매끄럽게 수정필요
                                    $('#pwd').focus();
                                    $('#id').focusout(function () {
                                        $('#check_id').text('');
                                    });
                                } else {
                                    $('#check_id').text('이미 사용중인 아이디입니다!');
                                }
                            }
                        });
                    }
                )
            ;
            $('#pwd2').keyup(function () {
                var pwd1 = $('#pwd').keyup().val();
                var pwd2 = $(this).val();
                if (pwd1 == pwd2) {
                    $('#check_pwd2').text('비밀번호가 일치합니다.');
                } else {
                    $('#check_pwd2').text('비밀번호가 일치하지 않습니다.');
                }


            });


        },
        send: function () {
            var id = $('#id').val();
            var pwd = $('#pwd').val();
            var pwd2 = $('#pwd2').val();
            var lev = $('#lev').val();
            if (id == '') {
                $('#check_id').text('아이디를 입력해주세요.');
                $('#id').focus();
                return;
            }
            if (pwd == '') {
                $('#check_pwd').text('비밀번호를 입력해주세요.');
                $('#pwd').focus();
                return;
            }
            if (pwd2 == '') {
                $('#check_pwd2').text('비밀번호를 입력해주세요.');
                $('#pwd2').focus();
                return;
            }
            if (lev == '') {
                $('#check_lev').text('관리자 등급을 입력해주세요.');
                $('#lev').focus();
                return;
            }

            $('#register_form').attr({
                'action': '/registerimpl',
                'method': 'post'
            });
            $('#register_form').submit();
        }
    };
    $(function () {
        register_form.init();
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
                            <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                        </div>
                        <form id="register_form" class="user">

                            <div class="form-group">
                                <input type="id" class="form-control form-control-user" id="id" name="id"
                                       placeholder="ID">
                                <span id="check_id"> </span>
                            </div>
                            <div class="form-group">
                                <input type="number" class="form-control form-control-user" id="lev" name="lev"
                                       placeholder="level">
                                <span id="check_lev"> </span>
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
                            <a href="index.html" class="btn btn-google btn-user btn-block">
                                <i class="fab fa-google fa-fw"></i> Register with Google
                            </a>
                            <a href="index.html" class="btn btn-facebook btn-user btn-block">
                                <i class="fab fa-facebook-f fa-fw"></i> Register with Facebook
                            </a>
                        </form>
                        <hr>
                        <div class="text-center">
                            <a class="small" href="forgot-password.html">Forgot Password?</a>
                        </div>
                        <div class="text-center">
                            <a class="small" href="/login">Already have an account? Login!</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

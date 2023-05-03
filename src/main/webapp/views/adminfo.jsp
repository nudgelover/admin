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
    let adminfo_form = {
        init: function () {
            $('#adminfo_btn').click(function () {
                adminfo_form.send();
            }),

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
            var pwd = $('#pwd').val();
            var pwd2 = $('#pwd2').val();
            var lev = $('#lev').val();

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

            $('#adminfo_form').attr({
                'action': '/adminfoimpl',
                'method': 'post'
            });
            $('#adminfo_form').submit();
        }
    };
    $(function () {
        adminfo_form.init();
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
                        <form id="adminfo_form" class="user">

                            <div class="form-group">
                                <input type="id" class="form-control form-control-user" id="id" name="id"
                                       value="${adminfo.id}" readonly>
                                <span id="check_id"> </span>
                            </div>
                            <div class="form-group">
                                <input type="number" class="form-control form-control-user" id="lev" name="lev"
                                       value="${adminfo.lev}" >
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

                            <button type="button" id="adminfo_btn" class="btn btn-primary btn-user btn-block">Change
                                Account
                            </button>
                        </form>
                        <hr>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

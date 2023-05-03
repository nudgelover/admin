<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--jquery--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<style>
    #itemimg {
        width: 100%;
        height: 100%;
        max-width: 100px;
        max-height: 100px;
    }
</style>
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Item Detail</h1>
    <p class="mb-4">Item 상세페이지 입니다.</p>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Live Chart</h6>
        </div>
        <div class="card-body">
            <figure class="highcharts-figure">


                <p><img id="itemimg" data-toggle="modal" data-target="#img${obj.id}"
                        src="/uimg/${obj.imgname}"></p>
                <p>${obj.name}</p>
                <p><fmt:formatNumber type="number" pattern="###,###" value="${obj.price}"/>원</p>
                <p><fmt:formatDate pattern="yyyy/MM/dd" value="${obj.rdate}"/></p>


            </figure>
        </div>
        <!-- /.container-fluid -->
    </div>
</div>


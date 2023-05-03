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
    <h1 class="h3 mb-2 text-gray-800">Item ALL</h1>
    <p class="mb-4">Item 조회 페이지 입니다.</p>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Live Chart</h6>
        </div>
        <div class="card-body">
            <figure class="highcharts-figure">
                <div class="table-responsive">
                    <table class="table table-bordered text-center align-items-center" id="dataTable" width="100%"
                           cellspacing="0">
                        <thead>
                        <tr>
                            <th>Img</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Date</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="obj" items="${ilist}">
                            <tr>
                                <td><img id="itemimg" data-toggle="modal" data-target="#img${obj.id}"
                                         src="/uimg/${obj.imgname}"></td>
                                <td>${obj.name}</td>
                                <td><fmt:formatNumber type="number" pattern="###,###" value="${obj.price}"/>원</td>
                                <td><fmt:formatDate pattern="yyyy/MM/dd" value="${obj.rdate}"/></td>
                            </tr>
                            <div id="img${obj.id}" class="modal fade" role="dialog">
                                <div class="modal-dialog">
                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h4 class="modal-title">Detail Image</h4>
                                        </div>
                                        <div class="modal-body text-center d-flex flex-column">
                                            <p>${obj.name}</p>
                                            <img src="/uimg/${obj.imgname}">
                                            <a class="btn btn-" href="/item/detail?id=${obj.id}">상세보기</a>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close
                                            </button>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </figure>
        </div>
        <!-- /.container-fluid -->
    </div>
</div>


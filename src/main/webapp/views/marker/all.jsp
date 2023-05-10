<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--jquery--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>
    let marker_search = {
        init: function () {
            $('#search_btn').click(function () {
                    $('#search_form').attr({
                        method: 'get',
                        action: '/marker/search'

                    })
                    $('#search_form').submit();

                }
            );
        }
    };
    $(function () {
        marker_search.init();
    });
</script>
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
    <h1 class="h3 mb-2 text-gray-800">Marker ALL</h1>
    <p class="mb-4">Marker 조회 페이지 입니다.</p>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Marker All</h6>
        </div>
        <form id="search_form">
            <div class="card-body d-flex">
                <label>Location :  </label>
                <select class="form-control col-sm-1" name="loc">
                    <option value="">전체</option>
                    <option value="S" <c:if test="${ms.loc=='S'}">selected</c:if>>서울</option>
                    <option value="B" <c:if test="${ms.loc=='B'}">selected</c:if>>부산</option>
                    <option value="J" <c:if test="${ms.loc=='J'}">selected</c:if>>제주</option>
                </select>

                <label>Title : </label> <input class="form-control col-sm-5 " type="text" id="title" name="title"
                                               value="${ms.title}" placeholder="Enter title...">


                <button class="btn btn-primary small" id="search_btn" type="button">Search</button>
            </div>
        </form>


        <div class="card-body">
            <figure class="highcharts-figure">
                <div class="table-responsive">
                    <table class="table table-bordered text-center align-items-center" id="dataTable" width="100%"
                           cellspacing="0">
                        <thead>
                        <tr>
                            <th>Img</th>
                            <th>Title</th>
                            <th>Lat</th>
                            <th>lng</th>
                            <th>loc</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="obj" items="${mlist}">
                            <tr>
                                <td><img id="itemimg" data-toggle="modal" data-target="#img${obj.id}"
                                         src="/uimg/${obj.img}"></td>
                                <td>${obj.title}</td>
                                <td>${obj.lat}</td>
                                <td>${obj.lng}</td>
                                <td>${obj.loc}</td>
                            </tr>
                            <div id="img${obj.id}" class="modal fade" role="dialog">
                                <div class="modal-dialog">
                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h4 class="modal-title">Detail Image</h4>
                                        </div>
                                        <div class="modal-body text-center d-flex flex-column">
                                            <p>${obj.title}</p>
                                            <img src="/uimg/${obj.img}">
                                            <a class="btn btn-" href="/marker/detail?id=${obj.id}">상세보기</a>
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



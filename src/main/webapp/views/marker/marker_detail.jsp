<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--jquery--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<style>
    #map {
        width: 400px;
        height: 400px;
        border: 2px solid red;
    }

    #update_btn, #delete_btn {
        margin-left: 30%;
        max-width: 40%;

    }

    #markerimg {
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
    let marker_detail_map = {
        map : null,
        init: function () {
            var mapContainer = document.querySelector('#map');
            var mapOption = {
                center: new kakao.maps.LatLng(${markerinfo.lat}, ${markerinfo.lng}), // 지도의 중심좌표
                level : 3 // 지도의 확대 레
            };
            map = new kakao.maps.Map(mapContainer, mapOption);
            var mapTypeControl = new kakao.maps.MapTypeControl();

            // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
            // kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
            map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

            // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
            var zoomControl = new kakao.maps.ZoomControl();
            map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

            var markerPosition = new kakao.maps.LatLng(${markerinfo.lat}, ${markerinfo.lng});

            // 마커를 생성합니다
            var marker = new kakao.maps.Marker({
                position: markerPosition
            });

            // 마커가 지도 위에 표시되도록 설정합니다
            marker.setMap(map);
            // 마커에 커서가 오버됐을 때 마커 위에 표시할 인포윈도우를 생성합니다
            var iwContent = '<img src="/uimg/${markerinfo.img}" style="width: 150px"><div style="padding:5px;">${markerinfo.title}</div>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

            // 인포윈도우를 생성합니다
            var infowindow = new kakao.maps.InfoWindow({
                content: iwContent
            });

// 마커에 마우스오버 이벤트를 등록합니다
            kakao.maps.event.addListener(marker, 'mouseover', function () {
                // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
                infowindow.open(map, marker);
            });

// 마커에 마우스아웃 이벤트를 등록합니다
            kakao.maps.event.addListener(marker, 'mouseout', function () {
                // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
                infowindow.close();
            });

            kakao.maps.event.addListener(marker, 'click', function () {
                location.href = '${markerinfo.target}';
            });
        }
    };
    let marker_detail = {
        init: function () {
            $('#update_btn').click(function () {
                marker_detail.send();
            });
            $('#delete_btn').click(function () {
                var c = confirm("삭제하시겠습니까?");
                if (c == true) {
                    location.href = "/marker/deleteimpl?id=${markerinfo.id}";
                }
            });
        },
        send: function () {
            $('#marker_detail_form').attr({
                method : 'post',
                action : '/marker/updateimpl',
                enctype: 'multipart/form-data'
            });
            $('#marker_detail_form').submit();
        },
    }
    $(function () {
        marker_detail_map.init();
        marker_detail.init();
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
    <h1 class="h3 mb-2 text-gray-800">Marker Detail</h1>
    <p class="mb-4">Marker 상세페이지입니다.</p>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">marker : ${markerinfo.title}</h6>
        </div>
        <div class="card-body">
            <figure class="highcharts-figure text-center">

                <p><img id="markerimg" data-toggle="modal" data-target="#img${markerinfo.id}"
                        src="/uimg/${markerinfo.img}"></p>


                <h2>${markerinfo.title}</h2>

                <form id="marker_detail_form" class="user">
                    <input type="hidden" id="id" name="id" value="${markerinfo.id}">
                    <input type="hidden" id="img" name="img" value="${markerinfo.img}">
                    <div class="form-group">
                        <input type="title" class="form-control form-control-user" id="title" name="title"
                               value="${markerinfo.title}">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control form-control-user" id="target" name="target"
                               value="${markerinfo.target}">
                    </div>
                    <div class="form-group">
                        <input type="number" class="form-control form-control-user" id="lat" name="lat"
                               value="${markerinfo.lat}">
                    </div>
                    <div class="form-group">
                        <input type="number" class="form-control form-control-user" id="lng" name="lng"
                               value="${markerinfo.lng}">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control form-control-user" id="loc" name="loc"
                               value="${markerinfo.loc}">
                    </div>
                    <div class="form-group">
                        <input type="file" class="form-control form-control-user"
                               id="file" name="file" onchange="readURL(this)" placeholder="Input image">
                        <div id="preview_box"><img id="preview"/></div>
                    </div>


                    <hr>

                    <button type="button" id="update_btn" class="btn btn-primary btn-user btn-block">Update Info
                    </button>
                    <button type="button" id="delete_btn" class="btn btn-primary btn-user btn-block">Delete Info
                    </button>


                </form>


            </figure>
            <div class="card-body">
                <div class="row ">
                    <div class="col-sm-10" id="map"></div>
                </div>
            </div>
        </div>
        <!-- /.container-fluid -->
    </div>
</div>


<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<style>
    <%-- 차트 1--%>
    .highcharts-figure,
    .highcharts-data-table table {
        min-width: 360px;
        max-width: 800px;
        margin: 1em auto;
    }

    .highcharts-data-table table {
        font-family: Verdana, sans-serif;
        border-collapse: collapse;
        border: 1px solid #ebebeb;
        margin: 10px auto;
        text-align: center;
        width: 100%;
        max-width: 500px;
    }

    .highcharts-data-table caption {
        padding: 1em 0;
        font-size: 1.2em;
        color: #555;
    }

    .highcharts-data-table th {
        font-weight: 600;
        padding: 0.5em;
    }

    .highcharts-data-table td,
    .highcharts-data-table th,
    .highcharts-data-table caption {
        padding: 0.5em;
    }

    .highcharts-data-table thead tr,
    .highcharts-data-table tr:nth-child(even) {
        background: #f8f8f8;
    }

    .highcharts-data-table tr:hover {
        background: #f1f7ff;
    }

</style>
<script>
    let websocket_dash = {
        stompClient: null,
        init       : function () {
            this.connect();
        },
        connect    : function () {
            var socket = new SockJS('${adminserver}/wss');
            this.stompClient = Stomp.over(socket);
            this.stompClient.connect({}, function (frame) {
                // console.log('Connected: ' + frame);
                this.subscribe('/sendadm', function (msg) {
                    $('#content1_msg').text(JSON.parse(msg.body).content1 + '개');
                    $('#content2_msg').text((JSON.parse(msg.body).content2).toLocaleString() + '원');
                    $('#content3_msg').text((JSON.parse(msg.body).content3) + '%');
                    $('#content4_msg').text(JSON.parse(msg.body).content4);
                    $('#progress1').css('width', JSON.parse(msg.body).content1 / 1000 * 100 + '%');
                    $('#progress1').attr('aria-valuenow', JSON.parse(msg.body).content1);
                    $('#progress1').text((JSON.parse(msg.body).content1 / 1000 * 100).toFixed(2) + '% Complete');

                    $('#progress2').css('width', JSON.parse(msg.body).content2 / 15000000 * 100 + '%');
                    $('#progress2').attr('aria-valuenow', JSON.parse(msg.body).content2);
                    $('#progress2').text((JSON.parse(msg.body).content2 / 15000000 * 100).toFixed(2) + '% Complete');

                    $('#progress3').css('width', JSON.parse(msg.body).content3 + '%');
                    $('#progress3').attr('aria-valuenow', JSON.parse(msg.body).content3);
                    $('#progress3').text((JSON.parse(msg.body).content3) + '% Complete');
                    // $('#progress3').attr('aria-valuenow', JSON.parse(msg.body).content3/500*100);
                    // 이렇게 백분율 줄 수도 있다!(근데 난 random num 이 1~100 이라 필요없음
                    $('#progress4').css('width', JSON.parse(msg.body).content4 + '%');
                    $('#progress4').attr('aria-valuenow', JSON.parse(msg.body).content4);
                    $('#progress4').text((JSON.parse(msg.body).content4) + '% Complete');

                });
            });
        }
    };
    let dash_chart = {
        init      : function () {
            $.ajax({
                url    : '/monthlychart1',
                success: function (data) {
                    dash_chart.display(data);
                }

            });


        }, display: function (data) {
            Highcharts.chart('container1', {
                chart      : {
                    type: 'line'
                },
                title      : {
                    text: '월별 매출 통계'
                },
                subtitle   : {
                    text: '남성, 여성을 기준으로 월별 매출 통계를 나타낸 그래프입니다.'
                },
                xAxis      : {
                    categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
                },
                yAxis      : {
                    title: {
                        text: '원'
                    }
                },
                plotOptions: {
                    line: {
                        dataLabels         : {
                            enabled: true
                        },
                        enableMouseTracking: false
                    }
                },
                series     : data
                // [{
                //     name: 'Male',
                //     data: [16.0, 18.2, 23.1, 27.9, 32.2, 36.4, 39.8, 38.4, 35.5, 29.2,
                //         22.0, 17.8]
                // }, {
                //     name: 'Female',
                //     data: [-2.9, -3.6, -0.6, 4.8, 10.2, 14.5, 17.6, 16.5, 12.0, 6.5,
                //         2.0, -0.9]
                // }]
            });


        }
    };
    let dash_chart2 = {
        init      : function () {
            $.ajax({
                url    : '/monthlychart1',
                success: function (data) {
                    dash_chart2.display(data);
                }
            });

        }, display: function (data) {
            Highcharts.chart('container2', {
                chart      : {
                    type: 'column'
                },
                title      : {
                    text: '월별 매출액'
                },
                subtitle   : {
                    text: '남성 여성 월별 매출액 통계입니다.'
                },
                xAxis      : {
                    categories: [
                        'Jan',
                        'Feb',
                        'Mar',
                        'Apr',
                        'May',
                        'Jun',
                        'Jul',
                        'Aug',
                        'Sep',
                        'Oct',
                        'Nov',
                        'Dec'
                    ],
                    crosshair : true
                },
                yAxis      : {
                    min  : 0,
                    title: {
                        text: '천원'
                    }
                },
                tooltip    : {
                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                    pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                        '<td style="padding:0"><b>{point.y} 원</b></td></tr>',
                    footerFormat: '</table>',
                    shared      : true,
                    useHTML     : true
                },
                plotOptions: {
                    column: {
                        pointPadding: 0.2,
                        borderWidth : 0
                    }
                },
                series     :  data
                //     [{
                //     name: 'Male',
                //     data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4,
                //         194.1, 95.6, 54.4]
                //
                // }, {
                //     name: 'Female',
                //     data: [83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5,
                //         106.6, 92.3]
                //
                // }]
            });
        }
    };
    let dash_chart3 = {
        init   : function () {
            $.ajax({
                url:'/monthlychart2',
                success : function (data){
                    dash_chart3.display(data);
                }
            })
        },
        display: function (data) {
            Highcharts.chart('container3', {
                title      : {
                    text : 'Sales of petroleum products March, Norway',
                    align: 'left'
                },
                xAxis      : {
                    categories: ['Jet fuel', 'Duty-free diesel', 'Petrol', 'Diesel', 'Gas oil']
                },
                yAxis      : {
                    title: {
                        text: 'Million liters'
                    }
                },
                tooltip    : {
                    valueSuffix: ' million liters'
                },
                plotOptions: {
                    series: {
                        borderRadius: '25%'
                    }
                },
                series     :data,
            });


        }
    }
    $(function () {
        websocket_dash.init();
        dash_chart.init();
        dash_chart2.init();
        dash_chart3.init();

        setInterval(dash_chart.init,5000);
        setInterval(dash_chart2.init,5000);
    });
</script>
<!-- Begin Page Content -->
<div class="container-fluid">
    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Dashboard2</h1>
        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
    </div>
    <!-- Content Row -->
    <div class="row">

        <!-- Earnings (Monthly) Card Example -->
        <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                Total order quantity(1천개 목표)
                            </div>
                            <div class="row no-gutters align-items-center">
                                <div class="col-auto">
                                    <div id="content1_msg" class="h5 mb-0 mr-3 font-weight-bold text-gray-800">50%</div>
                                </div>
                                <div class="col">
                                    <div class="progress progress-sm mr-2">
                                        <div id="progress1" class="progress-bar bg-primary" role="progressbar"
                                             style="width:50%" aria-valuenow="50" aria-valuemin="0"
                                             aria-valuemax="100"> % Complete
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-calendar fa-2x text-gray-300"></i>

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Earnings (Monthly) Card Example -->
        <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                Total order price(15백만 목표)
                            </div>
                            <div class="row no-gutters align-items-center">
                                <div class="col-auto">
                                    <div id="content2_msg" class="h5 mb-0 mr-3 font-weight-bold text-gray-800">50%</div>
                                </div>
                                <div class="col">
                                    <div class="progress progress-sm mr-2">
                                        <div id="progress2" class="progress-bar bg-success" role="progressbar"
                                             style="width:50%" aria-valuenow="50" aria-valuemin="0"
                                             aria-valuemax="100"> % Complete
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="col-auto">
                            <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Earnings (Monthly) Card Example -->
        <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-info shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Tasks
                            </div>
                            <div class="row no-gutters align-items-center">
                                <div class="col-auto">
                                    <div id="content3_msg" class="h5 mb-0 mr-3 font-weight-bold text-gray-800">50%</div>
                                </div>
                                <div class="col">
                                    <div class="progress progress-sm mr-2">
                                        <div id="progress3" class="progress-bar bg-info" role="progressbar"
                                             style="width:50%" aria-valuenow="50" aria-valuemin="0"
                                             aria-valuemax="100"> % Complete
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Pending Requests Card Example -->
        <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-warning shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                Pending Requests
                            </div>
                            <div class="row no-gutters align-items-center">
                                <div class="col-auto">
                                    <div id="content4_msg" class="h5 mb-0 mr-3 font-weight-bold text-gray-800">50%</div>
                                </div>
                                <div class="col">
                                    <div class="progress progress-sm mr-2">
                                        <div id="progress4" class="progress-bar bg-warning" role="progressbar"
                                             style="width:50%" aria-valuenow="50" aria-valuemin="0"
                                             aria-valuemax="100"> % Complete
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-comments fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Content Row -->
    <div class="row">
        <!-- Area Chart -->
        <div class="col-xl-6 col-lg-7">
            <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div
                        class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                    <h6 class="m-0 font-weight-bold text-primary">Earnings Overview</h6>
                </div>
                <!-- Card Body -->
                <div class="card-body">
                    <div id="container1" class="chart-area">
                    </div>
                    <p>SELECT TO_NUMBER(TO_CHAR(rdate, 'MM')) AS month, gender, SUM(price) AS total</p>
                    <p> FROM sales</p>
                    <p> GROUP BY TO_CHAR(rdate, 'MM'), gender</p>
                    <p> ORDER BY 1;</p>
                    <p>chart dto 만들고 mapper.java, mapper.xml 만들기</p>
                </div>
            </div>
        </div>
        <!-- Chart2 -->
        <div class="col-xl-6 col-lg-7">
            <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div
                        class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                    <h6 class="m-0 font-weight-bold text-primary">Revenue Sources</h6>
                </div>
                <!-- Card Body -->
                <div class="card-body">
                    <div id="container2" class="chart-area">
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xl-6 col-lg-7">
            <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div
                        class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                    <h6 class="m-0 font-weight-bold text-primary">Revenue Sources</h6>
                </div>
                <!-- Card Body -->
                <div class="card-body">
                    <div id="container3" class="chart-area">
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xl-6 col-lg-7">
            <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div
                        class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                    <h6 class="m-0 font-weight-bold text-primary">Revenue Sources</h6>
                </div>
                <!-- Card Body -->
                <div class="card-body">
                    <div id="container4" class="chart-area">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Content Row -->
</div>
<!-- /.container-fluid -->
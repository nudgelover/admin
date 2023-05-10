<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>
<style>
    .highcharts-figure,
    .highcharts-data-table table {
        min-width: 320px;
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

    input[type="number"] {
        min-width: 50px;
    }


    .highcharts-figure,
    .highcharts-data-table table {
        min-width: 310px;
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
    let chart = {

            init    : function () {

                this.getdata();
                this.getdata2();

            },
            getdata : function () {

                $.ajax({
                    url    : '/managerchart',
                    success: function (result) {
                        console.log(result);
                        chart.display(result)
                    }
                })

            },
            getdata2: function () {
                chart.display2();
            },
            display : function (result) {
                Highcharts.chart('chart', {
                    chart        : {
                        plotBackgroundColor: null,
                        plotBorderWidth    : null,
                        plotShadow         : false,
                        type               : 'pie'
                    },
                    title        : {
                        text : 'Manager chart',
                        align: 'left'
                    },
                    tooltip      : {
                        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                    },
                    accessibility: {
                        point: {
                            valueSuffix: '%'
                        }
                    },
                    plotOptions  : {
                        pie: {
                            allowPointSelect: true,
                            cursor          : 'pointer',
                            dataLabels      : {
                                enabled: true,
                                format : '<b>{point.name}</b>: {point.percentage:.1f} %'
                            }
                        }
                    },
                    series       : [{
                        name        : 'Branch',
                        colorByPoint: true,
                        data        : result


                    }]
                });


            },
            display2: function (result) {
                Highcharts.chart('container', {
                    chart      : {
                        type: 'spline'
                    },
                    title      : {
                        text: 'Monthly Average Temperature'
                    },
                    subtitle   : {
                        text: 'Source: ' +
                            '<a href="https://en.wikipedia.org/wiki/List_of_cities_by_average_temperature" ' +
                            'target="_blank">Wikipedia.com</a>'
                    },
                    xAxis      : {
                        categories   : ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                            'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
                        accessibility: {
                            description: 'Months of the year'
                        }
                    },
                    yAxis      : {
                        title : {
                            text: 'Temperature'
                        },
                        labels: {
                            formatter: function () {
                                return this.value + '°';
                            }
                        }
                    },
                    tooltip    : {
                        crosshairs: true,
                        shared    : true
                    },
                    plotOptions: {
                        spline: {
                            marker: {
                                radius   : 4,
                                lineColor: '#666666',
                                lineWidth: 1
                            }
                        }
                    },
                    series     : [{
                        name  : 'Tokyo',
                        marker: {
                            symbol: 'square'
                        },
                        data  : [5.2, 5.7, 8.7, 13.9, 18.2, 21.4, 25.0, 26, 22.8, 17.5, 12.1, 7.6]

                    }, {
                        name  : 'Bergen',
                        marker: {
                            symbol: 'diamond'
                        },
                        data  : [1.0, 1.6, 3.3, 5.9, 10.5, 13.5, 14.5, 14.4, 11.5, 8.7, 4.7, 2.6]
                    }, {
                        name  : 'Korea',
                        marker: {
                            symbol: 'diamond'
                        },
                        data  : [1.0, 1.6, 4.3, 5.9, 10.5, 18.5, 19.5, 20.4, 11.5, 9.7, 4.7, 2.6]
                    }]
                });

            }

        }
    ;

    $(function () {
            chart.init();

        }
    )
    ;
</script>
<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Manager Charts</h1>
    <p class="mb-4"> Manager Charts</p>

    <!-- Content Row -->
    <div class="row">

        <div class="col-xl-8 col-lg-7">

            <!-- Area Chart -->
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Manager Chart</h6>
                </div>
                <figure class="highcharts-figure">
                    <div id="chart"></div>
                    <p class="highcharts-description">
                        각 나라별 근무 중인 Manager의 인원을 파악할 수 있는 차트입니다.
                    </p>
                </figure>

            </div>

            <!-- Bar Chart -->
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Bar Chart</h6>
                </div>
                <figure class="highcharts-figure">
                    <div id="container"></div>
                    <p class="highcharts-description">
                        This chart shows how symbols and shapes can be used in charts.
                        Highcharts includes several common symbol shapes, such as squares,
                        circles and triangles, but it is also possible to add your own
                        custom symbols. In this chart, custom weather symbols are used on
                        data points to highlight that certain temperatures are warm while
                        others are cold.
                    </p>
                </figure>
            </div>

        </div>

        <!-- Donut Chart -->
        <div class="col-xl-4 col-lg-5">
            <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Donut Chart</h6>
                </div>
                <!-- Card Body -->
                <div class="card-body">
                    <div class="chart-pie pt-4">
                        <canvas id="myPieChart"></canvas>
                    </div>
                    <hr>
                    Styling for the donut chart can be found in the
                    <code>/js/demo/chart-pie-demo.js</code> file.
                </div>
            </div>
        </div>
    </div>

</div>
<!-- /.container-fluid -->


<!-- End of Main Content -->
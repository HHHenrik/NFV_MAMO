angular.module('myApp',['treeControl','ngAnimate', 'ngSanitize', 'ui.bootstrap']);
angular.module('myApp').controller('myCtrl', function ($scope) {
    var url = document.URL;
    var getVal = url.split('?')[1];
    // var sfcId = getVal.split('&')[0].split('=')[1];
    // var vnfId = getVal.split('&')[1].split('=')[1];

    var sfcId = 1; var vnfId = 0;

    laydate.render({
        elem:'#cpu_begin_time',
        type:'datetime'
    });
    laydate.render({
        elem:'#cpu_end_time',
        type:'datetime'
    });
    laydate.render({
        elem:'#memory_begin_time',
        type:'datetime'
    });
    laydate.render({
        elem:'#memory_end_time',
        type:'datetime'
    });
    laydate.render({
        elem:'#package_begin_time',
        type:'datetime'
    });
    laydate.render({
        elem:'#package_end_time',
        type:'datetime'
    });

    $scope.treeOptions = {
        nodeChildren:"children",
        dirSelectable:true,
        injectClasses:{
            ul:"a1",
            li:"a2",
            liSelected:"a7",
            iExpanded:"a3",
            iCollapsed:"a4",
            iLeaf:"a5",
            label:"a6",
            labelSelected:"a8"
        }
    };

    $scope.initFlag = false;
    $scope.cpuFlag = true;
    $scope.memoryFlag = true;
    $scope.packageFlag = true;

    var vnfInfo = {"sfcId":sfcId, "vnfId":vnfId, "beginTime":"-1", "endTime":"-1"};

    $scope.fetchVnfData = function (vnfData, flag) {
        $.ajax({
            url:"vnfMonitorData.json",
            method:"GET",
            data:vnfData,
            dataType:"json",
            timeout:10000,
            success:function (data) {
                $scope.groupsTreeModel = JSON.parse(data.vnfData);
                $scope.cpuUtilArray = JSON.parse(data.cpuUtilArray);
                $scope.cpuThreUp = JSON.parse(data.cpuThreUp);
                $scope.cpuThreDown = JSON.parse(data.cpuThreDown);
                $scope.memUtilArray = JSON.parse(data.memUtilArray);
                $scope.memThreUp = JSON.parse(data.memThreUp);
                $scope.memThreDown = JSON.parse(data.memThreDown);
                $scope.packageRece = JSON.parse(data.packageRece);
                $scope.packageDeal = JSON.parse(data.packageDeal);
                $scope.dateArray = JSON.parse(data.dateArray);
                if (flag == 1){
                    var series = [
                        {name:'CPU利用率', data:$scope.cpuUtilArray},
                        {name:'阈值上限', data:$scope.cpuThreUp},
                        {name:'阈值下限', data:$scope.cpuThreDown}
                    ];
                    var title = {
                        text:'CPU利用率走势图',
                        style:{
                            color:'white',
                            fontFamily: '"Times New Roman", 宋体, sans-serif'
                        }
                    };
                    var cpu_json = getSplineData($scope.dateArray, series, title, '利用率%');
                    Highcharts.chart('cpu_container', cpu_json);
                }else if (flag == 2) {
                    $scope.memoryFlag = false;
                    var series = [
                        {name:'内存利用率', data:$scope.memUtilArray},
                        {name:'阈值上限', data:$scope.memThreUp},
                        {name:'阈值下限', data:$scope.memThreDown}
                    ];
                    var title = {
                        text:'内存利用率走势图',
                        style:{
                            color:'white',
                            fontFamily: '"Times New Roman", 宋体, sans-serif'
                        }
                    };
                    var memory_json = getSplineData($scope.dateArray, series, title, '利用率%');
                    Highcharts.chart('memory_container', memory_json);
                }else if (flag == 3){
                    $scope.packageFlag = false;
                    var series = [
                        {name:'接收到的数据包', data:$scope.packageRece},
                        {name:'处理的数据包', data:$scope.packageDeal}
                    ];
                    var title = {
                        text:'数据包处理走势图',
                        style:{
                            color:'white',
                            fontFamily: '"Times New Roman", 宋体, sans-serif'
                        }
                    };
                    var package_json = getSplineData($scope.dateArray, series, title, '数据包数量');
                    Highcharts.chart('package_container', package_json);
                }
                $scope.$apply();
            },
            error:function () {
                alert("网络连接超时,请重试!");
            }
        });
    };

    $scope.fetchVnfData(vnfInfo, 0);

    $scope.treeLeafClick = function (sel) {
        $scope.selectedNode = sel;

        $scope.initFlag = true;
        $scope.cpuFlag = true;
        $scope.memoryFlag = true;
        $scope.packageFlag = true;

        if (sel.name.indexOf("Cpu") == 0){
            $scope.cpuFlag = false;
            var series = [
                {name:'CPU利用率', data:$scope.cpuUtilArray},
                {name:'阈值上限', data:$scope.cpuThreUp},
                {name:'阈值下限', data:$scope.cpuThreDown}
            ];
            var title = {
                text:'CPU利用率走势图',
                style:{
                    color:'white',
                    fontFamily: '"Times New Roman", 宋体, sans-serif'
                }
            };
            var cpu_json = getSplineData($scope.dateArray, series, title, '利用率%');
            Highcharts.chart('cpu_container', cpu_json);
        }else if (sel.name.indexOf("Memory") == 0){
            $scope.memoryFlag = false;
            var series = [
                {name:'内存利用率', data:$scope.memUtilArray},
                {name:'阈值上限', data:$scope.memThreUp},
                {name:'阈值下限', data:$scope.memThreDown}
            ];
            var title = {
                text:'内存利用率走势图',
                style:{
                    color:'white',
                    fontFamily: '"Times New Roman", 宋体, sans-serif'
                }
            };
            var memory_json = getSplineData($scope.dateArray, series, title, '利用率%');
            Highcharts.chart('memory_container', memory_json);
        }else if (sel.name.indexOf("Package") == 0){
            $scope.packageFlag = false;
            var series = [
                {name:'接收到的数据包', data:$scope.packageRece},
                {name:'处理的数据包', data:$scope.packageDeal}
            ];
            var title = {
                text:'数据包处理走势图',
                style:{
                    color:'white',
                    fontFamily: '"Times New Roman", 宋体, sans-serif'
                }
            };
            var package_json = getSplineData($scope.dateArray, series, title, '数据包数量');
            Highcharts.chart('package_container', package_json);
        }else {
            $scope.initFlag = false;
        }
    };

    $scope.getCpuTimeRange = function () {
        var cpuBeginTime = $("#cpu_begin_time").val();
        var cpuEndTime = $("#cpu_end_time").val();
        $scope.vnfTime = {"sfcId":sfcId, "vnfId":vnfId, "beginTime":cpuBeginTime, "endTime":cpuEndTime};
        $scope.fetchVnfData($scope.vnfTime, 1);
    };

    $scope.clearCpuTimeRange = function () {
        $("#cpu_begin_time").val("");
        $("#cpu_end_time").val("");
        $scope.fetchVnfData(vnfInfo, 1);
    };

    $scope.getMemoeyTimeRange = function () {
        var memoryBeginTime = $("#memory_begin_time").val();
        var memoryEndTime = $("#memory_end_time").val();
        $scope.vnfTime = {"sfcId":sfcId, "vnfId":vnfId, "beginTime":memoryBeginTime, "endTime":memoryEndTime};
        $scope.fetchVnfData($scope.vnfTime, 2);
    };

    $scope.clearMemoryTimeRange = function () {
        $("#memory_begin_time").val("");
        $("#memory_end_time").val("");
        $scope.fetchVnfData(vnfInfo, 2);
    };

    $scope.getPackageTimeRange = function () {
        var packageBeginTime = $("#package_begin_time").val();
        var packageEndTime = $("#package_end_time").val();
        $scope.vnfTime = {"sfcId":sfcId, "vnfId":vnfId, "beginTime":packageBeginTime, "endTime":packageEndTime};
        $scope.fetchVnfData($scope.vnfTime, 3);
    };

    $scope.clearPackageTimeRange = function () {
        $("#package_begin_time").val("");
        $("#package_end_time").val("");
        $scope.fetchVnfData(vnfInfo, 3);
    };

    function getSplineData(dateArray, series, title, yAxisText) {
        var chart = {
            backgroundColor:'rgba(0, 0, 0, 0.4)',
            zoomType: 'x'
        };
        var subtitle = {
            text: document.ontouchstart === undefined ?
                '鼠标拖动可以进行缩放' : '手势操作进行缩放'
        };
        var xAxis = {
            tickWidth: 0,
            // gridLineWidth: 1,
            title:{
                text:"时间",
                style:{
                    color:'white',
                    fontFamily: '"Times New Roman", 宋体, sans-serif',
                    fontSize:'14px'
                },
                align:'high'
            },
            categories : dateArray,
            labels:{
                style:{
                    color:'white',
                    fontFamily: '"Times New Roman", 宋体, sans-serif',
                    fontSize:'14px'
                }
            },
            type: 'datetime',
            dateTimeLabelFormats: {
                millisecond: '%H:%M:%S.%L',
                second: '%H:%M:%S',
                minute: '%H:%M',
                hour: '%H:%M',
                day: '%m-%d',
                week: '%m-%d',
                month: '%Y-%m',
                year: '%Y'
            }
        };
        var yAxis = {
            title:{
                text:yAxisText,
                style:{
                    color:'white',
                    fontFamily: '"Times New Roman", 宋体, sans-serif',
                    fontSize:'14px'
                }
            },
            showFirstLabel:false,
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }],
            labels:{
                style:{
                    color:'white',
                    fontFamily: '"Times New Roman", 宋体, sans-serif',
                    fontSize:'14px'
                }
            }
        };

        var legend = {
            layout:'vertical',
            align:'right',
            verticalAlign:'middle',
            borderWidth:0,
            itemStyle:{
                color:'white',
                fontFamily: '"Times New Roman", 宋体, sans-serif',
                fontSize:'16px'
            }
        };
        var tooltip = {
            dateTimeLabelFormats: {
                millisecond: '%H:%M:%S.%L',
                second: '%H:%M:%S',
                minute: '%H:%M',
                hour: '%H:%M',
                day: '%Y-%m-%d',
                week: '%m-%d',
                month: '%Y-%m',
                year: '%Y'
            },
            valueSuffix:'%'
        };
        var plotOptions = {
            area: {
                fillColor: {
                    linearGradient: {
                        x1: 0,
                        y1: 0,
                        x2: 0,
                        y2: 1
                    },
                    stops: [
                        [0, Highcharts.getOptions().colors[0]],
                        [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                    ]
                },
                marker: {
                    radius: 2
                },
                lineWidth: 1,
                states: {
                    hover: {
                        lineWidth: 1
                    }
                },
                threshold: null
            },
            series:{
                cursor:'pointer',
                marker:{
                    lineWidth: 1
                }
            }
        };
        var json = {};
        json.chart = chart;
        json.title = title;
        json.subtitle = subtitle;
        json.xAxis = xAxis;
        json.yAxis = yAxis;
        json.legend = legend;
        json.tooltip = tooltip;
        json.plotOptions = plotOptions;
        json.series = series;
        return json;
    }
});
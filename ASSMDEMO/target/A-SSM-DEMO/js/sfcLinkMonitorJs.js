angular.module('myApp',['treeControl','ngAnimate', 'ngSanitize', 'ui.bootstrap']);
angular.module('myApp').controller('myCtrl', function ($scope) {
    var url = document.URL;
    var getVal = url.split('?')[1];
    // var sfcId = getVal.split('&')[0].split('=')[1];
    // var linkId = getVal.split('&')[1].split('=')[1];

    var sfcId = 1; var linkId = 0;

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

    var vnfInfo = {"sfcId":sfcId, "linkId":linkId, "beginTime":"-1", "endTime":"-1"};

    $scope.fetchVnfData = function (linkData, flag) {
        $.ajax({
            url:"linkMonitorData.json",
            method:"GET",
            data:linkData,
            dataType:"json",
            timeout:10000,
            success:function (data) {
                $scope.groupsTreeModel = JSON.parse(data.linkData);
                $scope.bwUtilArray = JSON.parse(data.bwUtilArray);
                $scope.bwThreUp = JSON.parse(data.bwThreUp);
                $scope.bwThreDown = JSON.parse(data.bwThreDown);
                $scope.delay = JSON.parse(data.delay);
                $scope.delayThreshold = JSON.parse(data.delayThreshold);
                $scope.dateArray = JSON.parse(data.dateArray);
                if (flag == 1){
                    var series = [
                        {name:'带宽利用率', data:$scope.bwUtilArray},
                        {name:'阈值上限', data:$scope.bwThreUp},
                        {name:'阈值下限', data:$scope.bwThreDown}
                    ];
                    var title = {
                        text:'带宽利用率走势图',
                        style:{
                            color:'white',
                            fontFamily: '"Times New Roman", 宋体, sans-serif'
                        }
                    };
                    var cpu_json = getSplineData($scope.dateArray, series, title, '利用率%');
                    Highcharts.chart('cpu_container', cpu_json);
                }else if (flag == 2) {
                    var series = [
                        {name:'链路延迟', data:$scope.delay},
                        {name:'阈值', data:$scope.delayThreshold}
                    ];
                    var title = {
                        text:'链路延迟走势图',
                        style:{
                            color:'white',
                            fontFamily: '"Times New Roman", 宋体, sans-serif'
                        }
                    };
                    var memory_json = getSplineData($scope.dateArray, series, title, '延迟');
                    Highcharts.chart('memory_container', memory_json);
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

        if (sel.name.indexOf("Bw") == 0){
            $scope.cpuFlag = false;
            var series = [
                {name:'带宽利用率', data:$scope.bwUtilArray},
                {name:'阈值上限', data:$scope.bwThreUp},
                {name:'阈值下限', data:$scope.bwThreDown}
            ];
            var title = {
                text:'带宽利用率走势图',
                style:{
                    color:'white',
                    fontFamily: '"Times New Roman", 宋体, sans-serif'
                }
            };
            var cpu_json = getSplineData($scope.dateArray, series, title, '利用率%');
            Highcharts.chart('cpu_container', cpu_json);
        }else if (sel.name.indexOf("Delay") == 0){
            $scope.memoryFlag = false;
            var series = [
                {name:'链路延迟', data:$scope.delay},
                {name:'阈值', data:$scope.delayThreshold}
            ];
            var title = {
                text:'链路延迟走势图',
                style:{
                    color:'white',
                    fontFamily: '"Times New Roman", 宋体, sans-serif'
                }
            };
            var memory_json = getSplineData($scope.dateArray, series, title, '延迟');
            Highcharts.chart('memory_container', memory_json);
        }else {
            $scope.initFlag = false;
        }
    };

    $scope.getCpuTimeRange = function () {
        var cpuBeginTime = $("#cpu_begin_time").val();
        var cpuEndTime = $("#cpu_end_time").val();
        $scope.vnfTime = {"sfcId":sfcId, "linkId":linkId, "beginTime":cpuBeginTime, "endTime":cpuEndTime};
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
        $scope.vnfTime = {"sfcId":sfcId, "linkId":linkId, "beginTime":memoryBeginTime, "endTime":memoryEndTime};
        $scope.fetchVnfData($scope.vnfTime, 2);
    };

    $scope.clearMemoryTimeRange = function () {
        $("#memory_begin_time").val("");
        $("#memory_end_time").val("");
        $scope.fetchVnfData(vnfInfo, 2);
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
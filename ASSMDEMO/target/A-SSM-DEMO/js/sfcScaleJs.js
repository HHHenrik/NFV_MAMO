angular.module('uiAccordion', ['ngAnimate', 'ngSanitize', 'ui.bootstrap']);
angular.module('uiAccordion').controller('aCtrl', function ($scope) {
    var url = document.URL;
    var getVal = url.split('?')[1];
    var val = getVal.split('=')[1];
   // var val = 10;

    var canvas = document.getElementById('canvas');
    var stage = new JTopo.Stage(canvas);
    var scene = new JTopo.Scene(stage);

    var sfcNodes = [];
    var serverNodes = [];
    var switchNodes = [];
    var sfcNodesMap = [];
    var serverNodeMap = [];
    var switchNodeMap = [];
    var phyLinks = [];
    var beginNode = null;
    var endNode = null;

    setInterval(function(){
        for (var i = 0; i < $scope.sfcNodeMonitor.length; i++){
            if ($scope.sfcNodeMonitor[i].alarmLevel > 0){
                if(sfcNodesMap[i].alarm != null){
                    sfcNodesMap[i].alarm = null;
                }else{
                    sfcNodesMap[i].alarm = $scope.sfcNodeMonitor[i].alarmLevel + '级告警'
                }
            }
        }
    }, 600);

    $scope.fetchDataList = function () {
        $.ajax({
            url:"dataList.json",
            method:"GET",
            data:{"sfcId":val},
            dataType:"json",
            timeout:10000,
            success:function (data) {
                $scope.selectedSfc = data.sfc;
                $scope.sfcNode = data.sfcNode;
                $scope.sfcLink = data.sfcLink;
                $scope.vnfDeploy = data.vnfDeploy;
                $scope.sfcLinkDeploy = data.sfcLinkDeploy;
                $scope.sfcNodeMonitor = data.sfcNodeMonitor;
                $scope.addSfc();
                $scope.addServer();
                $scope.addSwitch();
                var date = new Date($scope.selectedSfc.createTime);
                $scope.selectedSfc.currentTime = getDateTime(date);
                $scope.$apply();
            },
            error:function () {
                alert("网络连接超时,请重试!");
            }
        });
    };

    $scope.fetchDataList();

    function getDateTime(date) {
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var day = date.getDate();
        var hh = date.getHours();
        var mm = date.getMinutes();
        mm = mm > 9 ? mm : "0" + mm;
        var ss = date.getSeconds();
        ss = ss > 9 ? ss : "0" + ss;
        return year + "-" + month + "-" + day + " " + hh + ":" + mm + ":" + ss;
    }

    $scope.addSfc = function () {
        for (var i = 0; i < $scope.sfcNode.length; i++){
            var node = new JTopo.Node($scope.sfcNode[i].vnfdId.toString());
            node.setImage('../images/zhuji1.png');
            node.font="12px Times New Roman";
            node.fontColor='black';
            if ($scope.sfcNodeMonitor[i].alarmLevel > 0){
                node.alarm = $scope.sfcNodeMonitor[i].alarmLevel + '级告警';
            }else {
                node.alarm = null;
            }
            scene.add(node);
            sfcNodes.push(node);
            sfcNodesMap[node.text] = node;
        }
        var flag = $scope.sfcLink[0].flag;
        var dist = 1;
        var xGap = 200;
        // var xGap = 1200;
        var yGap = 20;
        for (i = 0; i < $scope.sfcLink.length; i++){
            var from = $scope.sfcLink[i].from;
            var to = $scope.sfcLink[i].to;
            beginNode = sfcNodes[from];
            endNode = sfcNodes[to];
            if (i == 0)
                sfcNodes[from].setLocation(xGap, yGap);
            // sfcNodes[from].setLocation(xGap, yGap);
            if(flag == $scope.sfcLink[i].flag){
                sfcNodes[to].setLocation(xGap + dist * 150, yGap);
                // sfcNodes[to].setLocation(xGap, yGap + dist * 150);
                dist++;
            }else {
                dist = 1;
                // xGap = 1000;
                // yGap = 30;
                xGap = 250;
                yGap = 80;
                sfcNodes[to].setLocation(xGap + dist * 150, yGap);
                // sfcNodes[to].setLocation(xGap, yGap + dist * 150);
                dist++;
                flag = $scope.sfcLink[i].flag;
            }
            // for (var j = 0; j < sfcNodes.length; j++){
            //     if (sfcNodes[j].text == from)
            //         beginNode = sfcNodes[j];
            //     if(sfcNodes[j].text == to)
            //         endNode = sfcNodes[j];
            // }
            if (beginNode != null && endNode != null){
                var link = new JTopo.Link(beginNode, endNode);
                link.arrowsRadius = 10;
                link.bundleGap = 10;
                scene.add(link);
                beginNode = null; endNode = null;
            }
        }
    };

    $scope.addServer = function () {
        for (var i = 0; i < $scope.vnfDeploy.length; i++){
            var node = new JTopo.Node($scope.vnfDeploy[i].nodeId);
            var x = 180 + i * 150;
            // var y = 20 + i * 80;
            // node.setLocation(700, y);
            node.setLocation(x, 250);
            node.setImage('../images/fuwuqi.png');
            node.font="12px Times New Roman";
            node.fontColor='black';
            scene.add(node);
            serverNodes.push(node);
            serverNodeMap[node.text] = node;
        }
        for (i = 0; i < serverNodes.length; i++){
            beginNode = sfcNodes[$scope.vnfDeploy[i].vnfId];
            endNode = serverNodes[i];
            var link = new JTopo.Link(beginNode, endNode);
            link.bundleGap = 10;
            link.dashedPattern = 5;
            scene.add(link);
            beginNode = null; endNode = null;
        }
    };

    $scope.addSwitch = function () {
        var flag = 0;
        for (var i = 0; i < $scope.sfcLinkDeploy.length; i++){
            var linkArray = $scope.sfcLinkDeploy[i].results.split(',');
            var xGap = 200 + i * 50;
            var yGap = 400 + i * 50;
            // var xGap = 400 - i * 300;
            // var yGap = 20 + i * 10;
            var dist = 0;
            for (var j = 0; j < linkArray.length; j++){
                if(linkArray[j].indexOf("switch") == 0){
                    var flag = 0;
                    for (var k = 0; k < switchNodes.length; k++){
                        if(switchNodes[k].text == linkArray[j])
                            flag = 1;
                    }
                    if (flag == 0){
                        var node = new JTopo.Node(linkArray[j]);
                        node.setLocation(xGap + dist * 150, yGap);
                        // node.setLocation(xGap, yGap + dist * 80);
                        node.setImage('../images/jiaohuanji.png');
                        node.font="12px Times New Roman";
                        node.fontColor='black';
                        scene.add(node);
                        switchNodes.push(node);
                        // switchContainer.add(node);
                        switchNodeMap[node.text] = node;
                        dist++;
                    }
                }
            }
        }
        for (i = 0; i < $scope.sfcLinkDeploy.length; i++){
            linkArray = $scope.sfcLinkDeploy[i].results.split(',');
            for (j = 0; j < linkArray.length - 1; j++){
                var fromNode = null;
                var toNode = null;
                if (linkArray[j].indexOf("switch") == 0){
                    fromNode = switchNodeMap[linkArray[j]];
                }else {
                    fromNode = serverNodeMap[linkArray[j]];
                }
                if(linkArray[j + 1].indexOf("switch") == 0){
                    toNode = switchNodeMap[linkArray[j + 1]];
                }else {
                    toNode = serverNodeMap[linkArray[j + 1]];
                }
                if (fromNode != null && toNode != null){
                    flag = 0;
                    for (k = 0; k < phyLinks.length; k++){
                        if(phyLinks[k].nodeA == fromNode && phyLinks[k].nodeZ == toNode ||
                            phyLinks[k].nodeZ == fromNode && phyLinks[k].nodeA == toNode){flag = 1; break;}
                    }
                    if (flag == 0){
                        var link = new JTopo.Link(fromNode, toNode);
                        link.bundleGap = 10;
                        link.bundleOffset = i * 60;
                        scene.add(link);
                        phyLinks.push(link);
                    }
                }
            }
        }
    };

    $scope.getNode = function (data) {
        if (data.indexOf("switch") == 0){
            var nodeInfo = {
                "code":1,
                "sfcId":val,
                "nodeId":data
            }
        }else if (data.indexOf("server") == 0){
            nodeInfo = {
                "code":2,
                "sfcId":val,
                "nodeId":data
            }
        }else {
            var nodeInfo = {
                "code":3,
                "sfcId":val,
                "nodeId":data
            }
        }
        $.ajax({
            url:"nodeData.json",
            type:"GET",
            data:nodeInfo,
            dataType:"json",
            timeout:10000,
            success:function (data) {
                if (data.code == 1){
                    $scope.selectedSwitch = data.node;
                }else if (data.code == 2){
                    $scope.selectedServer = data.node;
                }else if (data.code == 3){
                    $scope.selectedVnf = data.node;
                }
                $scope.$apply();
            },
            error:function () {
                alert("网络连接超时,请重试!");
            }
        });
    };

    $scope.goToPreHtml = function () {

    }
});
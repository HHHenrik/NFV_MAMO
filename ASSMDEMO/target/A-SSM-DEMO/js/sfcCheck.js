angular.module('uiAccordion', ['ngAnimate', 'ngSanitize', 'ui.bootstrap']);
angular.module('uiAccordion').controller('aCtrl', function ($scope) {
    var url = document.URL;
    var getVal = url.split('?')[1];
    var val = getVal.split('=')[1]; //sfcId

    var canvas = document.getElementById('canvas');
    var stage = new JTopo.Stage(canvas);
    var scene = new JTopo.Scene(stage);
    // var switchContainer = new JTopo.Container();
    // switchContainer.setBackground('../images/01.jpg');
    // switchContainer.height = 500;
    // scene.add(switchContainer);


    var sfcNodes = [];
    var serverNodes = [];
    var switchNodes = [];
    var sfcNodesMap = [];
    var serverNodeMap = [];
    var switchNodeMap = [];
    var phyLinks = [];
    var beginNode = null;
    var endNode = null;




    scene.mouseup(function(e){
        if(e.button == 2){
            if (e.target != null && e.target instanceof JTopo.Node){
                $('#sfc_table').hide();
                var node = e.target;
                if (node.text.indexOf("switch") == 0){
                    $('#vnf_table').hide();
                    $('#server_table').hide();
                    $('#switch_table').show();
                }else if (node.text.indexOf("server") == 0){
                    $('#vnf_table').hide();
                    $('#switch_table').hide();
                    $('#server_table').show();
                }else {
                    $('#switch_table').hide();
                    $('#server_table').hide();
                    $('#vnf_table').show();
                }
                $scope.getNode(node.text);
            }
        }
    });

    // scene.mousemove(function(e){
    //
    // });
    //
    // stage.click(function (event) {
    //
    // });


    /*SFCcheck 画板JS */
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
                $scope.vnfDeploy = data.vnfDeploy; //对于扩缩容时，一个 vnf 可能有多个 vnfDeploy
                $scope.sfcLinkDeploy = data.sfcLinkDeploy;
                $scope.addSfc();
                $scope.addServer();
                $scope.addSwitch();
                var date = new Date($scope.selectedSfc.createTime);
                $scope.selectedSfc.createTime = getDateTime(date);
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
        var ss = date.getSeconds();
        return year + "-" + month + "-" + day + " " + hh + ":" + mm + ":" + ss;
    }

    $scope.addSfc = function () {
        //准备 节点相关信息
        for (var i = 0; i < $scope.sfcNode.length; i++){
            var node = new JTopo.Node($scope.sfcNode[i].vnfdId.toString());
            node.setImage('../images/zhuji1.png');
            node.font="12px Times New Roman";
            node.fontColor='black';
            scene.add(node);
            sfcNodes.push(node);
            sfcNodesMap[node.text] = node;
        }
        var flag = $scope.sfcLink[0].flag; //分支
        var dist = 1;
        var xGap = 200;
        // var xGap = 1200;
        var yGap = 20;
        //画链路和节点
        for (i = 0; i < $scope.sfcLink.length; i++){
            //节点上画布
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

            //链路上画布
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
            //因为扩缩容后VNF有可能对应多个server, 有时会有多个VNF映射到重复的serverId，重复的 serverId将不再画布上出现
            var has_repeate = 0;
            for(var l=0; l<serverNodes.length; l++ ){
                var node = serverNodes[l];
                if(node.text == $scope.vnfDeploy[i].nodeId){
                    //此时发现重复的ServerId，不再画出
                    has_repeate = 1;
                    break;
                }
            }
            if(has_repeate ==1)
                continue;

            //server节点上画布
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


        for (i = 0; i < $scope.vnfDeploy.length; i++){
            //server 和 VNF间的链路
            // (由于扩缩容，所以会导致一个 vnf对应多个 server 的情况,所以 endNode要做相应查找）
            beginNode = sfcNodes[$scope.vnfDeploy[i].vnfId];
            for(var l = 0; l<serverNodes.length; l++ ){
                var node = serverNodes[l];
                if(node.text == $scope.vnfDeploy[i].nodeId){
                    endNode = serverNodes[l];
                    break;
                }
            }
            var link = new JTopo.Link(beginNode, endNode);
            link.bundleGap = 10;
            link.dashedPattern = 5;
            scene.add(link);
            beginNode = null; endNode = null;
        }
    };

    $scope.addSwitch = function () {
        var flag = 0;
        for (var i = 0; i < $scope.sfcLinkDeploy.length; i++){ //按分支上画版
            var linkArray = $scope.sfcLinkDeploy[i].results.split(',');
            var xGap = 200 + i * 10;
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

    $scope.getSfcInfo = function () {
        $('#sfc_table').show();
        $('#vnf_table').hide();
        $('#switch_table').hide();
        $('#server_table').hide();
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
        window.location.href = "../html/sfcManager.html";
    }

    $scope.sfcScaleUp = function() {
        $scope.$broadcast('childOpen','open');
    }
    $scope.sfcScaleDown = function() {
        $scope.$broadcast('childOpen','open');
    }

});




/*----------------------------------------------------------*/

angular.module('uiAccordion').controller('addModalCtrl', function ($scope, $uibModal) {

    $scope.animationsEnabled = true;
    $scope.data = "true";
    $scope.$on('childOpen', function () {
        $scope.open();
    });

    $scope.open = function () {
        var addModalInstance = $uibModal.open({
            animation: $scope.animationsEnabled,
            templateUrl: 'add.html',
            controller: 'addModalInsCtrl',
            controllerAs: '$ctrl'
            // resolve:{
            //     dataArray:function () {
            //         return $scope.dataArray;
            //     }
            // }
        });
        addModalInstance.result.then(function () {
            $scope.fetchAlgorithmList();
        }, function () {

        });
    };
});

angular.module('uiAccordion').controller('addModalInsCtrl', function ($uibModalInstance) {

    var $ctrl = this;
    var url = document.URL;
    var getVal = url.split('?')[1];
    var sfcId = getVal.split('=')[1]; //sfcId
    $ctrl.delayConstraint = null;
    $ctrl.ok = function () {
        $uibModalInstance.close();
        window.location.reload()
    };
    $ctrl.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
    $ctrl.upload = function () {
        if ($ctrl.delayConstraint == null)
            alert("请输入时延限制");
        else {
            var formdata = new FormData();
            formdata.append("sfcId",sfcId);
            formdata.append("delayConstraint", $ctrl.delayConstraint);

            $.ajax({
                url:"sfcScale.json",
                type:"POST",
                dataType:"json",
                data:formdata,
                timeout:10000,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success:function (data) {
                    if(data.AlgorithmStatus == -1){
                        alert("无启用扩缩容算法");
                    } else if(data.AlgorithmStatus == -2){
                        alert("扩缩容算法只适用于单链结构");
                    }
                    else if(data.SFCstatus == -1){
                        alert("SFC中存在不合法VNF,请删除!");
                    } else if (data.terminalFalse == 1){
                        alert("SFC暂时终止失败，无法扩缩容");
                    } else if(data.AlgorithmStatus == '1'){
                        alert("扩容成功!");
                        $ctrl.functionName = null;
                        $ctrl.className = null;
                        $ctrl.delayConstraint = null;
                    }else if(data.code == '2'){
                        alert("缩容成功!");
                    }else if(data.AlgorithmStatus == '-3'){
                        alert("操作失败")
                    }else {
                        alert("未知错误");
                    }
                },
                error:function () {
                    alert("网络连接超时!");
                }
            });
        }
    };
});
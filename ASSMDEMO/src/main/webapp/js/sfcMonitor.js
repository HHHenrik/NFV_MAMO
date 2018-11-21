angular.module('myApp',['treeControl','ngAnimate', 'ngSanitize', 'ui.bootstrap']);
angular.module('myApp').controller('myCtrl', function ($scope) {
    var canvas = document.getElementById('canvas');
    var stage = new JTopo.Stage(canvas);
    var scene = new JTopo.Scene(stage);
    var sfcNodes = [];
    var sfcLinks = [];
    var beginNode = null;
    var endNode = null;

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

    $scope.allSfc = false;
    $scope.sfcFlag = true;
    $scope.sfcNodeFlag = true;
    $scope.sfcLinkFlag = true;
    $scope.vnfFlag = true;
    $scope.linkFlag = true;

    $scope.getSfcList = function () {
        $.ajax({
            url:"sfcMonitor.json",
            type:"GET",
            dataType:"json",
            timeout:10000,
            success:function (data) {
                $scope.groupsTreeModel = JSON.parse(data.sfcArray);
                $scope.total = data.length;
                $scope.data = data.sfcMonitorInfo;
                $scope.values = [{"recordNum": 5},{"recordNum": 6},{"recordNum": 7}];
                $scope.selectedRecNum = $scope.values[0];
                $scope.currentPage=1;
                if($scope.data.length != 0){
                    for(var i = 0; i < $scope.data.length; i++){
                        var date = new Date($scope.data[i].currentTime);
                        $scope.data[i].currentTime = getDateTime(date);
                    }
                    $scope.datas = $scope.data.slice($scope.selectedRecNum.recordNum * ($scope.currentPage - 1), $scope.selectedRecNum.recordNum * $scope.currentPage);
                }else{alert("No Data!");}
                $scope.page={"recordNum":$scope.selectedRecNum.recordNum, "pageSize":5, "pageNo":$scope.currentPage, "totalRecord":$scope.total};
                $scope.$apply();
            },
            error:function () {
                alert("网络连接超时,请重试!");
            }
        });
    };

    $scope.getSfcList();

    $scope.treeLeafClick = function (sel, $parentNode) {
        $scope.selectedNode = sel;
        $scope.allSfc = true;
        $scope.sfcFlag = true;
        $scope.sfcNodeFlag = true;
        $scope.sfcLinkFlag = true;
        $scope.vnfFlag = true;
        $scope.linkFlag = true;
        if (sel.name == "SFC"){
            $scope.allSfc = false;
            // $scope.sfcFlag = true;
            // $scope.sfcNodeFlag = true;
        }
        // else if (sel.name.indexOf("vnfId") == 0){
        //     $scope.sfcFlag = false;
        //     $scope.vnfFlag = false;
        //     var vnfId = sel.name.split(":")[1];
        //     var sfcName = $parentNode.name.split("_")[0];
        //     if (sfcName != $scope.sfcData.sfcName){
        //         scene.clear();
        //         sfcNodes = [];
        //         sfcLinks = [];
        //         beginNode = null;
        //         endNode = null;
        //         getSfcMonitorData(sfcName, vnfId, -1);
        //     }
        //     $scope.selectedVnf = $scope.vnfNode[vnfId];
        //     for (var i = 0; i < sfcNodes.length; i++){
        //         if (sfcNodes[i].text == vnfId){
        //             sfcNodes[i].borderWidth=2;
        //             sfcNodes[i].borderColor="255,255,255";
        //         }else {
        //             sfcNodes[i].borderWidth=0;
        //         }
        //     }
        //     for (i = 0; i < sfcLinks.length; i++){
        //         sfcLinks[i].strokeColor = "0,191,255";
        //         sfcLinks[i].lineWidth = 2;
        //     }
        // }else if (sel.name.indexOf("linkId") == 0){
        //     $scope.sfcFlag = false;
        //     $scope.linkFlag = false;
        //     var linkId = sel.name.split(":")[1];
        //     sfcName = $parentNode.name.split("_")[0];
        //     if (sfcName != $scope.sfcData.sfcName){
        //         scene.clear();
        //         sfcNodes = [];
        //         sfcLinks = [];
        //         beginNode = null;
        //         endNode = null;
        //         getSfcMonitorData(sfcName, -1, linkId);
        //     }
        //     for (i = 0; i < $scope.sfcLink.length; i++){
        //         if ($scope.sfcLink[i].linkId == linkId){
        //             $scope.selectedLink = $scope.sfcLink[i];
        //             var from = $scope.sfcLink[i].from;
        //             var to = $scope.sfcLink[i].to;
        //             break;
        //         }
        //     }
        //
        //     for (i = 0; i < sfcLinks.length; i++){
        //         if (sfcLinks[i].nodeA.text == from && sfcLinks[i].nodeZ.text == to){
        //             sfcLinks[i].strokeColor = "0,255,0";
        //             sfcLinks[i].lineWidth = 3;
        //         }else {
        //             sfcLinks[i].strokeColor = "0,191,255";
        //             sfcLinks[i].lineWidth = 2;
        //         }
        //     }
        //     for (i = 0; i < sfcNodes.length; i++){
        //         sfcNodes[i].borderWidth=0;
        //     }
        // }
        else if (sel.name.lastIndexOf("node") >= 0){
            $scope.sfcNodeFlag = false;
            // $scope.allSfc = true;
            // $scope.sfcFlag = true;
            // $scope.sfcLinkFlag = true;
            // scene.clear();
            // sfcNodes = [];
            // beginNode = null;
            // endNode = null;
            $.ajax({
                url:"sfcNodeMonitorData.json",
                method:"GET",
                data:{"sfcName":$parentNode.name},
                dataType:"json",
                timeout:10000,
                success:function (data) {
                    $scope.sfcNodeData = data.sfcNodeData;
                    for(var i = 0; i < $scope.sfcNodeData.length; i++){
                        var date = new Date($scope.sfcNodeData[i].currentTime);
                        $scope.sfcNodeData[i].currentTime = getDateTime(date);
                    }
                    $scope.$apply();
                },
                error:function () {
                    alert("网络连接超时!");
                }
            });
        }else if (sel.name.lastIndexOf("link") >= 0){
            $scope.sfcLinkFlag = false;
            $.ajax({
                url:"sfcLinkMonitorData.json",
                method:"GET",
                data:{"sfcName":$parentNode.name},
                dataType:"json",
                timeout:10000,
                success:function (data) {
                    $scope.sfcLinkData = data.sfcLinkData;
                    for(var i = 0; i < $scope.sfcLinkData.length; i++){
                        var date = new Date($scope.sfcLinkData[i].currentTime);
                        $scope.sfcLinkData[i].currentTime = getDateTime(date);
                    }
                    $scope.$apply();
                },
                error:function () {
                    alert("网络连接超时!");
                }
            });
        }else if (sel.name.indexOf("sfc") == 0){
            $scope.sfcFlag = false;
            // $scope.allSfc = true;
            // $scope.sfcNodeFlag = true;
            // $scope.sfcLinkFlag = true;
            scene.clear();
            sfcNodes = [];
            sfcLinks = [];
            beginNode = null;
            endNode = null;
            getSfcMonitorData(sel.name);
        }
    };

    $scope.addSfc = function () {
        for (var i = 0; i < $scope.sfcNode.length; i++){
            var node = new JTopo.Node($scope.sfcNode[i].vnfdId.toString());
            node.setImage('../images/zhuji1.png');
            node.font="12px Times New Roman";
            node.fontColor="255, 255, 255";
            scene.add(node);
            sfcNodes.push(node);
        }
        var flag = $scope.sfcLink[0].flag;
        var dist = 1;
        var xGap = 200;
        var yGap = 200;
        for (i = 0; i < $scope.sfcLink.length; i++){
            var from = $scope.sfcLink[i].from;
            var to = $scope.sfcLink[i].to;
            beginNode = sfcNodes[from];
            endNode = sfcNodes[to];
            if (i == 0)
                sfcNodes[from].setLocation(xGap, yGap);
            if(flag == $scope.sfcLink[i].flag){
                sfcNodes[to].setLocation(xGap + dist * 150, yGap);
                dist++;
            }else {
                dist = 1;
                xGap = 250;
                yGap = 280;
                sfcNodes[to].setLocation(xGap + dist * 150, yGap);
                dist++;
                flag = $scope.sfcLink[i].flag;
            }
            if (beginNode != null && endNode != null){
                var link = new JTopo.Link(beginNode, endNode);
                link.arrowsRadius = 10;
                link.bundleGap = 10;
                link.strokeColor = "0,191,255";
                link.lineWidth = 2;
                scene.add(link);
                sfcLinks.push(link);
                beginNode = null; endNode = null;
            }
        }
    };

    $scope.goToNodeMonitor = function ($index) {
        var sfcId = $scope.sfcNodeData[$index].sfcId;
        var vnfId = $scope.sfcNodeData[$index].vnfId;
        var url = "sfcNodeMonitor.html?sfcId=" + sfcId + "&vnfId=" + vnfId;
        window.location.href = url;
    };

    $scope.goToLinkMonitor = function ($index) {
        var sfcId = $scope.sfcLinkData[$index].sfcId;
        var linkId = $scope.sfcLinkData[$index].linkId;
        var url = "sfcLinkMonitor.html?sfcId=" + sfcId + "&linkId=" + linkId;
        window.location.href = url;
    };

    $scope.goToSfcMonitor = function ($index) {
        var sfcId = $scope.datas[$index].sfcId;
        var url = "sfcScale.html?sfcId=" + sfcId;
        window.location.href = url;
    };

    scene.mouseup(function(e){
        if(e.button == 0){
            if (e.target != null && e.target instanceof JTopo.Node){
                $scope.vnfFlag = false;
                $scope.linkFlag = true;
                var node = e.target;
                var vnfId = node.text;
                $scope.selectedVnf = $scope.vnfNode[vnfId];
                for (var i = 0; i < sfcNodes.length; i++){
                    if (sfcNodes[i].text == vnfId){
                        sfcNodes[i].borderWidth=2;
                        sfcNodes[i].borderColor="255,255,255";
                    }else {
                        sfcNodes[i].borderWidth=0;
                    }
                }
                for (i = 0; i < sfcLinks.length; i++){
                    sfcLinks[i].strokeColor = "0,191,255";
                    sfcLinks[i].lineWidth = 2;
                }
            }else if (e.target != null && e.target instanceof JTopo.Link){
                $scope.linkFlag = false;
                $scope.vnfFlag = true;
                var link = e.target;
                for (i = 0; i < sfcLinks.length; i++){
                    if (sfcLinks[i].nodeA == link.nodeA && sfcLinks[i].nodeZ == link.nodeZ){
                        sfcLinks[i].strokeColor = "0,255,0";
                        sfcLinks[i].lineWidth = 3;
                        $scope.selectedLink = $scope.sfcLink[i];
                    }else {
                        sfcLinks[i].strokeColor = "0,191,255";
                        sfcLinks[i].lineWidth = 2;
                    }
                }
                for (i = 0; i < sfcNodes.length; i++){
                    sfcNodes[i].borderWidth=0;
                }
            }else {
                for (i = 0; i < sfcNodes.length; i++){
                    sfcNodes[i].borderWidth=0;
                }
                for (i = 0; i < sfcLinks.length; i++){
                    sfcLinks[i].strokeColor = "0,191,255";
                    sfcLinks[i].lineWidth = 2;
                }
                $scope.vnfFlag = true;
                $scope.linkFlag = true;
            }
        }
        $scope.$apply();
    });

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

    function getSfcMonitorData(sfcName){
        $.ajax({
            url:"sfcMonitorData.json",
            method:"GET",
            data:{"sfcName":sfcName},
            dataType:"json",
            timeout:10000,
            success:function (data) {
                $scope.sfcData = data.sfcMonitorCustom;
                $scope.sfcNode = data.sfcNode;
                $scope.sfcLink = data.sfcLink;
                $scope.vnfNode = data.vnfNode;
                var date = new Date($scope.sfcData.currentTime);
                $scope.sfcData.currentTime = getDateTime(date);
                $scope.addSfc();
                // if (vnfId != -1){
                //     $scope.selectedVnf = $scope.vnfNode[vnfId];
                //     for (var i = 0; i < sfcNodes.length; i++){
                //         if (sfcNodes[i].text == vnfId){
                //             sfcNodes[i].borderWidth=2;
                //             sfcNodes[i].borderColor="255,255,255";
                //         }else {
                //             sfcNodes[i].borderWidth=0;
                //         }
                //     }
                //     for (i = 0; i < sfcLinks.length; i++){
                //         sfcLinks[i].strokeColor = "0,191,255";
                //         sfcLinks[i].lineWidth = 2;
                //     }
                // }
                // if (linkId != -1){
                //     for (i = 0; i < $scope.sfcLink.length; i++){
                //         if ($scope.sfcLink[i].linkId == linkId){
                //             $scope.selectedLink = $scope.sfcLink[i];
                //             var from = $scope.sfcLink[i].from;
                //             var to = $scope.sfcLink[i].to;
                //             break;
                //         }
                //     }
                //
                //     for (i = 0; i < sfcLinks.length; i++){
                //         if (sfcLinks[i].nodeA.text == from && sfcLinks[i].nodeZ.text == to){
                //             sfcLinks[i].strokeColor = "0,255,0";
                //             sfcLinks[i].lineWidth = 3;
                //         }else {
                //             sfcLinks[i].strokeColor = "0,191,255";
                //             sfcLinks[i].lineWidth = 2;
                //         }
                //     }
                //     for (i = 0; i < sfcNodes.length; i++){
                //         sfcNodes[i].borderWidth=0;
                //     }
                // }
                $scope.$apply();
            },
            error:function () {
                alert("网络连接超时!");
            }
        });
    }
});
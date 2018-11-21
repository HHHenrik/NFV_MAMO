angular.module('uiAccordion', ['ngAnimate', 'ngSanitize', 'ui.bootstrap']);
angular.module('uiAccordion').controller('aCtrl', function ($scope, $http, $uibModal) {
    $scope.fetchVnfList = function () {
        var user = {"userName":"admin"};
        $.ajax({
            url:"vnfList.json",
            method:"GET",
            data:user,
            dataType:"json",
            timeout:10000,
            success:function (data) {
                $scope.groups = data;
                $scope.$apply();
            },
            error:function () {
                alert("网络连接超时,请重试!");
            }
        });
    };
    $scope.fetchVnfList();

    var flag = 0;
    var linkFlag = 0;
    var currentNode = null;
    var currentLink = null;

    // var map = {};
    var sfcNodes = [];
    var sfcLinks = [];
    var usedNode = [];

    var canvas = document.getElementById('canvas');
    var stage = new JTopo.Stage(canvas);
    var scene = new JTopo.Scene(stage);
    var beginNode = null;
    var endNode = null;
    var tempNodeA = new JTopo.Node('');
    tempNodeA.setSize(1, 1);
    var tempNodeZ = new JTopo.Node('');
    tempNodeZ.setSize(1, 1);
    var link = new JTopo.Link(tempNodeA, tempNodeZ);
    link.arrowsRadius = 10;
    scene.dbclick(function (e) {
        if(e.target != null && e.target instanceof JTopo.Node){
            if(beginNode == null){
                beginNode = e.target;
                scene.add(link);
                tempNodeA.setLocation(e.x, e.y);
                tempNodeZ.setLocation(e.x, e.y);
            }else if(beginNode !== e.target){
                endNode = e.target;
                var l = new JTopo.Link(beginNode, endNode);
                l.arrowsRadius = 10;
                l.bundleGap = 10;
                scene.add(l);
                var fromName = null, toName = null;
                for(var i = 0; i < sfcNodes.length; i++){
                    if(fromName != null && toName != null) break;
                    if(sfcNodes[i].node == beginNode)
                        fromName = sfcNodes[i].vnf.vnfName;
                    if(sfcNodes[i].node == endNode)
                        toName = sfcNodes[i].vnf.vnfName;
                }
                $scope.selectedLink = {
                    "fromVnf":fromName,
                    "toVnf":toName
                };
                $scope.$apply();
                $('#left_config_panel').slideDown(300);
                flag = 1;
                // beginNode = null;
                scene.remove(link);
                l.addEventListener('mouseup', function (event) {
                    currentLink = this;
                    linkHandler(event);
                })
            }else{

            }
        }else{
            scene.remove(link);
        }
    });
    scene.mouseup(function(e){
        if(e.button == 2){
            scene.remove(link);
            beginNode = null;
            return;
        }
        if (linkFlag == 1){
            $('#left_config_panel').slideUp(300);
            linkFlag = 0;
            $scope.bandwidth = null;
            $scope.delay = null;
            $scope.branchNum = null;
            $scope.selectedLink = null;
            // $scope.$apply();
        }
        // else if(e.button == 0){
        //     $('#left_config_panel').slideUp(300);
        // }
    });

    scene.mousemove(function(e){
        tempNodeZ.setLocation(e.x, e.y);
    });

    function nodeHandler(event) {
        if(event.button == 2){
            if(flag == 1){
                alert("请填写链路信息!");
            }else {
                $('#node_context_menu').css({
                    top:event.pageY,
                    left:event.pageX
                }).show();
            }
        }
    }

    $('#node_context_menu a').click(function () {
        var text = $(this).text();
        if(text == "删除"){
            scene.remove(currentNode);
            for(var i = 0; i < sfcNodes.length; i++){
                var node = sfcNodes[i].node;
                if(node == currentNode){
                    sfcNodes.splice(i, 1);
                    break;
                }
            }
            for (i = 0; i < usedNode.length; i++){
                var node = usedNode[i];
                if(node == currentNode){
                    usedNode.splice(i, 1);
                    break;
                }

            }
            currentNode = null;
        }
        $('#node_context_menu').hide();
    });

    function linkHandler(event) {
        if(event.button == 2){
            if(flag == 1){
                alert("请填写链路信息!");
            }else {
                $('#link_context_menu').css({
                    top:event.pageY,
                    left:event.pageX
                }).show();
            }
        }
    }

    $('#link_context_menu a').click(function () {
        var text = $(this).text();
        if(text == "删除"){
            scene.remove(currentLink);
            for (var i = 0; i < sfcLinks.length; i++){
                if(sfcLinks[i].fromNode == currentLink.nodeA && sfcLinks[i].toNode == currentLink.nodeZ){
                    sfcLinks.splice(i, 1);
                    break;
                }
            }
        } else{
            linkFlag = 1;
            var fromName = null, toName = null;
            for(i = 0; i < sfcNodes.length; i++){
                if(fromName != null && toName != null) break;
                if(sfcNodes[i].node == currentLink.nodeA)
                    fromName = sfcNodes[i].vnf.vnfName;
                if(sfcNodes[i].node == currentLink.nodeZ)
                    toName = sfcNodes[i].vnf.vnfName;
            }
            for(i = 0; i < sfcLinks.length; i++){
                if(sfcLinks[i].fromNode == currentLink.nodeA && sfcLinks[i].toNode == currentLink.nodeZ){
                    $scope.branchNum = sfcLinks[i].branchNum;
                    $scope.bandwidth = sfcLinks[i].bandwidth;
                    $scope.delay = sfcLinks[i].delay;
                }
            }
            $scope.selectedLink = {
                "fromVnf":fromName,
                "toVnf":toName
            };
            $scope.$apply();
            $('#left_config_panel').slideDown(300);
        }
        currentLink = null;
        $('#link_context_menu').hide();
    });

    stage.click(function (event) {
        if(flag == 1){
            alert("请填写链路信息!");
        }else {
            if(event.button == 0){
                $('#node_context_menu').hide();
                $('#link_context_menu').hide();
            }
        }
    });

    $scope.addToCanvas = function (data) {
        //alert("aabc");
        if(flag == 1){
            alert("请填写链路信息!");
        }else {
            var index = data.index;
            var x = data.x;
            var y = data.y;
            var node = new JTopo.Node($scope.groups[index].vnfName);
            node.setLocation(x, y);
            node.setImage('../images/zhuji1.png');
            node.font="12px Times New Roman";
            node.fontColor='black';
            scene.add(node);
            //在javascript语言中，key的值只能是字符串，不能是其它。
            // map[node.text] = $scope.groups[$index];
            var tem = {
                "node":node,
                "vnf":$scope.groups[index]
            };
            sfcNodes.push(tem);
            node.addEventListener('mouseup', function (event) {
                currentNode = this;
                nodeHandler(event);
            })
        }
    };
    $scope.canvas_clear = function () {
        $('#left_config_panel').slideUp(300);
        sfcNodes = [];
        sfcLinks = [];
        usedNode = [];
        scene.clear();
        flag = 0;
        $scope.selectedLink = null;
    };

    $scope.sfc_save = function () {
        // alert(usedNode.length + " " + sfcNodes.length);
        if(flag == 1) {
            alert("请填写链路信息!");
        }else if(usedNode.length != sfcNodes.length){
            alert("SFC不完整,请保证SFC的完整性!");
        }else {
            var addModalInstance = $uibModal.open({
                animation:true,
                templateUrl:'saveSfc.html',
                controller:'addModalInsCtrl',
                controllerAs:'$ctrl'
                // resolve:{
                //     dataArray:function () {
                //         return $scope.dataArray;
                //     }
                // }
            });
            addModalInstance.result.then(function (sfcInfo) {
                $scope.sfcName = sfcInfo.sfcName;
                $scope.userName = sfcInfo.userName;
                var nodeMap = [];
                var linkMap = [];
                for(var i = 0; i < sfcNodes.length; i++){
                    var node = {
                        "vnfdId":i,
                        "vnfd":sfcNodes[i].vnf.vnfd,
                        "vnfName":sfcNodes[i].vnf.vnfName
                    };
                    nodeMap.push(node);
                }
                var index = 0;
                for(i = 0; i < sfcLinks.length; i++){
                    var from = -1, to = -1;
                    for(var j = 0; j < sfcNodes.length; j++){
                        if(from != -1 && to != -1) break;
                        if(sfcNodes[j].node == sfcLinks[i].fromNode)
                            from = j;
                        if(sfcNodes[j].node == sfcLinks[i].toNode)
                            to = j;
                    }
                    var link = {
                        "fromVnf":sfcNodes[from].vnf.vnfName,
                        "toVnf":sfcNodes[to].vnf.vnfName,
                        "from":from,
                        "to":to,
                        "branchNum":sfcLinks[i].branchNum,
                        "bandwidth":sfcLinks[i].bandwidth,
                        "delay":sfcLinks[i].delay,
                        "linkId":index
                    };
                    linkMap.push(link);
                    index++;
                }
                var sfcData = {
                    "sfcName":$scope.sfcName,
                    "userName":$scope.userName,
                    "status":"stateless",
                    "sfcNodes":JSON.stringify(nodeMap),
                    "sfcLinks":JSON.stringify(linkMap)
                };

                $.ajax({
                    url:"saveSfc",
                    type:"POST",
                    data:sfcData,
                    dataType:"json",
                    timeout:10000,
                    success:function () {
                        alert("保存成功!");
                    },
                    error:function () {
                        alert("网络连接超时,请重试!");
                    }
                });
                scene.clear();
                sfcLinks = [];
                sfcNodes = [];
                usedNode = [];
                flag = 0;
                $scope.selectedLink = null;
            },function () {

            });
        }
    };

    $scope.linkSave = function () {
        if($scope.bandwidth == null || $scope.delay == null || $scope.branchNum == null)
            alert("请填入链路信息!");
        else {
            $('#left_config_panel').slideUp(300);
            flag = 0;
            var link = {
                "fromNode":beginNode,
                "toNode":endNode,
                // "linkName":$scope.linkName,
                "bandwidth":$scope.bandwidth,
                "delay":$scope.delay,
                "branchNum":$scope.branchNum
            };
            if (linkFlag == 0){
                sfcLinks.push(link);
                if(usedNode.length == 0){
                    usedNode.push(beginNode);
                    usedNode.push(endNode);
                }else {
                    for(var i = 0; i < usedNode.length; i++){
                        if(usedNode[i] == beginNode)
                            beginNode = null;
                        if(usedNode[i] == endNode)
                            endNode = null;
                    }
                    if(beginNode != null) usedNode.push(beginNode);
                    if(endNode != null) usedNode.push(endNode);
                }
            }
            linkFlag = 0;

            // $scope.linkName = null;
            $scope.bandwidth = null;
            $scope.delay = null;
            $scope.branchNum = null;
            beginNode = null;
            endNode = null;
            $scope.selectedLink = null;
        }
    };

    $scope.linkRest = function () {
        // $scope.linkName = null;
        $scope.bandwidth = null;
        $scope.delay = null;
        $scope.branchNum = null;
    };

    $scope.canvas_logout = function () {
        window.location.href = "../html/sfcManager.html";
    }
});

angular.module('uiAccordion').controller('addModalInsCtrl', function ($uibModalInstance) {
    var $ctrl = this;
    $ctrl.sfcName = null;
    $ctrl.userName = null;

    $ctrl.ok = function (sfcName, userName) {
        if($ctrl.sfcName == null || $ctrl.userName == null){
            alert("请填写完整信息!");
        }else {
            $ctrl.sfcInfo = {
                "sfcName":sfcName,
                "userName":userName
            };
            $uibModalInstance.close($ctrl.sfcInfo);
        }
    };
    $ctrl.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});
function drag(ev) {
    ev.dataTransfer.setData("Text",ev.target.name);
}

function allowDrop(ev)
{
    ev.preventDefault();
}
function drop(ev){
    ev.preventDefault();
    ev.stopPropagation();
    var data = ev.dataTransfer.getData("Text");
    var handPosition = getMousePos(ev);
    // alert("x: " + handPosition.x + " y: " + handPosition.y);
    var info = {
        "index":data,
        "x":handPosition.x,
        "y":handPosition.y
    };
    var appElement = document.querySelector('[ng-controller=aCtrl]');
    // var controller = element.controller();
    var $scope = angular.element(appElement).scope();
    $scope.addToCanvas(info);
    $scope.$apply();
}

function getMousePos(event) {
    var e = event || window.event;
    // var scrollX = document.documentElement.scrollLeft || document.body.scrollLeft;
    // var scrollY = document.documentElement.scrollTop || document.body.scrollTop;
    var x = e.clientX + document.documentElement.scrollLeft - document.body.scrollLeft;
    var y = e.clientY + document.documentElement.scrollTop - document.body.scrollTop;
    var ele = document.getElementById("canvas");
    x = x - ele.offsetLeft; y = y - ele.offsetTop;
    return { 'x': x, 'y': y };
}
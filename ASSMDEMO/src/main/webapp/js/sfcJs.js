angular.module('myApp',['ngAnimate', 'ngSanitize', 'ui.bootstrap']);
angular.module('myApp').controller('myCtrl', function ($scope) {
    $scope.fetchSfcList = function () {
        var userInfo = {"userName":""};
        $.ajax({
            url:"sfcList.json",
            type:"GET",
            data:userInfo,
            dataType:"json",
            timeout:10000,
            success:function (data) {
                $scope.total = data.length;
                $scope.data = data;
                $scope.values = [{"recordNum": 5},{"recordNum": 6},{"recordNum": 7}];
                $scope.selectedRecNum = $scope.values[0];
                $scope.currentPage=1;
                if($scope.data.length != 0){
                    for(var i = 0; i < $scope.data.length; i++){
                        var date = new Date($scope.data[i].createTime);
                        $scope.data[i].createTime = getDateTime(date);
                    }
                    $scope.datas = $scope.data.slice($scope.selectedRecNum.recordNum * ($scope.currentPage - 1), $scope.selectedRecNum.recordNum * $scope.currentPage);
                }else{alert("No Data!");}
                $scope.page={"recordNum":$scope.selectedRecNum.recordNum, "pageSize":5, "pageNo":$scope.currentPage, "totalRecord":$scope.total};
                $scope.$apply();
            },
            error:function () {
                alert("网络连接超时!");
            }
        });
    };
    
    $scope.fetchSfcList();

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
    
    $scope.deploy = function () {
        var checkbox = document.getElementsByName("checkbox");
        var sfcId;
        var count = 0;
        var sfcStatus = "";
        for(var i = checkbox.length - 1; i >= 0; i--){
            if(checkbox[i].checked == true){
                sfcId = $scope.datas[i].sfcId;
                count++;
                sfcStatus = $scope.datas[i].status;
            }
        }
        if(count == 0)
            alert("请选择数据!");
        else if(count > 1){
            alert("每次同时只能部署一个!");
            $scope.selectInverse();
        }else if (sfcStatus == "working"){
            alert("该SFC已处于工作状态!");
        }else if (sfcStatus == "hanging"){
            alert("该SFC已经部署!");
        }else{
            var sfcInfo = {"sfcId":sfcId};
            $.ajax({
                url:"deploySfc",
                type:"POST",
                traditional:true,
                data:sfcInfo,
                timeout:30000,
                success:function (data) {
                    if(data.status == -1){
                        alert("SFC中存在不合法VNF,请删除!");
                    }else if(data.status == 2){
                        alert("未有启用的部署算法,请先启用部署算法!");
                    }else if(data.status == 1){
                        alert("部署成功!");
                        $scope.fetchSfcList();
                    }else {alert("部署失败!");}
                    for(var i = checkbox.length - 1; i >= 0; i--){
                        if(checkbox[i].checked == true)
                            checkbox[i].checked = false;
                        $scope.$apply();
                    }
                },
                error:function () {
                    alert("网络连接超时,请重试!");
                }
            });
        }
    };
    $scope.changeData = function (selectedRecNum) {
        $scope.page.recordNum = selectedRecNum.recordNum;
        $scope.datas = $scope.data.slice($scope.selectedRecNum.recordNum * ($scope.page.pageNo - 1), $scope.selectedRecNum.recordNum * $scope.page.pageNo);
    };
    $scope.changePage = function () {
        $scope.page.recordNum = $scope.selectedRecNum.recordNum;
        $scope.datas = $scope.data.slice($scope.selectedRecNum.recordNum * ($scope.page.pageNo - 1), $scope.selectedRecNum.recordNum * $scope.page.pageNo);
    };
    function searchByKey() {
        var searchKey = $('#search_key').val();
        if(searchKey != ""){
            $scope.page.recordNum = $scope.selectedRecNum.recordNum;
            var j = 0;
            var array = [];
            for(var i = 0; i < $scope.data.length; i++){
                if($scope.data[i].name == searchKey || $scope.data[i].create_time == searchKey ||
                    $scope.data[i].sfc_identity == searchKey){
                    array[j] = $scope.data[i];
                    j++;
                }
            }
            $scope.datas = array;
            $scope.page.totalRecord = $scope.datas.length;
        }else{
            alert("请输入查找关键字!");
        }
    }
    $scope.search = function () {
        searchByKey();
    };
    $scope.enterKeyUp = function (e) {
        var keycode = window.event?e.keyCode:e.which;
        if(keycode == 13){
            searchByKey();
        }
    };
    $scope.searChange = function () {
        var searchKey = $('#search_key').val();
        if(searchKey == ""){
            $scope.page.recordNum = $scope.selectedRecNum.recordNum;
            $scope.datas = $scope.data.slice($scope.selectedRecNum.recordNum * ($scope.currentPage - 1), $scope.selectedRecNum.recordNum * $scope.currentPage);
            $scope.page.totalRecord = $scope.total;
        }
    };
    $scope.delSingle =function ($index) {
        if($scope.datas[$index].status == "working"){
            alert("该SFC处于工作状态,请终止后在删除!");
        }else {
            if(confirm("是否删除" + $scope.datas[$index].sfcName)){
                var sfcIdArray= [];
                sfcIdArray.push($scope.datas[$index].sfcId);
                $.ajax({
                    url:"deleteSfc",
                    type:"POST",
                    traditional:true,
                    data:{sfcIdArray:sfcIdArray},
                    dataType:"json",
                    timeout:10000,
                    success:function () {
                        for(var i = 0; i < $scope.data.length; i++){
                            if($scope.data[i].sfcId == $scope.datas[$index].sfcId){
                                $scope.data.splice(i, 1);
                                break;
                            }
                        }
                        $scope.datas = $scope.data.slice($scope.selectedRecNum.recordNum * ($scope.page.pageNo - 1), $scope.selectedRecNum.recordNum * $scope.page.pageNo);
                        $scope.page.totalRecord = $scope.data.length;
                        $scope.$apply();
                    },
                    error:function () {
                        alert("网络连接超时,请重试!");
                    }
                });
            }
        }
    };
    $scope.selectAll = function () {
        var checkbox = document.getElementsByName("checkbox");
        for(var i = 0; i < checkbox.length; i++){
            checkbox[i].checked = true;
        }
    };
    $scope.selectInverse = function () {
        var checkbox = document.getElementsByName("checkbox");
        for(var i = 0; i < checkbox.length; i++){
            checkbox[i].checked = false;
        }
    };
    $scope.delQuery = function () {
        var checkbox = document.getElementsByName("checkbox");
        var sfcIdArray = [];
        var unDeleteSfc = "";
        for(var i = checkbox.length - 1; i >= 0; i--){
            if((checkbox[i].checked == true && $scope.datas[i].status == "working") ||
                (checkbox[i].checked == true && $scope.datas[i].status == "hanging")){
                if(unDeleteSfc.length == 0)
                    unDeleteSfc += $scope.datas[i].sfcName;
                else
                    unDeleteSfc += "," + $scope.datas[i].sfcName;
                checkbox[i].checked = false;
            }else if(checkbox[i].checked == true){
                sfcIdArray.push($scope.datas[i].sfcId);
                checkbox[i].checked = false;
            }
        }
        if (sfcIdArray.length == 0){
            if (unDeleteSfc.length != 0){
                unDeleteSfc += "处于工作状态,未删除";
                alert(unDeleteSfc);
            }else {
                alert("请选择数据!");
            }
        }else {
            $.ajax({
                url:"deleteSfc",
                type:"POST",
                traditional:true,
                data:{sfcIdArray:sfcIdArray},
                dataType:"json",
                timeout:10000,
                success:function () {
                    if (unDeleteSfc.length != 0)
                        unDeleteSfc += "处于工作状态,未删除,其余SFC";
                    alert(unDeleteSfc + "删除成功!");
                    $scope.fetchSfcList();
                    // for(var i = checkbox.length - 1; i >= 0; i--){
                    //     if(checkbox[i].checked == true){
                    //         for (var j = 0; j < $scope.data.length; j++){
                    //             if($scope.data[j].sfcId == $scope.datas[i].sfcId)
                    //                 $scope.data.splice(j, 1);
                    //         }
                    //     }
                    // }
                    // $scope.datas = $scope.data.slice($scope.selectedRecNum.recordNum * ($scope.page.pageNo - 1), $scope.selectedRecNum.recordNum * $scope.page.pageNo);
                    // $scope.page.totalRecord = $scope.data.length;
                    // $scope.$apply();
                },
                error:function () {
                    alert("网络连接超时,请重试!");
                }
            });
        }
    };

    $scope.hangUp = function () {
        postToBack("hangUpSfc");
    };

    $scope.wakeUp = function () {
        postToBack("wakeUpSfc");
    };

    $scope.terminationSfc = function () {
        postToBack("terminationSfc");
    };

    function postToBack(address) {
        var checkbox = document.getElementsByName("checkbox");
        var sfcId;
        var count = 0;
        for (var i = 0; i < checkbox.length; i++){
            if(checkbox[i].checked == true){
                sfcId = $scope.datas[i].sfcId;
                checkbox[i].checked = false;
                count++;
            }
        }
        if(count == 0) alert("请选择数据!");
        else if(count > 1) alert("每次操作一条SFC!");
        else {
            var sfcInfo = {"sfcId": sfcId};
            $.ajax({
                url: address,
                type: "POST",
                data: sfcInfo,
                dataType: "json",
                timeout: 10000,
                success: function (data) {
                    if (address == "hangUpSfc") {
                        if (data == 1) {
                            alert("挂起成功!");
                            $scope.fetchSfcList();
                        } else if (data == 2) {
                            alert("该SFC已处于挂起状态!");
                        } else if (data == 3) {
                            alert("该SFC未部署，无法挂起!");
                        } else {
                            alert("挂起失败!");
                        }
                    }
                    else if (address == "wakeUpSfc") {
                        if (data == 1) {
                            alert("唤醒成功!");
                            $scope.fetchSfcList();
                        } else if (data == 2) {
                            alert("该SFC已处于工作状态!");
                        } else if (data == 3) {
                            alert("该SFC未部署，无法唤醒!");
                        } else {
                            alert("唤醒失败!");
                        }
                    }
                    else if (address == "terminationSfc") {
                        if (data == 1) {
                            alert("终止成功!");
                            $scope.fetchSfcList();
                        } else if (data == 2) {
                            alert("该SFC已处于终止状态!");
                        } else {
                            alert("终止失败!");
                        }
                    }
                },
                error: function () {
                    alert("网络连接超时,请重试!");
                }
            });
        }
    }
    
    $scope.checkSfc = function ($index) {
        var sfcId = $scope.datas[$index].sfcId;
        var url = "sfcCheck.html?sfcId="+sfcId;
        window.location.href = url;
    }
});
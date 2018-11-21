angular.module('myApp',['ngAnimate', 'ngSanitize', 'ui.bootstrap']);
angular.module('myApp').controller('myCtrl', function ($scope) {
    $scope.fetchAlgorithmList = function () {
        $.ajax({
            url:"algorithmList.json",
            type:"GET",
            dataType:"json",
            timeout:10000,
            success:function (data) {
                $scope.total = data.length;
                $scope.data = data;
                $scope.values = [{"recordNum": 5},{"recordNum": 6},{"recordNum": 7},{"recordNum": 8}];
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
    $scope.fetchAlgorithmList();

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
                if($scope.data[i].algName == searchKey || $scope.data[i].createTime == searchKey ||
                    $scope.data[i].status == searchKey || $scope.data[i].function == searchKey){
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
    $scope.startAlg = function ($index) {
        var startAlgInfo = {
            "function":$scope.datas[$index].function,
            "algName":$scope.datas[$index].algName,
            "id":$scope.datas[$index].id
        };
        $.ajax({
            url: "startAlg",
            type: "POST",
            data: startAlgInfo,
            dataType:"json",
            timeout: 10000,
            success: function (data) {
                if(data == 1){
                    alert("启动成功!");
                    for (var i = 0; i < $scope.data.length; i++) {
                        if ($scope.data[i].id == $scope.datas[$index].id) {
                            $scope.data[i].status = "working";
                            $scope.datas[$index].status = "working";
                            break;
                        }
                    }
                    $scope.$apply();
                }else if(data == 2){
                    alert("该算法已处于启动状态!");
                }else if(data == 3){
                    alert("请先关闭已处于启动状态的同类算法!");
                }else {
                    alert("启动失败!");
                }
            },
            error: function () {
                alert("网络连接超时,请重试!");
            }
        });
    };

    $scope.stopAlg = function ($index) {
        var algId = {"id":$scope.datas[$index].id};
        $.ajax({
            url:"stopAlg",
            type:"POST",
            data:algId,
            dataType:"json",
            timeout:10000,
            success:function (data) {
                if(data == 1){
                    alert("停止成功!");
                    for (var i = 0; i < $scope.data.length; i++) {
                        if ($scope.data[i].id == $scope.datas[$index].id) {
                            $scope.data[i].status = "stateless";
                            $scope.datas[$index].status = "stateless";
                            break;
                        }
                    }
                    $scope.$apply();
                }else if (data == 2){
                    alert("该算法已处于未启用状态!");
                }else {
                    alert("停止失败!");
                }
            },
            error:function () {
                alert("网络连接超时,请重试!");
            }
        });
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
        var algArray = [];
        for(var i = checkbox.length - 1; i >= 0; i--){
            if(checkbox[i].checked == true){
                var alg = {"id":$scope.datas[i].id};
                algArray.push(alg);
            }
        }
        var unDeletingAlg = "";
        $.ajax({
            url:"deleteAlgBatch",
            type:"POST",
            traditional:true,
            data:{algArray:JSON.stringify(algArray)},
            timeout:10000,
            success:function () {
                for(var i = checkbox.length - 1; i >= 0; i--){
                    if (checkbox[i].checked == true){
                        if ($scope.datas[i].status == "working"){
                            if(unDeletingAlg.length != 0)
                                unDeletingAlg += "," + $scope.datas[i].algName;
                            else
                                unDeletingAlg += $scope.datas[i].algName;
                        }
                        for (var j = 0; j < $scope.data.length; j++){
                            if($scope.data[j].id == $scope.datas[i].id && $scope.datas[i].status == "stateless")
                                $scope.data.splice(j, 1);
                        }
                    }
                }
                for(i = checkbox.length - 1; i >= 0; i--){
                    if (checkbox[i].checked == true){
                        checkbox[i].checked = false;
                    }
                }
                $scope.datas = $scope.data.slice($scope.selectedRecNum.recordNum * ($scope.page.pageNo - 1), $scope.selectedRecNum.recordNum * $scope.page.pageNo);
                $scope.page.totalRecord = $scope.data.length;
                if(unDeletingAlg.length != 0)
                    unDeletingAlg += "算法正在使用,未删除,其余算法";
                alert(unDeletingAlg + "删除成功!");
                $scope.$apply();
            },
            error:function () {
                alert("网络连接超时,请重试!");
            }
        });
    };

    $scope.addNewVnfd = function () {
        $scope.$broadcast('childOpen','open');
    };
});
angular.module('myApp').controller('addModalCtrl', function ($scope, $uibModal) {
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

angular.module('myApp').controller('addModalInsCtrl', function ($uibModalInstance) {
    var $ctrl = this;
    $ctrl.functionName = null;
    $ctrl.className = null;
    $ctrl.algName = null;
    $ctrl.ok = function () {
        $uibModalInstance.close();
    };
    $ctrl.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
    $ctrl.upload = function () {
        if ($ctrl.algName == null)
            alert("请输入算法名");
        else if ($ctrl.functionName == null)
            alert("请填入函数名");
        else if($ctrl.className == null)
            alert("请填入类名");
        else {
            var formdata = new FormData();
            var value = $('#upload_alg_jar_select').val();
            formdata.append("algName", $ctrl.algName);
            formdata.append("functionName", $ctrl.functionName);
            formdata.append("className", $ctrl.className);
            formdata.append("function", value);
            formdata.append("file", $("#algFile")[0].files[0]);
            $.ajax({
                url:"uploadAlgJar",
                type:"POST",
                data:formdata,
                timeout:10000,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success:function (data) {
                    if(data.code == '1'){
                        alert("上传成功!");
                        $ctrl.functionName = null;
                        $ctrl.className = null;
                        $ctrl.algName = null;
                    }else if(data.code == '2'){
                        alert("文件格式不合法!");
                    }else if(data.code == '3'){
                        alert("文件已存在!")
                    }else {
                        alert("上传失败,请重试!");
                    }
                },
                error:function () {
                    alert("网络连接超时!");
                }
            });
        }
    };
});
angular.module('myApp',['ngAnimate', 'ngSanitize', 'ui.bootstrap']);
angular.module('myApp').controller('myCtrl', function ($scope) {
    $scope.fetchVnfdList = function () {
        $.ajax({
            url:"vnfdList.json",
            type:"GET",
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
    $scope.fetchVnfdList();

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
                if($scope.data[i].vnfProductName == searchKey || $scope.data[i].createTime == searchKey ||
                    $scope.data[i].vnfd == searchKey || $scope.data[i].company == searchKey
                    || $scope.data[i].version == searchKey){
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
    $scope.delSingle = function ($index) {
        if(confirm("是否删除" + $scope.datas[$index].vnfd)){
            var vnfdInfo = {
                "vnfd":$scope.datas[$index].vnfd,
                "company":$scope.datas[$index].company
            };
            $.ajax({
                url:"deleteVnfd",
                type:"POST",
                data:vnfdInfo,
                timeout:10000,
                success:function () {
                    alert("删除成功!");
                    for(var i = 0; i < $scope.data.length; i++){
                        if($scope.data[i].vnfd == $scope.datas[$index].vnfd){
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
            })
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
        var vnfdArray = [];
        for(var i = checkbox.length - 1; i >= 0; i--){
            if(checkbox[i].checked == true){
                var vnfd = {"vnfd":$scope.datas[i].vnfd, "company":$scope.datas[i].company};
                vnfdArray.push(vnfd);
            }
        }

        if (vnfdArray.length == 0){
            alert("请选择数据!");
        }else {
            $.ajax({
                url:"deleteVnfdBatch",
                type:"POST",
                traditional:true,
                data:{vnfdArray:JSON.stringify(vnfdArray)},
                timeout:10000,
                success:function () {
                    for(var i = checkbox.length - 1; i >= 0; i--){
                        if(checkbox[i].checked == true){
                            for (var j = 0; j < $scope.data.length; j++){
                                if($scope.data[j].vnfd == $scope.datas[i].vnfd)
                                    $scope.data.splice(j, 1);
                            }
                        }
                    }
                    // for(var i = checkbox.length - 1; i >= 0; i--){
                    //     if(checkbox[i].checked == true){
                    //        $scope.data.splice(i, 1);
                    //     }
                    // }
                    $scope.datas = $scope.data.slice($scope.selectedRecNum.recordNum * ($scope.page.pageNo - 1), $scope.selectedRecNum.recordNum * $scope.page.pageNo);
                    $scope.page.totalRecord = $scope.data.length;
                    alert("删除成功!");
                    $scope.$apply();
                },
                error:function () {
                    alert("网络连接超时,请重试!");
                }
            });
        }
    };

    $scope.addNewVnfd = function () {
        $scope.$broadcast('childOpen','open');

    };

    $scope.checkVnfd = function ($index) {
        $scope.selectedVnfd = $scope.datas[$index];
        $scope.$broadcast('checkVnfd', 'open');
    }


    $scope.startVnfd = function ($index) {
        var startAlgInfo = {
            "vnfd":$scope.datas[$index].vnfd,
            "company":$scope.datas[$index].company,
            "version":$scope.datas[$index].version
        };
        $.ajax({
            url: "startVnfd",
            type: "POST",
            data: startAlgInfo,
            dataType:"json",
            timeout: 10000,
            success: function (data) {
                if(data == 1){
                    alert("启动成功!");
                    $scope.datas[$index].status = "working";
                    $scope.$apply();
                }else if(data == 2){
                    alert("该虚拟网络功能已处于启动状态!");

                }else {
                    alert("启动失败!");
                }
            },
            error: function () {
                alert("网络连接超时,请重试!");
            }
        });
    };

    $scope.stopVnfd = function ($index) {
        var algVnfd = {"vnfd":$scope.datas[$index].vnfd};
        $.ajax({
            url:"stopVnfd",
            type:"POST",
            data:algVnfd,
            dataType:"json",
            timeout:10000,
            success:function (data) {
                if(data == 1){
                    alert("停止成功!");
                    $scope.datas[$index].status = "stateless";
                    $scope.$apply();
                }else if (data == 2){
                    alert("该虚拟网络功能已处于未启用状态!");
                }else {
                    alert("停止失败!");
                } ng-controller
            },
            error:function () {
                alert("网络连接超时,请重试!");
            }
        });
    };
});

angular.module('myApp').controller('checkVnfdCtrl', function ($scope, $uibModal) {
    $scope.animationsEnabled = true;
    // $scope.data = "true";
    $scope.$on('checkVnfd', function () {
        $scope.open();
    });

    $scope.open = function () {
        $uibModal.open({
            animation: $scope.animationsEnabled,
            templateUrl: 'check.html',
            controller: 'checkInsCtrl',
            controllerAs: '$ctrl',
            resolve:{
                vnfdInfo:function () {
                    return $scope.selectedVnfd;
                }
            }
        });
    };
});

angular.module('myApp').controller('checkInsCtrl', function ($uibModalInstance, vnfdInfo) {
    var $ctrl = this;
    $ctrl.vnfdData = vnfdInfo;
    $ctrl.ok = function () {
        $uibModalInstance.close();
    };
    $ctrl.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});

angular.module('myApp').controller('addModalCtrl', function ($scope, $uibModal) {
    $scope.animationsEnabled = true;
    // $scope.data = "true";
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
        addModalInstance.result.then(function (vnfData) {
            $scope.modalData = vnfData;
            if($scope.modalData != null)
                $scope.sendFileToVim($scope.modalData);
            $scope.fetchVnfdList();
            // if($scope.modalData != ){
            //     $scope.sendFileToVim($scope.modalData);
            // }
        }, function () {

        });
    };
    $scope.sendFileToVim = function (vnfData) {
        $.ajax({
            url:"unZip",
            type:"POST",
            data:vnfData,
            timeout:10000,
            success:function () {

            },
            error:function () {

            }
        })
    }

});

angular.module('myApp').controller('addModalInsCtrl', function ($uibModalInstance) {
    var $ctrl = this;
    $ctrl.vnfData = null;

    $ctrl.ok = function () {
        $uibModalInstance.close($ctrl.vnfData);
    };
    $ctrl.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
    $ctrl.upload = function () {
        document.getElementById("test2").style.visibility="hidden";
        document.getElementById("test4").style.visibility="hidden";
        document.getElementById("test6").style.visibility="hidden";
        document.getElementById("test1").style.visibility="hidden";
        document.getElementById("test3").style.visibility="hidden";
        document.getElementById("test5").style.visibility="hidden";
        var formdata = new FormData($("#uploadFile")[0]);
        $.ajax({
            url:"uploadVnfd",
            type:"POST",
            data:formdata,
            timeout:1000000000,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success:function (data) {
                if(data.code =='1'){
                    document.getElementById("test1").style.visibility="visible";
                    document.getElementById("test3").style.visibility="visible";
                    document.getElementById("test5").style.visibility="visible";
                    alert("上传成功!");
                    var path = data.path;
                    var vnfd = data.vnfd;
                    var typeOfStorage = data.typeOfStorage;
                    var virtualEnviroment = data.virtualEnviroment;
                    var vnfdInfo = {
                        "path":path,
                        "vnfd":vnfd,
                        "typeOfStorage":typeOfStorage,
                        "virtualEnviroment":virtualEnviroment
                    };
                    $ctrl.vnfData = vnfdInfo;
                }else if (data.code == '2'){
                    document.getElementById("test2").style.visibility="visible";
                    //document.getElementById("test4").style.visibility="hidden";
                    //document.getElementById("test6").style.visibility="hidden";
                    alert("文件格式不合法!");
                }else if (data.code == '4'){
                    alert("文件已存在!")
                }else if (data.code == '5'){
                    alert("文件内不含镜像,请重新上传!");
                    //document.getElementById("test2").style.visibility="hidden";
                    document.getElementById("test4").style.visibility="visible";
                    //document.getElementById("test6").style.visibility="hidden";
                    document.getElementById("test1").style.visibility="visible";
                    //document.getElementById("test3").style.visibility="visible";
                }else if (data.code == '6'){
                    alert("文件内不包含配置文件,请重新上传!");
                    //document.getElementById("test2").style.visibility="hidden";
                    //document.getElementById("test4").style.visibility="hidden";
                    document.getElementById("test6").style.visibility="visible";
                    document.getElementById("test1").style.visibility="visible";
                    document.getElementById("test3").style.visibility="visible";
                }else {
                    alert("上传失败,请选择文件!");
                }
            },
            error:function () {
                alert("请检查VNFD.json内容!");
                //document.getElementById("test2").style.visibility="visible";
                //document.getElementById("test4").style.visibility="visible";
                document.getElementById("test1").style.visibility="visible";
                document.getElementById("test3").style.visibility="visible";
                document.getElementById("test6").style.visibility="visible";
            }
        });
    };

});
angular.module('myApp',['ngAnimate', 'ngSanitize', 'ui.bootstrap']);
angular.module('myApp').controller('myCtrl', function ($scope,$uibModal) {
    $scope.fetchVnfList = function () {
        var userInfo = {"userName":"admin"};
        $.ajax({
            url:"vnfList.json",
            type:"GET",
            data:userInfo,
            dataType:"json",
            timeout:10000,
            success:function (data) {
                $scope.totalRecNum = data.length;
                $scope.data = data;
                $scope.values = [{"recordNum": 5},{"recordNum": 6},{"recordNum": 7}];
                $scope.selectedRecNum = $scope.values[0];
                $scope.currentPage=1;
                if($scope.data.length != 0){
                    $scope.datas = $scope.data.slice($scope.selectedRecNum.recordNum * ($scope.currentPage - 1), $scope.selectedRecNum.recordNum * $scope.currentPage);
                }else{alert("No Data!");}
                $scope.page={"recordNum":$scope.selectedRecNum.recordNum, "pageSize":5, "pageNo":$scope.currentPage, "totalRecord":$scope.totalRecNum};
                $scope.$apply();
            },
            error:function () {
                alert("网络连接超时!");
            }
        });
    };
    $scope.fetchVnfList();

    $scope.dataArray = {types:["--请选择--"],companys:["--请选择--"],vnfds:{}};
    $scope.fetchVnfdList = function () {
        $.ajax({
            url: "vnfdList.json",
            type: "GET",
            dataType: "json",
            timeout: 10000,
            success: function (data) {
                $scope.vnfdList = data;
                if ($scope.vnfdList.length != 0) {
                    $scope.dataArray.vnfds=$scope.vnfdList;
                    // alert($scope.vnfdList[0].virtualMemSize);
                    for (var i = 0; i < $scope.vnfdList.length; i++) {
                        if ($scope.dataArray.types.indexOf($scope.vnfdList[i].vnfProductName)<0){
                            $scope.dataArray.types.push($scope.vnfdList[i].vnfProductName);
                        }
                        if ($scope.dataArray.companys.lastIndexOf($scope.vnfdList[i].company)<0){
                            $scope.dataArray.companys.push($scope.vnfdList[i].company);
                        }
                    }
                } else {
                    alert("No vnfd's Data!")
                }
            }
        });
    };

    $scope.fetchVnfdList();




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
                if($scope.data[i].vnfName == searchKey || $scope.data[i].createTime == searchKey ||
                    $scope.data[i].vnfd == searchKey){
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
        if(confirm("是否删除" + $scope.datas[$index].vnfName)){
            var vnfInfos = {
                "vnfName":$scope.datas[$index].vnfName,
                "vnfd":$scope.datas[$index].vnfd
            };
            var vnfInfo = {
                "vnfName": $scope.datas[$index].vnfName
            };
            $.ajax({
                url:"deleteVnf",
                type:"POST",
                data:vnfInfos,
                dataType:"json",
                timeout:10000,
                success:function (data) {
                    $scope.del =data;
                    if($scope.del == true){
                        alert("删除成功！");
                        $scope.data.splice($index, 1);
                        $scope.datas = $scope.data.slice($scope.selectedRecNum.recordNum * ($scope.page.pageNo - 1), $scope.selectedRecNum.recordNum * $scope.page.pageNo);
                        $scope.page.totalRecord = $scope.data.length;
                        $scope.$apply();
                    }else{
                        alert("该vnf正在使用，不可删除！");
                    }
                },
                error:function () {
                    alert("网络连接超时!");
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
        var del_array = [];
        for(var i = checkbox.length - 1; i >= 0; i--){
            if(checkbox[i].checked == true){
                del_array.push($scope.data.slice(i, i+1));
            }
        }
        var del_vnf = [];
        for(var m = 0;m<del_array.length;m++){
            var vnf_info = {
                "vnfName":del_array[m][0].vnfName,
                "vnfd":del_array[m][0].vnfd
            };
            del_vnf.push(vnf_info);
        }
        $scope.delQueryVnfs(del_vnf);
        // $.ajax({
        //     url:"deleteQuery",
        //     type:"POST",
        //     data:{
        //         "vnfs":JSON.stringify(del_vnf)
        //     },
        //     dataType: "json",
        //     timeout:10000,
        //     success:function(data){
        //         $scope.del_vnfNames = data;
        //         var names = "";
        //         for (var i =0; i<$scope.del_vnfNames.length;i++){
        //             names = names+ " "+$scope.del_vnfNames[i];
        //             for (var m = 0; m<$scope.data.length;m++){
        //                 if($scope.data[m].vnfName == $scope.del_vnfNames[i]){
        //                     $scope.data.splice(m,1);
        //                 }
        //             }
        //         }
        //         alert(names+"删除成功,其他vnf正在使用不可删除！");
        //         $scope.datas = $scope.data.slice($scope.selectedRecNum.recordNum * ($scope.page.pageNo - 1), $scope.selectedRecNum.recordNum * $scope.page.pageNo);
        //         $scope.page.totalRecord = $scope.data.length;
        //         $scope.$apply();
        //     },
        //     error:function(){
        //         alert("网络连接超时,请重试!");
        //     }
        // })
    };

    $scope.delQueryVnfs = function (del_vnfInfo) {
        $.ajax({
            url:"deleteQuery",
            type:"POST",
            data:{
                "vnfs":JSON.stringify(del_vnfInfo)
            },
            dataType: "json",
            timeout:10000,
            success:function(data){
                $scope.del_vnfNames = data;
                var names = "";
                for (var i =0; i<$scope.del_vnfNames.length;i++){
                    names = names+ " "+$scope.del_vnfNames[i];
                    for (var m = 0; m<$scope.data.length;m++){
                        if($scope.data[m].vnfName == $scope.del_vnfNames[i]){
                            $scope.data.splice(m,1);
                        }
                    }
                }
                alert(names+"删除成功,其他vnf正在使用不可删除！");
                $scope.datas = $scope.data.slice($scope.selectedRecNum.recordNum * ($scope.page.pageNo - 1), $scope.selectedRecNum.recordNum * $scope.page.pageNo);
                $scope.page.totalRecord = $scope.data.length;
                $scope.selectInverse();
                $scope.$apply();
            },
            error:function(){
                alert("网络连接超时,请重试!");
            }
        })
    };
    $scope.del_all = function(){
        var del_vnfs = [];
        for(var m = 0;m<$scope.data.length;m++){
            var vnf_info = {
                "vnfName":$scope.data[m].vnfName,
                "vnfd":$scope.data[m].vnfd
            };
            del_vnfs.push(vnf_info);
        }
        $scope.delQueryVnfs(del_vnfs);
    };

    $scope.addNew = function () {
        $scope.animationsEnabled = true;
        $scope.data = "true";
        var addModalInstance = $uibModal.open({
            animation:$scope.animationsEnabled,
            templateUrl:'add.html',
            controller:'addModalInsCtrl',
            controllerAs:'$ctrl',
            resolve:{
                vnfds:function () {
                    return $scope.dataArray;
                }
            }
        });
        addModalInstance.result.then(function (selectedVnf) {
            $scope.selectedVnf = selectedVnf;
            $.ajax({
                url:"addVnf.json",
                type:"POST",
                timeout:10000,
                data: $scope.selectedVnf,
                success: function () {
                    $scope.fetchVnfList();
                }

            })

        },function () {

        });
    };

    $scope.checkVnf = function($index){
        $scope.animationsEnabled = true;
        $scope.data = "true";
        $scope.selectedData = $scope.datas[$index];
        var checkModalInstance = $uibModal.open({
            animation:$scope.animationsEnabled,
            templateUrl:'checkVnf.html',
            controller:'checkModalInsCtrl',
            controllerAs:'$checkCtrl',
            resolve:{
                data:function () {
                    return $scope.selectedData;

                }
            }

        });
        checkModalInstance.result.then(function () {

        },function () {

        });

    };

    // $scope.editVnf = function($index){
    //     $scope.animationsEnabled = true;
    //     $scope.data = "true";
    //
    //
    // };
});
angular.module('myApp').controller('checkModalInsCtrl', function ($uibModalInstance,data){
    var $checkCtrl =  this;
    $checkCtrl.selectedData = data;
    $checkCtrl.ok = function () {
        $uibModalInstance.close();
    };
    $checkCtrl.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});

angular.module('myApp').controller('addModalInsCtrl', function ($uibModalInstance,vnfds) {
    var $ctrl = this;
    $ctrl.vnfName = '';
    $ctrl.userName = '';
    $ctrl.vnfds = vnfds.vnfds;
    $ctrl.types = vnfds.types;
    $ctrl.companys = vnfds.companys;
    $ctrl.selectedType = $ctrl.types[0];
    $ctrl.selectedCompany = $ctrl.companys[0];
    $ctrl.selectedVnf = {};

    $ctrl.typeChange = function(selectedType){
        if(selectedType === "--请选择--"){
            $ctrl.companys = vnfds.companys;
            $ctrl.selectedVnf = {};
        }else{
            $ctrl.companys =["--请选择--"];
            for (var i = 0; i<$ctrl.vnfds.length; i++ ){
                if($ctrl.vnfds[i].vnfProductName == selectedType ){
                    if($ctrl.companys.indexOf($ctrl.vnfds[i].company)<0) {
                        $ctrl.companys.push($ctrl.vnfds[i].company);
                    }
                }
            }
            $ctrl.informationUpdate();

        }
    };
    $ctrl.companyChange = function(selectedCompany){
        if(selectedCompany ==="--请选择--" ){
            $ctrl.types = vnfds.types;
            $ctrl.selectedVnf = {};
        }else{
            $ctrl.types = ["--请选择--"];
            for (var i = 0; i<$ctrl.vnfds.length; i++){
                if($ctrl.vnfds[i].company == selectedCompany){
                    if($ctrl.types.indexOf($ctrl.vnfds[i].vnfProductName)<0){
                        $ctrl.types.push($ctrl.vnfds[i].vnfProductName);
                    }
                }
            }
            $ctrl.informationUpdate();

        }
    };

    $ctrl.informationUpdate = function(){
        if($ctrl.selectedType !== "--请选择--" && $ctrl.selectedCompany !== "--请选择--"){
            for (var j = 0; j<$ctrl.vnfds.length; j++){
                if ($ctrl.vnfds[j].vnfProductName == $ctrl.selectedType && $ctrl.vnfds[j].company == $ctrl.selectedCompany){
                    $ctrl.selectedVnf.vnfd = $ctrl.vnfds[j].vnfd;
                    $ctrl.selectedVnf.vnfProductName = $ctrl.vnfds[j].vnfProductName;
                    $ctrl.selectedVnf.company = $ctrl.vnfds[j].company;
                    $ctrl.selectedVnf.version = $ctrl.vnfds[j].version;
                    $ctrl.selectedVnf.numVirtualCpu = $ctrl.vnfds[j].numVirtualCpu;
                    $ctrl.selectedVnf.sizeOfStorage = $ctrl.vnfds[j].sizeOfStorage;
                    $ctrl.selectedVnf.virtualMemSize = $ctrl.vnfds[j].virtualMemSize;
                }
            }
        }
    };
    // $ctrl.informationUpdate();

    $ctrl.ok = function (vnf, user) {
        $ctrl.selectedVnf.vnfName = $ctrl.vnfName;
        $ctrl.selectedVnf.userName = $ctrl.userName;
        $uibModalInstance.close($ctrl.selectedVnf);
    };
    $ctrl.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };



});
angular.module('myApp',[]);
angular.module('myApp').controller('myCtrl',function ($scope) {
    $scope.login = function (username, password) {
        var userInfo = {
            "username":username,
            "password":password
        };
        $.ajax({
            url:"loginCheck",
            type:"POST",
            data:userInfo,
            timeout:10000,
            dataType : "json",
            success:function (data) {
                if(data.code == '1'){
                    window.location.href = "../html/index.html";
                }else{
                    alert("用户名或密码错误!");
                }
            },
            error:function () {
                alert("网络连接超时!");
            }
        });
        $scope.userName = '';
        $scope.passWord = '';
    } ;

    $scope.register = function (newUser, newPassword, userEmail) {
        var userInfo = {
            "username":newUser,
            "password":newPassword
        };
        $.ajax({
            url:"userRegister",
            type:'POST',
            data:userInfo,
            timeout:10000,
            dataType:"json",
            success:function (data) {
                if(data.code == '1')
                    alert("注册成功!");
                else if(data.code == '2')
                    alert("用户名已存在!");
                else if(data.code == '3')
                    alert("密码过于简单!");
                else
                    alert("注册失败!");
            },
            error:function () {
                alert("网络连接超时!");
            }
        });
        $scope.newUser = '';
        $scope.newPassword = '';
        $scope.userEmail = '';
    };
});
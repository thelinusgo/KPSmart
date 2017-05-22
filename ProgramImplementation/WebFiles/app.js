/**
 * Created by linus on 19/05/2017.
 */
var app = angular.module('plunker', []);
//list of users that can log in to KPSmart


/*Code for the sidebar opening-closing logic */
function openNav() {
    document.getElementById("mySidenav").style.width = "200px";
    document.getElementById("main").style.marginLeft = "200px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginLeft= "0";
}


app.controller('MainCtrl', function($scope) {
    $scope.userTypes = ['CEO', 'Manager' ,'Regular User'];
    $scope.name = 'World';

    $scope.testUser = "userman";
    $scope.testPwd = "password1";


    $scope.checkLogin = function(){
        if($scope.username == "" && $scope.password == ""){
            $scope.print="Please input a valid username and password";
        }else if($scope.username == "" && $scope.password != ""){
            $scope.print="Please input a valid username";
        }else if($scope.password == ""){
            $scope.print="Please input a valid password";
        }else if($scope.username == $scope.testUser && $scope.password == $scope.testPwd){
            alert("Logged in successfully as " + testUser);
            $scope.cancelLogin();
        }else{
            $scope.print="Incorrect username or password";
        }
    };

    $scope.cancelLogin = function(){
        $scope.username = "";
        $scope.password = "";
    }


});

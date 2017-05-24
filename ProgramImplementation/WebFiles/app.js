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
    $scope.priority = ['InternAir', 'InternStd', 'DomestAir', 'DomestStd']; //define the different types of priority
    $scope.userTypes = ['CEO', 'Manager' ,'Regular User']; //define the different types of User that can access the system.
    var testUser = "userman";
    var testPwd = "password1";


    $scope.checkLogin = function(){

        var userInput = $scope.username;
        var passwordInput = $scope.password;
        $scope.currentUser = userInput; //Used to Store the Current Users Information

        if(userInput == "" && passwordInput == ""){
            $scope.print="Please input a valid username and password";
        }else if(userInput == "" && passwordInput != ""){
            $scope.print="Please input a valid username";
        }else if(passwordInput == ""){
            $scope.print="Please input a valid password";
        }else if(userInput == testUser && passwordInput == testPwd){
            alert("Welcome " + userInput +", you have logged in successfully.");
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

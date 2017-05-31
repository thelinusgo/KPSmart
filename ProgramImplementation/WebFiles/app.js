/**
 * Created by linus on 19/05/2017.
 */
var app = angular.module('kps', []);
//list of users that can log in to KPSmart


/*Code for the sidebar opening-closing logic */
/*function openNav() {
    document.getElementById("mySidenav").style.width = "300px";
    document.getElementById("main").style.marginLeft = "300px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginLeft= "0";
}*/


app.controller('MainCtrl', function($scope, $http, $location) {

    $scope.userlist = "userlist.json"; //the list of different users

    $scope.priority = ['InternAir', 'InternStd', 'DomestAir', 'DomestStd']; //define the different types of priority
    $scope.userTypes = ['CEO', 'Manager' ,'Regular User']; //define the different types of User that can access the system.
    $scope.transportTypes = ['Land', 'Sea', 'Air']; //define the different users

    $scope.customerPriceFields = {origin: "", destination: "", pricePerGram:"", pricePerCubic: "", mailPriority: ""};

    var testUser = "userman";
    var testPwd = "password1";

    $http.get($scope.userlist)
        .then(function sucessCall(response)	{
                $scope.userlist = response.data.users;
            },function errorCall()	{
                alert("Error reading users list.");
            }
        );


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
        }else{
            $scope.validUserName = false;
            $scope.validPassword = false;

            for(i = 0; i < $scope.userlist.length; i++){
                if($scope.userlist[i].LoginName == userInput){
                    $scope.validUserName = true;
                }

                if($scope.userlist[i].UPassword == passwordInput){
                    $scope.validPassword = true;
                }
            }
        if($scope.validUserName && $scope.validPassword){
            alert("Welcome " + userInput +", you have logged in successfully.");
            location.href='processevents.html';
            $scope.cancelLogin();

        }else{
            $scope.print = "Incorrect userame or password";
        }
        }
    };

    $scope.cancelLogin = function(){
        $scope.username = "";
        $scope.password = "";
    }

    $scope.sendEvent = function() {
        if ($scope.originField == null) {
            alert("Please fill out the origin field");
        } else if ($scope.destinationField == null) {
            alert("Please fill out the destination field");
        } else if ($scope.volumePackageField == null) {
            alert("Please choose a volume amount for your mail.");
        } else if ($scope.packageWeightField == null) {
            alert("Please choose a volume amount for your mail.");
        }

        var reqObject = {
            "MsgType": "requestDelivery", "Origin": $scope.originField, "Destination": $scope.destinationField,
            "Mail Priority": $scope.priorityField, "Weight of Package": $scope.volumePackageField
        }

        sendData(reqObject);
    };

    $scope.sendUpdatedCustomerPrice = function() {

        if (customerPriceFields.origin == null) {
            alert("Please fill out the origin field");
        } else if (customerPriceFields.destination == null) {
            alert("Please fill out the destination field");
        } else if (customerPriceFields.pricePerGram == null) {
            alert("Please fill out the price per gram field.");
        } else if (customerPriceFields.pricePerCubic == null) {
            alert("Please fill out the price per cubic field.");
        } else if (customerPriceFields.mailPriority == null) {
            alert("Please choose a mail priority.");
        }

        var regObject = {
            "MsgType": "updateCustomerPrice", "Origin": customerPriceFields.origin, "Destination": customerPriceFields.destination,
            "pricePerGram": customerPriceFields.pricePerGram, "pricePerCubic": customerPriceFields.pricePerCubic,
            "mailPriority": customerPriceFields.mailPriority
        }

        sendData(regObject)
    }

});

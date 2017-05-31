/**
 * Created by linus on 31/05/17.
 */
/**
 * This JS file is responsible for sending and recieving data from the server as JS files.
 */

var app = angular.module('client', []);

app.controller('MainCtrl', function($scope, $http, $location) {


    /**
     * Sends data from the website and converts it into a Json file.
     * @param message
     */
    $scope.sendData = function(json_message){
        if(message == null){
            alert("There must be a message to pass in");
            return;
        }

        /*Returns the message being passed in as a String. */
        var stringObj =  JSON.stringify(json_message);
        alert("String object: " + stringObj);
        //TODO need to send stringObj to server??

    }
    /**
     * Recieves data from the server and places it back into the website.
     * @param message
     */
    $scope.receiveData = function(message){
        if(message == null){
            alert("There must be a message to pass in");
        }

        //TODO need to implement the rest of this part.

    }

    /**
     * Grabs all the fields from request Delivery HTML file and returns a JSON file.
     * @returns {{MsgType: string, Origin: *, Destination: *, Mail Priority: *, Weight of Package: *}}
     */
    $scope.sendRequestDelivery = function(){

    if($scope.originField == null){
        alert("Please fill out the origin field");
    }else if($scope.destinationField == null){
        alert("Please fill out the destination field");
    }else if($scope.priorityField == null){
        alert("Please choose a type of priority for your mail");
    }else if($scope.volumePackageField == null){
        alert("Please choose a volume amount for your mail.");
    }

    var reqObject = {"MsgType": "requestDelivery", "Origin" : $scope.originField, "Destination": $scope.destinationField,
    "Mail Priority": $scope.priorityField, "Weight of Package": $scope.volumePackageField};

    sendData(reqObject);
    }

});


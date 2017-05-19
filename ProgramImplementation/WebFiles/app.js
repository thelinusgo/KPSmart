/**
 * Created by linus on 19/05/2017.
 */
var app = angular.module('plunker', []);
//list of users that can log in to KPSmart

app.controller('MainCtrl', function($scope) {
    var userTypes = ['CEO', 'Manager' ,'Regular User'];
    $scope.name = 'World';
});

/* Controllers */

var digestControllers = angular.module('digestControllers', ['ngResource']);


digestControllers.controller('AuthCtrl', ['$scope', '$http', '$location', function ($scope, $http, $location) {
    $http.get("/v1/user").success(function(data) {
        $scope.user = data.name;
        $scope.authenticated = true;
    }).error(function() {
        $scope.user = "N/A";
        $scope.authenticated = false;
    });

    $scope.logout = function() {
        $http.post('/logout', {}).success(function () {
            console.log("logged out");
            self.authenticated = false;
            $location.path("/");
        }).error(function (data) {
            console.log("Logout failed");
            self.authenticated = false;
        });
    };
}]);



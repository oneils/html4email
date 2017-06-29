/* Controllers */

var digestControllers = angular.module('digestControllers', ['ngResource']);


digestControllers.controller('AuthCtrl', ['$scope', '$http', '$location', function ($scope, $http, $location) {
    $http.get("/api/v1/user").success(function(data) {
        $scope.user = data.name;
        $scope.authenticated = data.authenticated;
        $scope.authorities = data.authorities;

        $scope.isDisplayed = isDisplayed();
    }).error(function() {
        $scope.user = "N/A";
        $scope.authenticated = false;
    });

    $scope.logout = function() {
        $http.post('/logout', {}).success(function () {
            console.log("logged out");
            $scope.authenticated = false;
            $scope.isDisplayed = false;
            $location.path("/");
        }).error(function (data) {
            console.log("Logout failed");
            $scope.authenticated = false;
            $scope.isDisplayed = false;
        });
    };

    var isDisplayed = function () {
        var authorities = $scope.authorities;
        for (var i = 0; i < authorities.length; i++) {
            if (authorities[i].authority === "ROLE_ADMIN") {
                return true;
            }
        }
        return false;
    };

}]);



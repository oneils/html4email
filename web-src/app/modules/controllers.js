/* Controllers */

var digestControllers = angular.module('digestControllers', ['ngResource']);


digestControllers.controller('AuthCtrl', ['$scope', '$http', '$location', function ($scope, $http, $location) {
    $http.get("/api/v1/user").success(function(data) {
        $scope.user = data.name;
        $scope.authenticated = true;
        $scope.principal = data.principal;

        $scope.isDisplayed = isDisplayed();
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

    var isDisplayed = function () {
        var authorities = $scope.principal.authorities;

        for (var i = 0; i < authorities.length; i++) {
            if (authorities[i].authority === "ROLE_ADMIN") {
                return true;
            }
        }
        return false;
    };

}]);



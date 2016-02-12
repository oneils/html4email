digestControllers.controller('ArchiveCtrl', ['$scope', '$http', function ($scope, $http) {
    $scope.currentPage = 1;

    $http({
        method: 'GET',
        url: '/v1/digests'
    }).then(function successCallback(response) {
        $scope.digests = response.data.content;
        $scope.totalItems = response.data.totalElements;
    });

    $scope.$watch("totalItems", true);
    $scope.$watch("digests", true);

    $scope.$watch("currentPage", function (page) {
        $http({
            method: 'GET',
            // on backend pages start from 0
            url: '/v1/digests?page=' + (page - 1 )
        }).then(function successCallback(response) {
            $scope.digests = response.data.content;
            $scope.totalItems = response.data.totalElements;
        });

    }, true);
}]);
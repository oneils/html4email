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

}]);
digestControllers.controller('ArchiveCtrl', ['$scope', '$http', function ($scope, $http) {

    $http({
        method: 'GET',
        url: '/v1/digests'
    }).then(function successCallback(response) {
        $scope.digests = response.data.content;
    });

}]);
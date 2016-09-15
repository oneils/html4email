digestControllers.controller('DigestCtrl', ['$scope', '$http', '$routeParams', function ($scope, $http, $routeParams) {
    var digestId = $routeParams.id;
    $scope.digest = {};

    $http({
        method: 'GET',
        url: '/api/v1/digests/' + digestId
    }).then(function successCallback(response) {
        $scope.digest = response.data;
    });

    /**
     * Returns a hostname from the URL specified.
     * @param url to be parsed
     * @returns hostname
     */
    $scope.getHostName = function getHostName(url){
        var parser = document.createElement('a');
        parser.href = url;
        return parser.hostname;
    };

}]);
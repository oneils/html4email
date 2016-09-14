digestControllers.controller('ArchiveCtrl', ['$scope', '$http', function ($scope, $http) {
    $scope.currentPage = 1;
    $scope.toggle = true;

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
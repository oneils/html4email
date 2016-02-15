digestControllers.controller('DigestCtrl', ['$scope', '$http', function ($scope, $http) {
    var digest = {};
    $scope.toggle = true;

    $scope.isCollapsed = false;
    digest.title = "BackEnd Digest #";
    $scope.digest = digest;

    $scope.digests = [];

    $scope.createDigest = function () {
        var digest = {};
        digest.title = $scope.digest.title;

        $scope.digests.push(digest);

        $scope.newDigestForm.$setPristine();
        $scope.newDigestForm.$setUntouched();
    };


    $scope.saveJsonDigest = function () {
        var digest = {};
        digest.title = $scope.digest.title;

        $http({
            method: 'POST',
            url: '/v1/digests',
            headers: {
                'Content-Type': "application/json"
            },
            data: $scope.jsonDigest
        }).then(function successCallback(response) {
            $scope.jsonDigest = null;
        });

        $scope.digests.push(digest);

        $scope.saveJsonForm.$setPristine();
        $scope.saveJsonForm.$setUntouched();
    };
}]);
digestControllers.controller('DigestCtrl', ['$scope', '$http', function ($scope, $http ) {
    var digest = {};
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
}]);
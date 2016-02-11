digestControllers.controller('DigestCtrl', ['$scope', '$http', function ($scope, $http ) {
    console.log("Digest controller");
    var digest = {};
    digest.title = "BackEnd Digest #";
    $scope.digest = digest;

    $scope.digests = [];


    $scope.createDigest = function () {
        var digest = {};
        digest.title = $scope.digest.title;

        console.log(digest);

        $scope.digests.push(digest);

        $scope.newDigestForm.$setPristine();
        $scope.newDigestForm.$setUntouched();
    };
}]);
digestControllers.controller('DigestCtrl', ['$scope', '$http', function ($scope, $http) {
    var digest = {};
    $scope.toggle = true;
    $scope.hideDigestJsonValidation = true;

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

        if (!isJsonValid($scope.jsonDigest)) {
            var message = 'Invalide Digest JSON';
            $scope.hideDigestJsonValidation = false;
            return;
        }

        $scope.hideDigestJsonValidation = true;

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

    function isJsonValid(digestJson) {
        try {
            JSON.parse(digestJson)
        } catch (e) {
            return false;
        }
        return true;
    }
}]);
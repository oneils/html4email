/* Controllers */

var digestControllers = angular.module('digestControllers', ['ngResource']);

digestControllers.controller('ArticleListCtrl', ['$scope', 'Article', function ($scope, Article) {
    $scope.articles = Article.query();
}]);

digestControllers.controller('SaveArticleCtrl', ['$scope', 'Article', function ($scope, Article) {
    $scope.article = {};
    $scope.articles = Article.query();

    $scope.saveArticle = function () {
        var article = new Article();

        article.title = $scope.article.title;
        article.url = $scope.article.url;
        article.description = $scope.article.description;

        var result = article.$save();

        result.then(function (data) {
            $scope.articles.push(data)
        });

        $scope.saveArticleForm.$setPristine();
        $scope.saveArticleForm.$setUntouched();
        $scope.article = {}
    };

    $scope.deleteArticle = function(inputArticle) {
        console.log(inputArticle.id);

        $scope.articles.pop(inputArticle);
        var article = new Article();
        article.$delete({id: inputArticle.id});
    };
}]);

digestControllers.controller('DigestCtrl', ['$scope', '$http', 'Article', 'Digest', function ($scope, $http, Article, Digest) {
    $scope.digests = Digest.query();

    var digest = {};
    digest.title = "BackEnd Digest #";

    $scope.digest = digest;

    // TODO articles should be retrieved for the selected Digest.
    $scope.article = {};
    $scope.articles = Article.query();
    $scope.digestArticles = [];



    $scope.createDigest = function () {
        var digest = {};
        digest.title = $scope.digest.title;

        $scope.digests.push(digest);

        $scope.newDigestForm.$setPristine();
        $scope.newDigestForm.$setUntouched();
    };

    $scope.getArticles = function(data) {

        // TODO remove duplicated REST call on every clicking on Digest title
        $http({
            method: 'GET',
            url: '/v1/digests/' + data + '/articles'
        }).then(function successCallback(response) {

            console.log(response.data);

            $scope.digestArticles = response.data;

        }, function errorCallback(response) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    }
}]);
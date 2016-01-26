/* Controllers */

var digestControllers = angular.module('digestControllers', ['ngResource']);

digestControllers.controller('ArticleListCtrl', ['$scope', '$http', 'Article', function ($scope, $http, Article) {
    $scope.articles = Article.query();
    $scope.topics = [];

    $http({
        method: 'GET',
        url: '/v1/articles/topics'
    }).then(function successCallback(response) {
        $scope.topics = response;
    });
}]);

digestControllers.controller('SaveArticleCtrl', ['$scope', 'Article', function ($scope, Article) {
    $scope.article = {};
    $scope.articles = Article.query();

    $scope.saveArticle = function () {
        var article = new Article();

        article.title = $scope.article.title;
        article.url = $scope.article.url;
        article.description = $scope.article.description;
        article.topicId = $scope.topics.repeatSelect;

        var result = article.$save();

        result.then(function (data) {
            $scope.articles.push(data)
        });

        $scope.saveArticleForm.$setPristine();
        $scope.saveArticleForm.$setUntouched();
        $scope.article = {}
    };

    $scope.deleteArticle = function(inputArticle) {
        $scope.articles.pop(inputArticle);
        var article = new Article();
        article.$delete({id: inputArticle.id});
    };
}]);

digestControllers.controller('DigestCtrl', ['$scope', '$http', 'Article', 'Digest', function ($scope, $http, Article, Digest) {
    var digests = Digest.query();

    $scope.article = {};
    $scope.articles = Article.query();

    var digestArticles = [];
    digests.$promise
        .then(function (data) {

            for (var i = 0; i < data.length; i++) {
                digestArticles.push(
                    {
                        "digest": {
                            "id": data[i].id,
                            "createdDate": data[i].createdDate,
                            "title": data[i].title,
                            "articles": []
                        }
                    }
                );
            }
            $scope.digestArticles = digestArticles;
        });

    var digest = {};
    digest.title = "BackEnd Digest #";
    $scope.digest = digest;


    $scope.createDigest = function () {
        var digest = {};
        digest.title = $scope.digest.title;

        $scope.digests.push(digest);

        $scope.newDigestForm.$setPristine();
        $scope.newDigestForm.$setUntouched();
    };

    $scope.getArticles = function (digestId) {
        var digestArticles = $scope.digestArticles;

        // TODO remove duplicated REST call on every clicking on Digest title
        $http({
            method: 'GET',
            url: '/v1/digests/' + digestId + '/articles'
        }).then(function successCallback(response) {
            var articles = response.data;

            for (var i = 0; i < digestArticles.length; i++) {

                if (digestArticles[i].digest.id === digestId) {
                    digestArticles[i].digest.articles = articles;
                }
            }

            $scope.digestArticles.concat(digestArticles);

        }, function errorCallback(response) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    }
}]);
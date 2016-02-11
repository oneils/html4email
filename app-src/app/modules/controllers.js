/* Controllers */

var digestControllers = angular.module('digestControllers', ['ngResource']);

digestControllers.controller('ArticleListCtrl', ['$scope', '$http', 'Article', function ($scope, $http, Article) {
    $scope.articles = Article.query();
    $scope.topics = [];


    // TODO topic should be retrieved from the appropriate REST API
    $http({
        method: 'GET',
        url: '/v1/topics'
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

        var topic = {};
        topic.id = $scope.topics.repeatSelect;
        article.topic = topic;

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


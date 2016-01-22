/* Controllers */

var digestControllers = angular.module('digestControllers', ['ngResource']);

digestControllers.controller('ArticleListCtrl', ['$scope', 'Article',function($scope, Article) {
    $scope.articles = Article.query();
}]);

digestControllers.controller('SaveArticleCtrl', ['$scope', 'Article',function($scope, Article) {
    $scope.article = {};

//    if (!$scope.article) {
//        return;
//    }

//    $http.post("v1/articles/saveArticle", $scope.article);

    $scope.saveArticle = function() {
//        $http.post("v1/articles/saveArticle", $scope.article);
        console.log($scope.article.title + " " + $scope.article.url + " " + $scope.article.description);
        var article = new Article();
        article.title = $scope.article.title
        article.url = $scope.article.url
        article.description = $scope.article.description
        article.$save();
    };
}]);

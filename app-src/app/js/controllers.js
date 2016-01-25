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

digestControllers.controller('DigestCtrl', ['$scope', 'Article', 'Digest', function ($scope, Article) {
    
}]);
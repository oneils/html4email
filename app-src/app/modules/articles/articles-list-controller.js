digestControllers.controller('ArticleListCtrl', ['$scope', '$http', 'Article', function ($scope, $http, Article) {
    $scope.currentPage = 1;
    $scope.itemsPerPage = 10;
    $scope.article = {};
    $scope.articles = [];

    $http({
        method: 'GET',
        url: '/v1/articles'
    }).then(function successCallback(response) {
        $scope.articles = response.data.content;
        $scope.totalItems = response.data.totalElements;
    });

    $scope.$watch("totalItems", true);
    $scope.$watch("articles", true);

    $scope.$watch("currentPage", function (page) {
        $http({
            method: 'GET',
            url: '/v1/articles?page=' + (page -1)
        }).then(function successCallback(response) {
            $scope.articles = response.data.content;
            $scope.totalItems = response.data.totalElements;
        });
    });

    $scope.saveArticle = function () {
        var article = new Article();

        article.title = $scope.article.title;
        article.url = $scope.article.url;
        article.description = $scope.article.description;

        var result = article.$save();

        result.then(function (data) {
            if ($scope.articles.length === 10) {
                $scope.articles.pop();
            }
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
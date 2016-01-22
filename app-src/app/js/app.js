'use strict';

// alert('sdfsdf');
var digestApp = angular.module('digestApp', [
    'ngRoute',
    'digestControllers',
    'digestServices'
]);

// digestApp.config(['$routeProvider', function($routeProvider) {
//     $routeProvider.when('articles', {
//         templateUrl: 'modules/articles/articles-list.html',
//         controller: 'ArticleListCtrl'
//     }).
//     otherwise({
//         redirectTo: '/'
//     });
// }]);

digestApp.config(['$routeProvider',
function($routeProvider) {
    $routeProvider.
    when('/article-list', {
        templateUrl: 'modules/articles/articles-list.html',
        controller: 'ArticleListCtrl'
    }).
    when('/import-digest', {
        templateUrl: 'modules/import/import-digest.html'
    }).
    when('/', {
        templateUrl: 'modules/mainpage/mainpage.html'
    }).
    otherwise({
        redirectTo: '/'
    });
}]);

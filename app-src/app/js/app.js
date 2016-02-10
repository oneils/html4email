'use strict';

var digestApp = angular.module('digestApp', [
    'ngRoute',
    'digestControllers',
    'digestServices'
]);

digestApp.config(['$routeProvider',
function($routeProvider) {
    $routeProvider.
    when('/article-list', {
        templateUrl: 'modules/articles/articles-list.html',
        controller: 'ArticleListCtrl'
    }).
    when('/digest', {
        templateUrl: 'modules/digest/digest.html',
        controller: 'DigestCtrl'
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

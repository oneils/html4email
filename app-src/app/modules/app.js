'use strict';

var digestApp = angular.module('digestApp', [
    'ngRoute',
    'digestControllers',
    'digestServices',
    'ui.bootstrap'
]);

digestApp.config(['$routeProvider',
function($routeProvider) {
    $routeProvider.
    when('/admin', {
        templateUrl: 'modules/admin/admin.html',
        controller: 'ArticleListCtrl'
    }).
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
        templateUrl: 'modules/archive/archive.html',
        controller: 'ArchiveCtrl'
    }).
    otherwise({
        redirectTo: '/'
    });
}]);

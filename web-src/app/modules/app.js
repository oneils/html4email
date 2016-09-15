'use strict';

var digestApp = angular.module('digestApp', [
    'ngRoute',
    'ngSanitize',
    'digestControllers',
    'digestServices',
    'ui.bootstrap',
    'ngMessages'
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
    when('/digest/:id', {
        templateUrl: 'modules/digest/digest.html',
        controller: 'DigestCtrl'
    }).
    when('/save-digest', {
        templateUrl: 'modules/save-digest/save-digest.html',
        controller: 'SaveDigestCtrl'
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

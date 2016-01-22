'use strict';

/* Services */
var digestServices = angular.module('digestServices', ['ngResource']);

digestServices.factory('Article', ['$resource', function ($resource) {
    return $resource('v1/articles/:id', {id: '@id'});
}]);

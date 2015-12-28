(function () {
    'use strict';

    angular.module('app', ['ngRoute',
        'ngAnimate'
    ])
        .config(['$routeProvider', '$locationProvider',
            function ($routeProvider, $locationProvider) {
                $locationProvider.html5Mode({
                    enabled: true,
                    requireBase: false
                });
                $routeProvider
                    .when('/', {
                        templateUrl: 'modules/mainpage/mainpage.html'
                    })
                    .when('/drafts', {
                        templateUrl: 'modules/draft/draft.html'
                    })
                    .when('/test', {
                        templateUrl: 'modules/test/test.html'
                    })
                    .otherwise(
                    {redirectTo: '/'}
                );
            }
        ]);

})();

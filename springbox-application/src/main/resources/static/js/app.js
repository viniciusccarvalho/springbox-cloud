var springbox = angular.module('springbox', ['ngRoute','ui.bootstrap']).config(function ($routeProvider, $httpProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'views/home.html',
            controller: 'home'
        })
        .when('/details/:mlId',{
            templateUrl: 'views/movieDetails.html',
            controller: 'details'
        })



    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
});
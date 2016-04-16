var springbox = angular.module('springbox', ['ngRoute','ui.bootstrap'])

springbox.config(function ($routeProvider, $httpProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'views/home.html',
            controller: 'home'
        })
        .when('/details/:id',{
            templateUrl: 'views/movieDetails.html',
            controller: 'details'
        })

    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
});

springbox.run(function($rootScope){
    $rootScope.config = {
        popularSize: 5,
        images: {
            base_url: "http://image.tmdb.org/t/p/",
            popular_size: "w1280",
            detail_size: "w342",
            similar_size: "w185",
            genre_size: "w300"

        }
    }
});
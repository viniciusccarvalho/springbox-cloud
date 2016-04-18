var springbox = angular.module('springbox');
springbox.controller('home', function ($rootScope, $scope, $http, $location) {
    $scope.myInterval = 15000;
    $scope.active =0;
    $scope.noWrapSlides = false;
    var currIndex = 0;
    $http.get('/catalog/movies/popular/'+$rootScope.config.popularSize).success(function(data){
        $scope.slides = [];
        angular.forEach(data,function(m,key){
            m.backdropPath = $rootScope.config.images.base_url + $rootScope.config.images.popular_size + m.backdropPath;
            $scope.slides.push({
                text: m.title,
                id: currIndex++,
                mid: m.id,
                image: m.backdropPath
            })
        });
        $scope.popularMovies = data;


    });
    $http.get('/genres/random').success(function(data){
        $scope.genres = data;
        angular.forEach($scope.genres, function(genre){
            $http.get('/catalog/movies/random/genre/'+genre.id).then(function(response){
                var data = response.data;
                angular.forEach(data,function(m,key){
                    m.backdropPath = $rootScope.config.images.base_url + $rootScope.config.images.genre_size + m.backdropPath;
                });
                genre.movies = data;

            })
        })
    });

    $scope.loadMovie = function(mlId){
        $location.path('/details/'+mlId);
    }
});
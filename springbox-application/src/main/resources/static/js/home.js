var springbox = angular.module('springbox');
springbox.controller('home', function ($rootScope, $scope, $http, $location) {
    $scope.myInterval = 15000;
    $http.get('/movies/popular').success(function(data){
        $scope.popularMovies = data;
    });
    $http.get('/genres/random').success(function(data){
        $scope.genres = data;
        angular.forEach($scope.genres, function(genre){
            $http.get('/movies/random/genre/'+genre.id).then(function(response){
                genre.movies = response.data;
            })
        })
    });

    $scope.loadMovie = function(mlId){
        $location.path('/details/'+mlId);
    }
});
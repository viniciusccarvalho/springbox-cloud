var springbox = angular.module('springbox');
springbox.controller('details', function ($scope, $http, $routeParams) {
    $http.get('/catalog/movies/'+$routeParams.id).success(function(data){
        $scope.movie = data[0];
        $http.get('/catalog/movies/'+$scope.movie.id+'/similar').then(function(response){
            $scope.movie.recommendations = response.data;
            console.log($scope.movie);
        }, function(response){console.log("error calling recommendations service")});
    });
});
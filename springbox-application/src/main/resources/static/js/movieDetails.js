var springbox = angular.module('springbox');
springbox.controller('details', function ($scope, $http, $routeParams) {
    $http.get('/catalog/movies/'+$routeParams.mlId).success(function(data){
        $scope.movie = data[0];
        $http.get('/movies/recommendations/'+$scope.movie.mlId).then(function(response){
            $scope.movie.recommendations = response.data;
            console.log($scope.movie);
        }, function(response){console.log("error calling recommendations service")});
    });
});
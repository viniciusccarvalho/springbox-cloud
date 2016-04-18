var springbox = angular.module('springbox');
springbox.controller('details', function ($scope, $http, $routeParams, userStore) {
    $http.get('/catalog/movies/'+$routeParams.id).success(function(data){
        $scope.movie = data[0];
        userStore.currentMovie = $scope.movie.id;
        $scope.isReadonly = false;

        $scope.hoveringOver = function(value) {
            $scope.overStar = value;
            $scope.percent = 100 * (value / $scope.max);
        };




        $http.get('/catalog/movies/'+$scope.movie.id+'/similar').then(function(response){
            $scope.movie.recommendations = response.data;
        }, function(response){console.log("error calling recommendations service")});
    });

    $scope.$watch('rate', function(newValue, oldValue){
        if(oldValue != newValue){
            $http({
                    method: 'post',
                    url: '/ratings/',
                    data: {
                        movieId: userStore.currentMovie,
                        rating: $scope.rate

                    }
                }

            ).success(function(data){console.log("Rating successful")});
        }
    });

});
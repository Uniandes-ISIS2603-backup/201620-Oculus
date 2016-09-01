var mod = ng.module("profesoresModule");

    mod.controller("profesoresCtrl", ['$scope', '$state', '$stateParams', '$http', 'profesoresContext', function ($scope, $state, $stateParams, $http, context) {

            // Se inicializa el listado de profesores vacio 
            $scope.records = {};
            // Se cargan los profesores
            $http.get(context).then(function(response){
                $scope.records = response.data;    
            }, responseError);

            if ($stateParams.profeId !== null && $stateParams.profeId !== undefined) {
                id = $stateParams.profeId;
                $http.get(context + "/" + id)
                    .then(function (response) {
                        $scope.currentRecord = response.data;
                    }, responseError);
            } else
            {
                $scope.currentRecord = {
                    id: undefined /*Tipo Int*/,
                    name: '' /*Tipo String*/,
                    login: '' /*Tipo String*/,
                };

                $scope.alerts = [];
            }


            this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord;
                if (id == null) {
                    return $http.post(context, currentRecord)
                        .then(function () {
                            $state.go('profesoresList');
                        }, responseError);

                } else {

                    return $http.put(context + "/" + currentRecord.id, currentRecord)
                        .then(function () {
                            $state.go('profesoresList');
                        }, responseError);
                };
            }   
    }])(window.angular);
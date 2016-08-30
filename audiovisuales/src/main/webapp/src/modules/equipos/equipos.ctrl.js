(function (ng) 
{
    var mod = ng.module("equiposModule");

    mod.controller("equiposCtrl", ['$scope', '$state', '$stateParams', '$http', 'equiposContext', function ($scope, $state, $stateParams, $http, context) {


            $scope.records = {};
            // carga las equipos
            $http.get(context).then(function(response){
                $scope.records = response.data;    
            }, responseError);
            

            if ($stateParams.solicitudId !== null && $stateParams.solicitudId !== undefined) {
                
                // toma el id del parámetro
                id = $stateParams.solicitudId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                    .then(function (response) {
 
                        $scope.currentRecord = response.data;
                    }, responseError);

            } 
			else
            {

                $scope.currentRecord = {
                    id: undefined ,
                    name: '' /*Tipo String*/,
                };
              
                $scope.alerts = [];
            }
            
            this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord;
                
                // si el id es null, es un registro nuevo, entonces lo crea
                if (id == null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(context, currentRecord)
                        .then(function () {

                            $state.go('equiposGet');
                        }, responseError);
                        
                // si el id no es null, es un registro existente entonces lo actualiza
                } else {
                    
                    // ejecuta PUT en el recurso REST
                    return $http.put(context + "/" + currentRecord.id, currentRecord)
                        .then(function () {
                            // $http.put es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('equiposGet');
                        }, responseError);
                };
            };

            
            //Alertas
            this.closeAlert = function (index) {
                $scope.alerts.splice(index, 1);
            };

            // Función showMessage: Recibe el mensaje en String y su tipo con el fin de almacenarlo en el array $scope.alerts.
            function showMessage(msg, type) {
                var types = ["info", "danger", "warning", "success"];
                if (types.some(function (rc) {
                    return type === rc;
                })) {
                    $scope.alerts.push({type: type, msg: msg});
                }
            }

            this.showError = function (msg) {
                showMessage(msg, "danger");
            };

            this.showSuccess = function (msg) {
                showMessage(msg, "success");
            };

            var self = this;
            function responseError(response) {

                self.showError(response.data);
            }
            
        }]);

})(window.angular);
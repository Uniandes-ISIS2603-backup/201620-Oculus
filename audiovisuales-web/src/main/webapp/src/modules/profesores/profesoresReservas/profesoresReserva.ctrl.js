(function (ng) {
    var mod = ng.module("profesoresModule");
    equiposContext = "api/equipos";
    mod.controller("profesoresReservaCtrl", ['$scope', '$state', '$stateParams', '$http', 'profesoresContext','reservasContext', function ($scope, $state, $stateParams, $http, profesoresContext,reservasContext) {
            // inicialmente el listado de reservas está vacio
            $scope.records = {};
            $scope.recordsFechas = {};
            // carga las reservas
            $scope.actual=$stateParams.profesorId;
            var idP = $scope.actual;
            //alert($scope.actual);
            $http.get(profesoresContext + "/" + idP + "/reservas").then(function(response)
            {
                $scope.records = response.data;    
                //alert($scope.records[13].equipoDTO.id);
            }, responseError);
            
            $http.get(profesoresContext + "/" + idP + "/reservasRangoFechas").then(function(response)
            {
                $scope.recordsFechas = response.data;    
            }, responseError);
            
            $http.get(equiposContext+"/tipos").then(function(response){
                $scope.tipos=response.data;
                alert(tipos);
            }); 
            
            ; 
            
            this.saveRecord = function () 
            {
                currentRecord = $scope.currentRecord;
                currentRecord.estado = currentRecord.tipo.nombre;
                alert(currentRecord.estado);
                // ejecuta POST en el recurso REST
                return $http.post(profesoresContext+ "/" + idP + "/reservas", currentRecord)
                        .then(function () {
                            // $http.post es una promesa
                    // cuando termine bien, cambie de estado
                    $state.go('profesorReservasList');
                }, responseError);
                
            };
            
            this.cancelarRecord = function (record) {
                //currentRecord = $scope.currentRecord;
                var idR = record.id;
                
                // ejecuta DELETE en el recurso REST
                return $http.put(profesoresContext+ "/" + idP + "/cancelarReserva/" + idR)
                        .then(function () {
                            $state.reload();
                }, responseError);
                
            }
            
            
            
            
            
            
            // -----------------------------------------------------------------
            // Funciones para manejra los mensajes en la aplicación
            
            
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
(function (ng) {
    var mod = ng.module("profesoresModule");

    mod.controller("profesoresReservaCtrl", ['$scope', '$state', '$stateParams', '$http', 'profesoresContext','reservasContext', function ($scope, $state, $stateParams, $http, profesoresContext,reservasContext) {
            // inicialmente el listado de reservas está vacio
            $scope.records = {};
            // carga los reservas
            $scope.actual=$stateParams.profesorId;
            //alert($scope.actual);
            $http.get(profesoresContext + "/" + id + "/reservas").then(function(response)
            {
                $scope.records = response.data;    
            }, responseError);

            ; 
            
            this.saveRecord = function () 
            {
                currentRecord = $scope.currentRecord;

                    // ejecuta POST en el recurso REST
                    return $http.post(profesoresContext+ "/" + id + "/reservas", currentRecord)
                        .then(function () {
                            // $http.post es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('profesorReservasList');
                        }, responseError);

            };
            
           
            
           

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
(function (ng) {
    var mod = ng.module("administradorGeneralModule");

    mod.controller("administradorGeneralCtrl", ['$scope', '$state', '$stateParams', '$http', 'administradorGeneralContext', function ($scope, $state, $stateParams, $http, context) 
        {
            $scope.currentRecord = {};
            $scope.alerts = [];
            // acceso al GET de puntos de atencion 
            
            //guarda info
            
            $scope.records={};
            this.saveRecord = function(idPa)
            {
                
                currentRecord = $scope.currentRecord;
                

                
                
                $http.get('/audiovisuales/api/puntosDeAtencion/'+1+'/ReservasPendientes')
                
                     .then(function(response)
                {
                $scope.records = response.data;
                
                
                }, responseError); 
                $state.go('listarConsulta' );
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



(function (ng) {
    var mod = ng.module("administradoresModule");

    mod.controller("administradoresEquipoCtrl", ['$scope', '$state', '$stateParams', '$http', 'administradoresContext','equiposContext', function ($scope, $state, $stateParams, $http, administradoresContext,equiposContext) {
            // inicialmente el listado de equipos está vacio
            $scope.records = {};
            // carga los equipos
            $http.get(administradoresContext + "/" + id + "/equipos").then(function(response)
            {
                $scope.records = response.data;    
            }, responseError);

            $http.get(equiposContext+"/tipos").then(function(response){
                $scope.tipos=response.data;
            }); 
            
            this.saveRecord = function (id) 
            {
                currentRecord = $scope.currentRecord;
                
                // si el id es null, es un registro nuevo, entonces lo crea
                if (id == null) 
                {
                    alert(administradoresContext+ "/" + currentRecord.id + "/equipos");
                    // ejecuta POST en el recurso REST
                    return $http.post(administradoresContext+ "/" + id + "/equipos", currentRecord)
                        .then(function () {
                            // $http.post es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('administradorEquiposList');
                        }, responseError);
                        
                // si el id no es null, es un registro existente entonces lo actualiza
                } 
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
(function (ng) {
    var mod = ng.module("administradoresModule");

    mod.controller("administradoresCtrl", ['$scope', '$state', '$stateParams', '$http', 'administradoresContext', function ($scope, $state, $stateParams, $http, context) {
            // inicialmente el listado de administradores está vacio
            $scope.records = {};
            // carga los equipos
            $http.get(context).then(function(response)
            {
                $scope.records = response.data;    
            }, responseError);

            // el controlador recibió un administradorCodigo ??
            // revisa los parámetros (ver el :equipoId en la definición de la ruta)
            if ($stateParams.administradorCodigo !== null && $stateParams.administradorCodigo !== undefined) {
                
                // toma el id del parámetro
                codigo = $stateParams.administradorCodigo;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + codigo)
                    .then(function (response) {
                        // $http.get es una promesa
                        // cuando llegue el dato, actualice currentRecord
                        $scope.currentRecord = response.data;
                    }, responseError);

            // el controlador no recibió un equipoId
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentRecord = {
                    codigo: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    name: '' /*Tipo String*/,
                };
              
                $scope.alerts = [];
            }


            this.saveRecord = function (codigo) {
                currentRecord = $scope.currentRecord;
                
                // si el id es null, es un registro nuevo, entonces lo crea
                if (codigo == null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(context, currentRecord)
                        .then(function () {
                            // $http.post es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('administradoresList');
                        }, responseError);
                        
                // si el codigo no es null, es un registro existente entonces lo actualiza
                } else {
                    
                    // ejecuta PUT en el recurso REST
                    return $http.put(context + "/" + currentRecord.id, currentRecord)
                        .then(function () {
                            // $http.put es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('administradoresList');
                        }, responseError);
                };
            };
            
            this.deleteRecord=function(record)
            {
                id=record.id;
                return $http.delete(context+"/"+codigo)
                        .then(function(){
                            $state.reload();
                        },responseError);
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
(function (ng) 
{
    var mod = ng.module("profesoresModule");

    mod.controller("profesoresCtrl", ['$scope', '$state', '$stateParams', 
        '$http', 'profesoresContext', function ($scope, $state, $stateParams, $http, context) 
        {

            // Se inicializa el listado de profesores vacio 
            $scope.records = {};
            // Se cargan los profesores
            $http.get(context).then(function(response){
                $scope.records = response.data;    
            }, responseError);

            if ($stateParams.profesorId !== null && $stateParams.profesorId !== undefined) {
                id = $stateParams.profesorId;
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
            
            this.deleteRecord=function(record)
            {
                id=record.id;
                return $http.delete(context+"/"+id)
                        .then(function(){
                            $state.reload();
                        },responseError);
            }


            // -----------------------------------------------------------------
            // Funciones para manejar las fechas
            // -----------------------------------------------------------------

            $scope.popup = {
                opened: false
            };
            $scope.dateOptions = {
                dateDisabled: disabled,
                formatYear: 'yy',
                maxDate: new Date(2020, 5, 22),
                minDate: new Date(),
                startingDay: 1
            };

            this.today = function () {
                $scope.dt = new Date();
            };
            this.today();

            this.clear = function () {
                $scope.dt = null;
            };
            this.setDate = function (year, month, day) {
                $scope.dt = new Date(year, month, day);
            };

            this.open = function () {
                $scope.popup.opened = true;
            };

            function disabled(data) {
                var date = data.date,
                        mode = data.mode;
                return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
            }

            
            // -----------------------------------------------------------------
            // Funciones para manejar los mensajes en la aplicación
            // -----------------------------------------------------------------
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
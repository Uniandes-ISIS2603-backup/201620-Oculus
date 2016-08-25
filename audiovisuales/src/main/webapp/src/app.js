(function (ng) {

    // definición del módulo de la aplicación
    var mod = ng.module("mainApp", [
        
        // librerías de terceros
        "ui.router",
        "ngMessages"
        
        // módulos de la aplicación
        // eg. "citiesModule"
    ]);

    // configuración de los mensajes de log
    mod.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);

    // configuración de las rutas
    /* 
    mod.config(['$urlRouterProvider', function ($urlRouterProvider) {
            $urlRouterProvider.otherwise('/citiesList');
        }]);
    */
  
})(window.angular);
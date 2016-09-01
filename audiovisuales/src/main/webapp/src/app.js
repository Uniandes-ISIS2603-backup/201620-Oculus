(function (ng) {

   var mod = ng.module("audiovisuales",
   ["ui.router","ngMessages","equiposModule","solicitudesModule","locacionesModule","administradoresModule","profesoresModule"]);

    mod.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);

    mod.config(['$urlRouterProvider', function ($urlRouterProvider) {
            $urlRouterProvider.otherwise('/equiposList');
        }]);

  
})(window.angular);
//"ProfesoresModule,"
//No cargan!!!
(function (ng) {

   var mod = ng.module("audiovisuales",
   ["ui.router","ngMessages",
       "equiposModule",
       "puntosDeAtencionModule",
       "reservasModule",
       "administradoresModule",
       "profesoresModule",
       "administradorGeneralModule"
   ]);

    mod.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);

    mod.config(['$urlRouterProvider', function ($urlRouterProvider) {
            $urlRouterProvider.otherwise('/default');
        }]);

  
})(window.angular);
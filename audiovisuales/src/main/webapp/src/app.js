

(function (ng) {

   var mod = ng.module("audiovisuales",["ui-router","solicitudesModule","locacionesModule","equiposModule"]);

    mod.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);

    mod.config(['$urlRouterProvider', function ($urlRouterProvider) {
            $urlRouterProvider.otherwise('/citiesList');
        }]);

  
})(window.angular);
(function (ng) {
    
    var mod = ng.module("audiovisuales",
    ["ui.router","ngMessages",
        "equiposModule",
        "puntosDeAtencionModule",
        "reservasModule",
        "administradoresModule",
        "profesoresModule",
        "administradorGeneralModule",
        "datePickerModule"
    ]);
    mod.directive('datePicker', [function () {
            return {
                scope: {
                    model: '=',
                    name: '@'
                },
                restrict: 'E',
                templateUrl: 'src/utils/datepicker.tpl.html',
                controller: 'datePickerCtrl'
            };
        }]);
    mod.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);
    
    mod.config(['$urlRouterProvider', function ($urlRouterProvider) {
            $urlRouterProvider.otherwise('/default');
        }]);
    
    
})(window.angular);
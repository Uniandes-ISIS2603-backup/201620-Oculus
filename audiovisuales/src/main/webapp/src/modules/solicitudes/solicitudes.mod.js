var mod = angular.module('solicitudesModule', ['ui.router']);
    mod.constant("solicitudesContext", "api/solicitudes");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/solicitudes';
            $urlRouterProvider.otherwise("/solicitudes");
            $stateProvider
                .state('solicitudes'),{
                    url: "/solicitudes",
                    templateUrl: basePath + "solicitudes.html"
                }
    }]);
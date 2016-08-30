var mod = angular.module('solicitudesModule', ['ui.router']);
    mod.constant("citiesContext", "api/cities");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/solicitudes';
            $urlRouterProvider.otherwise("/solicitudes");
            $stateProvider
                .state('solicitudes'),{
                    url: "/solicitudes",
                    templateUrl: basePath + "solicitudes.html"
                }
    }]);
(function (ng) {
    var mod = ng.module("equiposModule", ["ngMessages"]);
    mod.constant("equiposContext", "api/equipos");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/equipos/';
            $urlRouterProvider.otherwise("/equiposList");
     
            $stateProvider.state('equiposList', {
                url: '/equipos',
                views: {
                    'mainView': {
                        controller: 'equiposCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'equipos.list.html'
                    }
                }
            }).state('cityCreate', {
                url: '/equipos/create',
                views: {
                    'mainView': {
                        controller: 'equiposCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'equipos.create.html'
                    }
                }

            }).state('cityEdit', {
                url: '/equipos/:cityId',
                param: {
                    cityId: null
                },
                views: {
                    'mainView': {
                        controller: 'equiposCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'equipos.create.html'
                    }
                }
            });
        }]);
})(window.angular);
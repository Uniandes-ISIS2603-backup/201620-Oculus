(function (ng) {
    var mod = ng.module("locacionesModule", ["ngMessages"]);
    mod.constant("locacionesContext", "api/locaciones");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/locaciones/';
            $urlRouterProvider.otherwise("/locacionesList");
     
            $stateProvider.state('locacionesList', {
                url: '/locaciones',
                views: {
                    'mainView': {
                        controller: 'locacionesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'locaciones.list.html'
                    }
                }
            }).state('cityCreate', {
                url: '/locaciones/create',
                views: {
                    'mainView': {
                        controller: 'locacionesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'locaciones.create.html'
                    }
                }

            }).state('cityEdit', {
                url: '/locaciones/:cityId',
                param: {
                    cityId: null
                },
                views: {
                    'mainView': {
                        controller: 'locacionesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'locaciones.create.html'
                    }
                }
            });
        }]);
})(window.angular);
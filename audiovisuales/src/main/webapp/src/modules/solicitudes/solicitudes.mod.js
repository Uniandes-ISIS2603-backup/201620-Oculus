(function (ng) {
    var mod = ng.module("solicitudesModule", ["ngMessages"]);
    mod.constant("solicitudesContext", "api/solicitudes");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/solicitudes/';
            $urlRouterProvider.otherwise("/solicitudesList");
     
            $stateProvider.state('solicitudesList', {
                url: '/solicitudes',
                views: {
                    'mainView': {
                        controller: 'solicitudesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'solicitudes.list.html'
                    }
                }
            }).state('cityCreate', {
                url: '/solicitudes/create',
                views: {
                    'mainView': {
                        controller: 'solicitudesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'solicitudes.create.html'
                    }
                }

            }).state('cityEdit', {
                url: '/solicitudes/:cityId',
                param: {
                    cityId: null
                },
                views: {
                    'mainView': {
                        controller: 'solicitudesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'solicitudes.create.html'
                    }
                }
            });
        }]);
})(window.angular);
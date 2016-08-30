(function (ng) {
    var mod = angular.module('solicitudesModule', ['ui.router']);
    mod.constant("solicitudesContext", "api/solicitudes");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/solicitudes';
            $urlRouterProvider.otherwise("/solicitudesGet");
            
            $stateProvider.state('solicitudesGet',{
                url: "/solicitudes",
                views: {
                'mainView': {
                    controller: 'solicitudesCtrl',
                    controllerAs: 'ctrl',
                    templateUrl: basePath + 'solicitudes.get.html'
                }
            }
            }).state('solicitudGet',{
                url: "/solicitudes/:solicitudId",
                param: {
                    cityId: null
                },
                views: {
                'mainView': {
                    controller: 'solicitudesCtrl',
                    controllerAs: 'ctrl',
                    templateUrl: basePath + 'solicitudes.getSolicitud.html'
                }
            }
            }).state('solicitudCreate', {
                url: '/solicitudes/create',
                views: {
                    'mainView': {
                        controller: 'solicitudesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'solicitudes.create.html'
                    }
                }

            }).state('solicitudUpdate', {
                url: '/solicitudes/update/:solicitudId',
                param: {
                    solicitudId: null
                },
                views: {
                    'mainView': {
                        controller: 'solicitudesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'solicitudes.update.html'
                    }
                }
            }).state('solicitudDelete', {
                url: '/solicitudes/delete/:solicitudId',
                param: {
                    solicitudId: null
                },
                views: {
                    'mainView': {
                        controller: 'solicitudesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'solicitudes.delete.html'
                    }
                }
            });
    }]);
})(window.angular);

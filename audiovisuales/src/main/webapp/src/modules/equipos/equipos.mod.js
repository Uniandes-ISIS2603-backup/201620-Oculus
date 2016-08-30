(function (ng) {
    var mod = angular.module('equiposModule', ['ui.router']);
    mod.constant("equiposContext", "api/equipos");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/equipos';
            $urlRouterProvider.otherwise("/equiposList");
            
            $stateProvider.state('equiposGet',{
                url: "/equipos",
                views: {
                'mainView': {
                    controller: 'equiposCtrl',
                    controllerAs: 'ctrl',
                    templateUrl: basePath + 'equipos.get.html'
                }
            }
            }).state('solicitudGet',{
                url: "/equipos/:solicitudId",
                param: {
                    cityId: null
                },
                views: {
                'mainView': {
                    controller: 'equiposCtrl',
                    controllerAs: 'ctrl',
                    templateUrl: basePath + 'equipos.getSolicitud.html'
                }
            }
            }).state('solicitudCreate', {
                url: '/equipos/create',
                views: {
                    'mainView': {
                        controller: 'equiposCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'equipos.create.html'
                    }
                }

            }).state('solicitudUpdate', {
                url: '/equipos/update/:solicitudId',
                param: {
                    solicitudId: null
                },
                views: {
                    'mainView': {
                        controller: 'equiposCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'equipos.update.html'
                    }
                }
            }).state('solicitudDelete', {
                url: '/equipos/delete/:solicitudId',
                param: {
                    solicitudId: null
                },
                views: {
                    'mainView': {
                        controller: 'equiposCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'equipos.delete.html'
                    }
                }
            });
    }]);
})(window.angular);
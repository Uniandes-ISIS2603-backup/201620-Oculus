(function () {
    var mod = angular.module('reservasModule', ['ui.router']);
    mod.constant("reservasContext", "api/reservas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/reservas/';
            $urlRouterProvider.otherwise("/reservasGet");
            $stateProvider.state('reservasGet',{
                url: "/reservas",
                views: {
                'mainView': {
                    controller: 'reservasCtrl',
                    controllerAs: 'ctrl',
                    templateUrl: basePath + 'reservas.get.html'
                }
            }
            }).state('reservaCreate', {
                url: '/reservas/create',
                views: {
                    'mainView': {
                        controller: 'reservasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'reservas.create.html'
                    }
                }

            }).state('reservaUpdate', {
                url: '/reservas/update/{reservaId:int}',
                param: {
                    reservaId: null
                },
                views: {
                    'mainView': {
                        controller: 'reservasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'reservas.create.html'
                    }
                }
            }).state('reservaDelete', {
                url: '/reservas/delete/:reservaId',
                param: {
                    reservaId: null
                },
                views: {
                    'mainView': {
                        controller: 'reservasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'reservas.delete.html'
                    }
                }
            }).state('reservaGet',{
                url: "/reservas/:reservaId",
                param: {
                    cityId: null
                },
                views: {
                'mainView': {
                    controller: 'reservasCtrl',
                    controllerAs: 'ctrl',
                    templateUrl: basePath + 'reservas.getReserva.html'
                }
            }
            });
    }]);
})(window.angular);

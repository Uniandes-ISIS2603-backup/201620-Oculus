(function () {
    var mod = angular.module('reservasModule', ['ui.router']);
    mod.constant("reservasContext", "api/reservas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/reservas/';
            $urlRouterProvider.otherwise("/reservasList");
            $stateProvider.state('reservasList',{
                url: "/reservas",
                views: {
                'mainView': {
                    controller: 'reservasCtrl',
                    controllerAs: 'ctrl',
                    templateUrl: basePath + 'reservas.list.html'
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

            }).state('reservaEdit', {
                url: '/reservas/edit/{reservaId:int}',
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

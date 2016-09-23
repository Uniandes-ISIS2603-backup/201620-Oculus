/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("puntosDeAtencionModule", ["ngMessages"]);
    mod.constant("puntosDeAtencionContext", "api/puntosDeAtencion");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/puntosDeAtencion/';
            $urlRouterProvider.otherwise("/puntosDeAtencionList");
     
            $stateProvider.state('puntosDeAtencionList', {
                url: '/puntosDeAtencion',
                views: {
                    'mainView': {
                        controller: 'puntosDeAtencionCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'puntosDeAtencion.list.html'
                    }
                }
            }).state('puntoDeAtencionCreate', {
                url: '/puntosDeAtencion/create',
                views: {
                    'mainView': {
                        controller: 'puntosDeAtencionCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'puntosDeAtencion.create.html'
                    }
                }

            }).state('puntoDeAtencionEdit', {
                url: '/puntosDeAtencion/edit/{puntoDeAtencionId:int}',
                param: {
                    puntoDeAtencionId: null
                },
                views: {
                    'mainView': {
                        controller: 'puntosDeAtencionCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'puntosDeAtencion.edit.html'
                    }
                }
            })
            ;
            
        }]);
})(window.angular);



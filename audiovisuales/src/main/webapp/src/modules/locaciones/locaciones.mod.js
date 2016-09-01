/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
            }).state('locacionCreate', {
                url: '/locaciones/create',
                views: {
                    'mainView': {
                        controller: 'locacionesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'locaciones.create.html'
                    }
                }

            }).state('locacionEdit', {
                url: '/locaciones/:locacionId',
                param: {
                    locacionId: null
                },
                views: {
                    'mainView': {
                        controller: 'locacionesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'locaciones.create.html'
                    }
                }
            }).state('locacionDelete', {
                url: '/locaciones/delete/:locacionDelId',
                param: {
                    locacionDelId: null
                },
                views: {
                    'mainView': {
                        controller: 'locacionesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'locaciones.delete.html'
                    }
                }

            });
            
        }]);
})(window.angular);


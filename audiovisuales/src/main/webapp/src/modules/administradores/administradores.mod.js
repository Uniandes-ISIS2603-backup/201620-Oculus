/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {
    var mod = ng.module("administradoresModule", ["ngMessages"]);
    mod.constant("administradoresContext", "api/administradores");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/administradores/';
            $urlRouterProvider.otherwise("/administradoresList");
     
            $stateProvider.state('administradoresList', {
                url: '/administradores',
                views: {
                    'mainView': {
                        controller: 'administradoresCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'administradores.list.html'
                    }
                }
            }).state('administradorCreate', {
                url: '/administradores/create',
                views: {
                    'mainView': {
                        controller: 'administradoresCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'administraciones.create.html'
                    }
                }

            }).state('administradorEdit', {
                url: '/administradores/:administradorId',
                param: {
                    administradorId: null
                },
                views: {
                    'mainView': {
                        controller: 'administradoresCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'administradores.create.html'
                    }
                }
            }).state('administradorDelete', {
                url: '/administradores/delete/:administradorId',
                param: {
                    administradorId: null
                },
                views: {
                    'mainView': {
                        controller: 'administradoresCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'administradores.delete.html'
                    }
                }

            });
            
        }]);
})(window.angular);



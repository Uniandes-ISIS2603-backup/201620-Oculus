(function (ng) 
{
    var mod = ng.module("administradoresModule", ["ngMessages"]);
    mod.constant("administradoresContext", "api/administradores");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) 
        {
            var basePath = 'src/modules/administradores/';
            $urlRouterProvider.otherwise("/administradoresList");
     
            $stateProvider.state('administradoresList', 
            {
                url: '/administradores',
                views: {
                    'mainView': {
                        
                        controller: 'administradoresCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'administradores.list.html'
                    }
                }
            }).state('administradoresCreate', {
                url: '/administradores/create',
                views: {
                    'mainView': {   
                        controller: 'administradoresCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'administradores.create.html'
                    }
                }

            }).state('administradorEdit', {
                url: '/administradores/edit/{administradorId:int}',
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
            }).state('administradoresReservasPendientes', {
               url: '/administradores/reservasPendientes',
              views: {
                    'mainView': {
                        controller: 'administradoresCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'administradores.reservasPendientes.html'
                    }
                }
            }).state('administradoresReservasCanceladas', {
                url: '/administradores/reservasCanceladas',
                views: {
                    'mainView': {
                        controller: 'administradoresCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'administradores.reservasCanceladas.html'
                    }
                }
            });
        }]);
})(window.angular);

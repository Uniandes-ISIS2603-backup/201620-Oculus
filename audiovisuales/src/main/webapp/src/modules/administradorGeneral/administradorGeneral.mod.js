(function (ng) 
{
    var mod = ng.module("administradorGeneralModule", ["ngMessages"]);
    mod.constant("administradorGeneralContext", "api/administradorGeneral");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) 
        {
            var basePath = 'src/modules/administradorGeneral/';
            $urlRouterProvider.otherwise("/administradoresList");
     
            $stateProvider.state('adminGeneralreservasCanceladas', 
            {
                url: '/administradorGeneral/reservasCanceladas',
                views: {
                    'mainView': {
                        
                        controller: 'administradorGenralCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + '..AUN NO SE QUE VA AQUI....html.....'
                    }
                }
            }).state('adminGeneralreservasPendientes', {
                url: '/administradorGeneral/reservasPendientes',
                views: {
                    'mainView': {   
                        controller: 'administradorGenralCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + '..AUN NO SE QUE VA AQUI....html.....'
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

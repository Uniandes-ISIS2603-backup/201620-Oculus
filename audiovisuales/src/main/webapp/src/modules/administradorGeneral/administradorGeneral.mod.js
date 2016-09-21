(function (ng) 
{
    var mod = ng.module("administradorGeneralModule", ["ngMessages"]);
    mod.constant("administradorGeneralContext", "api/administradorGeneral");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) 
        {
            var basePath = 'src/modules/administradorGeneral/';
            $urlRouterProvider.otherwise("/adminGeneralReservasCanceladas");
     
            $stateProvider.state('adminGeneralReservasCanceladas', 
            {
                url: '/administradorGeneral/reservasCanceladas',
                views: {
                    'mainView': {
                        
                        controller: 'administradorGeneralCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'administradorGeneral.search.html'
                    }
                }
            }).state('adminGeneralReservasPendientes', {
                url: '/administradorGeneral/reservasPendientes',
                views: {
                    'mainView': {   
                        controller: 'administradorGeneralCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'administradorGeneral.search.html'
                    }
                }

            }).state('listarConsulta',{
                url: '/administradorGeneral/reservasPoC/List',
                views: {
                    'mainView': {   
                        controller: 'administradorGeneralCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: 'src/modules/reservas/reservas.list.html'
                    }
                
            }}).state('adminGeneralPuntosDeAtencion', {
                url: '/administradorGeneral/puntosDeAtencion',
                views: {
                    'mainView': {   
                        controller: 'puntosDeAtencionCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: 'src/modules/puntosDeAtencion/puntosDeAtencion.list.html'}}});
        }]);
})(window.angular);

(function (ng) 
{
    var mod = ng.module("administradorGeneralModule", ["ngMessages"]);
    mod.constant("administradorGeneralContext", "api/administradorGeneral");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) 
        {
            var basePath = 'src/modules/administradorGeneral/';
            $urlRouterProvider.otherwise("/administradoresList");
     
            $stateProvider.state('adminGeneralReservasCanceladas', 
            {
                url: '/administradorGeneral/reservasCanceladas',
                views: {
                    'mainView': {
                        
                        controller: 'administradorGenralCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + '..AUN NO SE QUE VA AQUI....html.....'
                    }
                }
            }).state('adminGeneralReservasPendientes', {
                url: '/administradorGeneral/reservasPendientes',
                views: {
                    'mainView': {   
                        controller: 'administradorGenralCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + '..AUN NO SE QUE VA AQUI....html.....'
                    }
                }

            });
        }]);
})(window.angular);
